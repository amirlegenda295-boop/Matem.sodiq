package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.auth.AuthScreen
import com.example.auth.signOut
import com.example.data.MathRepository
import com.example.model.MathLesson
import com.example.ui.screens.CertificatesScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.FormulaSheetModal
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.ProgressProfileScreen
import com.example.ui.screens.QuizExamScreen
import com.example.ui.theme.MatemaProTheme
import com.example.ui.viewmodel.MathMasterViewModel
import com.example.ui.viewmodel.UiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

private enum class MainTab(
    val titleRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val tag: String
) {
    HOME(R.string.nav_home, Icons.Rounded.Home, Icons.Outlined.Home, "nav_home"),
    CURRICULUM(R.string.nav_curriculum, Icons.Rounded.School, Icons.Outlined.School, "nav_curriculum"),
    EXAMS(R.string.nav_exams, Icons.Rounded.WorkspacePremium, Icons.Outlined.WorkspacePremium, "nav_exams"),
    PROFILE(R.string.nav_profile, Icons.Rounded.Person, Icons.Outlined.Person, "nav_profile")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatemaProTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var currentUser by remember { mutableStateOf(Firebase.auth.currentUser) }
    DisposableEffect(Unit) {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            currentUser = auth.currentUser
        }
        Firebase.auth.addAuthStateListener(listener)
        onDispose { Firebase.auth.removeAuthStateListener(listener) }
    }

    val user = currentUser
    if (user == null) {
        AuthScreen(onAuthSuccess = { currentUser = Firebase.auth.currentUser })
    } else {
        val uid = user.uid
        key(uid) {
            AuthenticatedAppShell(
                currentUserId = uid,
                userDisplayName = user.displayName ?: "Matematachi",
                userEmail = user.email
            )
        }
    }
}

@Composable
private fun AuthenticatedAppShell(
    currentUserId: String,
    userDisplayName: String,
    userEmail: String?
) {
    val viewModel: MathMasterViewModel = viewModel(
        key = currentUserId,
        factory = viewModelFactory {
            initializer {
                val app = checkNotNull(this[APPLICATION_KEY]) {
                    "APPLICATION_KEY missing from CreationExtras"
                }
                val databaseId = app.getString(R.string.firestore_database_id)
                val db = FirebaseFirestore.getInstance(databaseId)
                MathMasterViewModel(
                    repository = MathRepository(db),
                    currentUserId = currentUserId,
                    userDisplayName = userDisplayName
                )
            }
        }
    )

    val profileState by viewModel.userProfileState.collectAsStateWithLifecycle()
    val progressState by viewModel.lessonProgressState.collectAsStateWithLifecycle()
    val attemptsState by viewModel.examAttemptsState.collectAsStateWithLifecycle()
    val savedState by viewModel.savedQuestionsState.collectAsStateWithLifecycle()
    val activeQuiz by viewModel.quizSession.collectAsStateWithLifecycle()
    val feedbackMessage by viewModel.feedbackMessage.collectAsStateWithLifecycle()

    var currentTab by remember { mutableStateOf(MainTab.HOME) }
    var activeLesson by remember { mutableStateOf<MathLesson?>(null) }
    var showFormulas by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = remember(context) { CredentialManager.create(context) }

    LaunchedEffect(feedbackMessage) {
        val msg = feedbackMessage
        if (msg != null) {
            snackbarHostState.showSnackbar(msg)
            viewModel.clearFeedback()
        }
    }

    if (showFormulas) {
        FormulaSheetModal(onDismiss = { showFormulas = false })
    }

    val profile = (profileState as? UiState.Success)?.data
    val lessonProgress = (progressState as? UiState.Success)?.data.orEmpty()
    val examAttempts = (attemptsState as? UiState.Success)?.data.orEmpty()
    val savedQuestions = (savedState as? UiState.Success)?.data.orEmpty()
    val savedIds = remember(savedQuestions) { savedQuestions.map { it.questionId }.toSet() }

    val currentQuiz = activeQuiz
    val currentLesson = activeLesson

    when {
        currentQuiz != null -> {
            QuizExamScreen(
                session = currentQuiz,
                savedQuestionIds = savedIds,
                onSelectOption = { qId, opt -> viewModel.selectAnswer(qId, opt) },
                onToggleExplanation = { qId -> viewModel.toggleExplanation(qId) },
                onGoToQuestion = { idx -> viewModel.goToQuestion(idx) },
                onSubmit = { viewModel.submitActiveQuiz() },
                onSaveQuestion = { q ->
                    viewModel.saveQuestionWithNote(q, "Imtihon paytida belgilangan masala")
                },
                onOpenFormulas = { showFormulas = true },
                onExit = { viewModel.closeQuizSession() }
            )
        }

        currentLesson != null -> {
            val isCompleted = lessonProgress.any { it.lessonId == currentLesson.id && it.completed }
            LessonDetailScreen(
                lesson = currentLesson,
                isAlreadyCompleted = isCompleted,
                onBack = { activeLesson = null },
                onMarkMastered = { mastery ->
                    viewModel.markLessonCompleted(
                        lessonId = currentLesson.id,
                        stageId = currentLesson.stageId,
                        masteryPercent = mastery,
                        alreadyCompleted = isCompleted
                    )
                },
                onSaveCheckQuestion = { q ->
                    viewModel.saveQuestionWithNote(q, "${currentLesson.title} mavzusidan")
                }
            )
        }

        else -> {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val useNavRail = maxWidth >= 600.dp

                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        if (!useNavRail) {
                            NavigationBar {
                                MainTab.entries.forEach { tab ->
                                    val selected = tab == currentTab
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { currentTab = tab },
                                        icon = {
                                            Icon(
                                                imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = stringResource(tab.titleRes)
                                            )
                                        },
                                        label = { Text(stringResource(tab.titleRes)) },
                                        modifier = Modifier.testTag(tab.tag)
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (useNavRail) {
                            NavigationRail(modifier = Modifier.fillMaxHeight()) {
                                MainTab.entries.forEach { tab ->
                                    val selected = tab == currentTab
                                    NavigationRailItem(
                                        selected = selected,
                                        onClick = { currentTab = tab },
                                        icon = {
                                            Icon(
                                                imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = stringResource(tab.titleRes)
                                            )
                                        },
                                        label = { Text(stringResource(tab.titleRes)) },
                                        modifier = Modifier.testTag(tab.tag)
                                    )
                                }
                            }
                        }

                        Box(modifier = Modifier.fillMaxSize()) {
                            val errorMsg = (profileState as? UiState.Error)?.message
                                ?: (progressState as? UiState.Error)?.message
                            if (profileState is UiState.Loading && progressState is UiState.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            } else {
                                if (errorMsg != null) {
                                    Surface(
                                        color = MaterialTheme.colorScheme.errorContainer,
                                        modifier = Modifier
                                            .align(Alignment.TopCenter)
                                            .padding(12.dp)
                                    ) {
                                        Text(
                                            text = errorMsg,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onErrorContainer,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                    }
                                }
                                when (currentTab) {
                                    MainTab.HOME -> HomeScreen(
                                        profile = profile,
                                        lessonProgress = lessonProgress,
                                        onOpenLesson = { activeLesson = it },
                                        onOpenCurriculum = { currentTab = MainTab.CURRICULUM },
                                        onOpenExams = { currentTab = MainTab.EXAMS },
                                        onStartCertificateExam = { track ->
                                            viewModel.startQuizSession(
                                                title = track.title,
                                                examType = track.examType.name,
                                                questions = track.questions,
                                                isTimedExam = true,
                                                durationMinutes = track.durationMinutes
                                            )
                                        },
                                        onOpenFormulas = { showFormulas = true }
                                    )

                                    MainTab.CURRICULUM -> CurriculumScreen(
                                        lessonProgress = lessonProgress,
                                        onOpenLesson = { activeLesson = it },
                                        onStartStageQuiz = { stage ->
                                            viewModel.startQuizSession(
                                                title = "${stage.number}-bosqich: ${stage.title}",
                                                examType = "STAGE_QUIZ",
                                                questions = stage.stageQuizQuestions,
                                                isTimedExam = false,
                                                durationMinutes = 0
                                            )
                                        }
                                    )

                                    MainTab.EXAMS -> CertificatesScreen(
                                        examAttempts = examAttempts,
                                        onStartPractice = { track ->
                                            viewModel.startQuizSession(
                                                title = "${track.title} (Mashq)",
                                                examType = track.examType.name,
                                                questions = track.questions,
                                                isTimedExam = false,
                                                durationMinutes = 0
                                            )
                                        },
                                        onStartTimedExam = { track ->
                                            viewModel.startQuizSession(
                                                title = track.title,
                                                examType = track.examType.name,
                                                questions = track.questions,
                                                isTimedExam = true,
                                                durationMinutes = track.durationMinutes
                                            )
                                        },
                                        onOpenFormulas = { showFormulas = true }
                                    )

                                    MainTab.PROFILE -> ProgressProfileScreen(
                                        profile = profile,
                                        userEmail = userEmail,
                                        examAttempts = examAttempts,
                                        savedQuestions = savedQuestions,
                                        onSelectTargetExam = { viewModel.updateTargetExam(it) },
                                        onUpdateSavedNote = { sq, note ->
                                            viewModel.saveQuestionWithNote(
                                                com.example.model.MathQuestion(
                                                    id = sq.questionId,
                                                    topicTitle = sq.topicTitle,
                                                    examTag = sq.examTag,
                                                    prompt = sq.prompt,
                                                    options = emptyList(),
                                                    correctIndex = 0,
                                                    explanationSteps = emptyList()
                                                ),
                                                note
                                            )
                                        },
                                        onDeleteSavedQuestion = { viewModel.deleteSavedQuestion(it) },
                                        onOpenFormulas = { showFormulas = true },
                                        onSignOut = {
                                            signOut(credentialManager = credentialManager, scope = scope)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
