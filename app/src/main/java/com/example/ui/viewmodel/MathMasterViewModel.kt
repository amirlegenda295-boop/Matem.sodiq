package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CertificateExamBank
import com.example.data.MathRepository
import com.example.model.ExamAttempt
import com.example.model.LessonProgress
import com.example.model.MathQuestion
import com.example.model.SavedQuestion
import com.example.model.TargetExamTrack
import com.example.model.UserProfile
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

data class ActiveQuizSession(
    val title: String,
    val examType: String,
    val isTimedExam: Boolean,
    val questions: List<MathQuestion>,
    val currentIndex: Int = 0,
    val selectedAnswers: Map<String, Int> = emptyMap(),
    val revealedExplanations: Set<String> = emptySet(),
    val totalDurationSeconds: Int = 0,
    val remainingSeconds: Int = 0,
    val isCompleted: Boolean = false,
    val correctCount: Int = 0,
    val scaledScore: Int = 0,
    val earnedXp: Int = 0
)

class MathMasterViewModel(
    private val repository: MathRepository,
    private val currentUserId: String,
    private val userDisplayName: String
) : ViewModel() {

    val userProfileState: StateFlow<UiState<UserProfile?>> =
        repository.observeUserProfile(currentUserId)
            .map<UserProfile?, UiState<UserProfile?>> { UiState.Success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val rootError = firestoreError ?: error
                Log.w(TAG, "Error observing profile", rootError)
                emit(UiState.Error(rootError.message ?: "Profilni yuklashda xatolik"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = UiState.Loading
            )

    val lessonProgressState: StateFlow<UiState<List<LessonProgress>>> =
        repository.observeLessonProgress(currentUserId)
            .map<List<LessonProgress>, UiState<List<LessonProgress>>> { UiState.Success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val rootError = firestoreError ?: error
                Log.w(TAG, "Error observing lesson progress", rootError)
                emit(UiState.Error(rootError.message ?: "Dars jarayonini yuklashda xatolik"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = UiState.Loading
            )

    val examAttemptsState: StateFlow<UiState<List<ExamAttempt>>> =
        repository.observeExamAttempts(currentUserId)
            .map<List<ExamAttempt>, UiState<List<ExamAttempt>>> { UiState.Success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val rootError = firestoreError ?: error
                Log.w(TAG, "Error observing exam attempts", rootError)
                emit(UiState.Error(rootError.message ?: "Imtihon natijalarini yuklashda xatolik"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = UiState.Loading
            )

    val savedQuestionsState: StateFlow<UiState<List<SavedQuestion>>> =
        repository.observeSavedQuestions(currentUserId)
            .map<List<SavedQuestion>, UiState<List<SavedQuestion>>> { UiState.Success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val rootError = firestoreError ?: error
                Log.w(TAG, "Error observing saved questions", rootError)
                emit(UiState.Error(rootError.message ?: "Saqlangan masalalarni yuklashda xatolik"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = UiState.Loading
            )

    private val _quizSession = MutableStateFlow<ActiveQuizSession?>(null)
    val quizSession: StateFlow<ActiveQuizSession?> = _quizSession.asStateFlow()

    private val _feedbackMessage = MutableStateFlow<String?>(null)
    val feedbackMessage: StateFlow<String?> = _feedbackMessage.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            repository.ensureUserProfile(userDisplayName)
                .onFailure { e ->
                    _feedbackMessage.value = e.localizedMessage ?: "Profil yaratishda xatolik"
                }
        }
    }

    fun clearFeedback() {
        _feedbackMessage.value = null
    }

    fun updateTargetExam(track: TargetExamTrack) {
        viewModelScope.launch {
            repository.updateTargetExam(track)
                .onSuccess { _feedbackMessage.value = "Maqsadli imtihon yangilandi: ${track.titleUz}" }
                .onFailure { _feedbackMessage.value = it.localizedMessage ?: "Saqlashda xatolik" }
        }
    }

    fun markLessonCompleted(lessonId: String, stageId: String, masteryPercent: Int, alreadyCompleted: Boolean) {
        viewModelScope.launch {
            repository.markLessonMastered(
                lessonId = lessonId,
                stageId = stageId,
                masteryPercent = masteryPercent,
                alreadyCompleted = alreadyCompleted
            ).onSuccess {
                _feedbackMessage.value = if (alreadyCompleted) {
                    "Mavzu natijasi yangilandi!"
                } else {
                    "Tabriklaymiz! +50 XP qo‘shildi"
                }
            }.onFailure {
                _feedbackMessage.value = it.localizedMessage ?: "Natijani saqlashda xatolik"
            }
        }
    }

    fun startQuizSession(
        title: String,
        examType: String,
        questions: List<MathQuestion>,
        isTimedExam: Boolean,
        durationMinutes: Int
    ) {
        if (questions.isEmpty()) return
        timerJob?.cancel()
        val totalSecs = if (isTimedExam) durationMinutes * 60 else 0
        _quizSession.value = ActiveQuizSession(
            title = title,
            examType = examType,
            isTimedExam = isTimedExam,
            questions = questions,
            totalDurationSeconds = totalSecs,
            remainingSeconds = totalSecs
        )
        if (isTimedExam && totalSecs > 0) {
            timerJob = viewModelScope.launch {
                while (true) {
                    delay(1000L)
                    val current = _quizSession.value ?: break
                    if (current.isCompleted) break
                    val nextRem = current.remainingSeconds - 1
                    if (nextRem <= 0) {
                        submitActiveQuiz()
                        break
                    } else {
                        _quizSession.update { it?.copy(remainingSeconds = nextRem) }
                    }
                }
            }
        }
    }

    fun selectAnswer(questionId: String, optionIndex: Int) {
        _quizSession.update { session ->
            if (session == null || session.isCompleted) return@update session
            val updatedAnswers = session.selectedAnswers.toMutableMap().apply {
                put(questionId, optionIndex)
            }
            val updatedRevealed = if (!session.isTimedExam) {
                session.revealedExplanations + questionId
            } else {
                session.revealedExplanations
            }
            session.copy(
                selectedAnswers = updatedAnswers,
                revealedExplanations = updatedRevealed
            )
        }
    }

    fun toggleExplanation(questionId: String) {
        _quizSession.update { session ->
            if (session == null) return@update null
            val next = if (questionId in session.revealedExplanations) {
                session.revealedExplanations - questionId
            } else {
                session.revealedExplanations + questionId
            }
            session.copy(revealedExplanations = next)
        }
    }

    fun goToQuestion(index: Int) {
        _quizSession.update { session ->
            if (session == null) return@update null
            val clamped = index.coerceIn(0, (session.questions.size - 1).coerceAtLeast(0))
            session.copy(currentIndex = clamped)
        }
    }

    fun submitActiveQuiz() {
        val session = _quizSession.value ?: return
        if (session.isCompleted) return
        timerJob?.cancel()

        val correct = session.questions.count { q ->
            session.selectedAnswers[q.id] == q.correctIndex
        }
        val total = session.questions.size.coerceAtLeast(1)
        val scaled = if (session.examType == "STAGE_QUIZ") {
            ((correct * 100.0) / total).toInt()
        } else {
            CertificateExamBank.computeScaledScore(
                TargetExamTrack.fromRaw(session.examType),
                correct,
                total
            )
        }
        val elapsed = if (session.isTimedExam) {
            (session.totalDurationSeconds - session.remainingSeconds).coerceAtLeast(1)
        } else {
            total * 30
        }
        val xp = (correct * 20 + 30).coerceAtMost(500)

        _quizSession.update {
            it?.copy(
                isCompleted = true,
                correctCount = correct,
                scaledScore = scaled,
                earnedXp = xp,
                revealedExplanations = session.questions.map { q -> q.id }.toSet()
            )
        }

        viewModelScope.launch {
            repository.recordExamAttempt(
                examType = session.examType,
                examTitle = session.title,
                correctCount = correct,
                totalCount = total,
                scaledScore = scaled,
                durationSeconds = elapsed
            ).onFailure { e ->
                _feedbackMessage.value = e.localizedMessage ?: "Imtihon natijasini saqlashda xatolik"
            }
        }
    }

    fun closeQuizSession() {
        timerJob?.cancel()
        _quizSession.value = null
    }

    fun saveQuestionWithNote(question: MathQuestion, note: String) {
        viewModelScope.launch {
            repository.saveOrUpdateQuestion(question, note)
                .onSuccess { _feedbackMessage.value = "Masala saqlandi!" }
                .onFailure { _feedbackMessage.value = it.localizedMessage ?: "Saqlashda xatolik" }
        }
    }

    fun deleteSavedQuestion(questionId: String) {
        viewModelScope.launch {
            repository.deleteSavedQuestion(questionId)
                .onSuccess { _feedbackMessage.value = "Saqlangan masala o‘chirildi" }
                .onFailure { _feedbackMessage.value = it.localizedMessage ?: "O‘chirishda xatolik" }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    private companion object {
        const val TAG = "MathMasterVM"
        const val STOP_TIMEOUT_MILLIS = 5000L
    }
}
