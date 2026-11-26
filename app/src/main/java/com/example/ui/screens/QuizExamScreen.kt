package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Functions
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.MathQuestion
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MathFormulaStyle
import com.example.ui.viewmodel.ActiveQuizSession
import java.util.Locale
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizExamScreen(
    session: ActiveQuizSession,
    savedQuestionIds: Set<String>,
    onSelectOption: (questionId: String, optionIndex: Int) -> Unit,
    onToggleExplanation: (questionId: String) -> Unit,
    onGoToQuestion: (Int) -> Unit,
    onSubmit: () -> Unit,
    onSaveQuestion: (MathQuestion) -> Unit,
    onOpenFormulas: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onExit() }

    var showCalculator by remember { mutableStateOf(false) }

    if (showCalculator) {
        QuickCalculatorDialog(onDismiss = { showCalculator = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = session.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                        Text(
                            text = if (session.isCompleted) {
                                "Natija: ${session.correctCount}/${session.questions.size}"
                            } else {
                                "${session.currentIndex + 1} / ${session.questions.size}-savol"
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onExit,
                        modifier = Modifier.testTag("quiz_exit_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Chiqish"
                        )
                    }
                },
                actions = {
                    if (session.isTimedExam && !session.isCompleted) {
                        val mins = session.remainingSeconds / 60
                        val secs = session.remainingSeconds % 60
                        val timeText = String.format(Locale.US, "%02d:%02d", mins, secs)
                        val isUrgent = session.remainingSeconds < 60
                        Surface(
                            color = if (isUrgent) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape,
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Timer,
                                    contentDescription = "Qolgan vaqt",
                                    tint = if (isUrgent) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = timeText,
                                    fontFamily = JetBrainsMonoFontFamily,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (isUrgent) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                    IconButton(
                        onClick = { showCalculator = true },
                        modifier = Modifier.testTag("quiz_calculator_button")
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Calculate,
                            contentDescription = "Tezkor kalkulyator"
                        )
                    }
                    IconButton(
                        onClick = onOpenFormulas,
                        modifier = Modifier.testTag("quiz_formulas_button")
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Functions,
                            contentDescription = "Formulalar"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            if (session.isCompleted) {
                ExamResultsView(
                    session = session,
                    savedQuestionIds = savedQuestionIds,
                    onSaveQuestion = onSaveQuestion,
                    onExit = onExit
                )
            } else {
                ActiveQuestionView(
                    session = session,
                    savedQuestionIds = savedQuestionIds,
                    onSelectOption = onSelectOption,
                    onToggleExplanation = onToggleExplanation,
                    onGoToQuestion = onGoToQuestion,
                    onSubmit = onSubmit,
                    onSaveQuestion = onSaveQuestion
                )
            }
        }
    }
}

@Composable
private fun ActiveQuestionView(
    session: ActiveQuizSession,
    savedQuestionIds: Set<String>,
    onSelectOption: (String, Int) -> Unit,
    onToggleExplanation: (String) -> Unit,
    onGoToQuestion: (Int) -> Unit,
    onSubmit: () -> Unit,
    onSaveQuestion: (MathQuestion) -> Unit
) {
    val question = session.questions[session.currentIndex]
    val selectedIndex = session.selectedAnswers[question.id]
    val showExplanation = question.id in session.revealedExplanations
    val isSaved = question.id in savedQuestionIds
    val progress = (session.selectedAnswers.size.toFloat() / session.questions.size.coerceAtLeast(1))

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 720.dp)
    ) {
        item {
            LinearProgressIndicator(
                progress = { progress },
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                session.questions.forEachIndexed { idx, q ->
                    val answered = q.id in session.selectedAnswers
                    val current = idx == session.currentIndex
                    Surface(
                        color = when {
                            current -> MaterialTheme.colorScheme.primary
                            answered -> MaterialTheme.colorScheme.secondaryContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable { onGoToQuestion(idx) }
                            .testTag("quiz_stepper_$idx")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "${idx + 1}",
                                style = MaterialTheme.typography.labelLarge,
                                color = when {
                                    current -> MaterialTheme.colorScheme.onPrimary
                                    answered -> MaterialTheme.colorScheme.onSecondaryContainer
                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        }
                    }
                }
            }
        }

        item {
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = CircleShape
                            ) {
                                Text(
                                    text = question.examTag,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = CircleShape
                            ) {
                                Text(
                                    text = question.topicTitle,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                        IconButton(
                            onClick = { onSaveQuestion(question) },
                            modifier = Modifier.testTag("quiz_save_question_button")
                        ) {
                            Icon(
                                imageVector = if (isSaved) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                                contentDescription = if (isSaved) stringResource(R.string.quiz_saved_question) else stringResource(R.string.quiz_save_question),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = question.prompt,
                        style = MathFormulaStyle,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    question.options.forEachIndexed { idx, option ->
                        val isSelected = selectedIndex == idx
                        val showPracticeFeedback = !session.isTimedExam && selectedIndex != null
                        val isCorrect = idx == question.correctIndex

                        val bg = when {
                            showPracticeFeedback && isCorrect -> MaterialTheme.colorScheme.secondaryContainer
                            showPracticeFeedback && isSelected -> MaterialTheme.colorScheme.errorContainer
                            isSelected -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }

                        Surface(
                            color = bg,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { onSelectOption(question.id, idx) }
                                .testTag("quiz_option_$idx")
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${('A' + idx)})  $option",
                                    style = MathFormulaStyle,
                                    modifier = Modifier.weight(1f)
                                )
                                if (showPracticeFeedback && isCorrect) {
                                    Icon(
                                        imageVector = Icons.Rounded.CheckCircle,
                                        contentDescription = "To‘g‘ri",
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                } else if (showPracticeFeedback && isSelected) {
                                    Icon(
                                        imageVector = Icons.Rounded.Cancel,
                                        contentDescription = "Noto‘g‘ri",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }

                    if (!session.isTimedExam && selectedIndex != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = { onToggleExplanation(question.id) },
                            modifier = Modifier.testTag("quiz_toggle_explanation_button")
                        ) {
                            Text(if (showExplanation) "Yechimni yashirish" else stringResource(R.string.quiz_explanation))
                        }
                    }

                    AnimatedVisibility(visible = showExplanation && !session.isTimedExam) {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Qadam-baqadam yechim:",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                question.explanationSteps.forEach { step ->
                                    Text(
                                        text = "• $step",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (session.currentIndex > 0) {
                    OutlinedButton(
                        onClick = { onGoToQuestion(session.currentIndex - 1) },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("quiz_prev_button")
                    ) {
                        Text(stringResource(R.string.quiz_previous))
                    }
                }
                if (session.currentIndex < session.questions.lastIndex) {
                    FilledTonalButton(
                        onClick = { onGoToQuestion(session.currentIndex + 1) },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("quiz_next_button")
                    ) {
                        Text(stringResource(R.string.quiz_next))
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null)
                    }
                } else {
                    Button(
                        onClick = onSubmit,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("quiz_submit_button")
                    ) {
                        Text(stringResource(R.string.quiz_submit))
                    }
                }
            }
            if (session.currentIndex < session.questions.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("quiz_early_submit_button")
                ) {
                    Text("Testni yakunlash (${session.selectedAnswers.size}/${session.questions.size})")
                }
            }
        }
    }
}

@Composable
private fun ExamResultsView(
    session: ActiveQuizSession,
    savedQuestionIds: Set<String>,
    onSaveQuestion: (MathQuestion) -> Unit,
    onExit: () -> Unit
) {
    val accuracy = ((session.correctCount * 100.0) / session.questions.size.coerceAtLeast(1)).toInt()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 720.dp)
    ) {
        item {
            Card(
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.EmojiEvents,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(44.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = session.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${session.scaledScore}",
                        fontFamily = JetBrainsMonoFontFamily,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "To‘g‘ri javoblar: ${session.correctCount}/${session.questions.size} ($accuracy%) • +${session.earnedXp} XP",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        itemsIndexed(session.questions, key = { _, q -> q.id }) { idx, q ->
            val chosen = session.selectedAnswers[q.id]
            val isCorrect = chosen == q.correctIndex
            val isSaved = q.id in savedQuestionIds

            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Rounded.CheckCircle else Icons.Rounded.Cancel,
                                contentDescription = null,
                                tint = if (isCorrect) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${idx + 1}-savol • ${q.topicTitle}",
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        IconButton(onClick = { onSaveQuestion(q) }) {
                            Icon(
                                imageVector = if (isSaved) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                                contentDescription = "Saqlash",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = q.prompt, style = MathFormulaStyle)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sizning javobingiz: ${chosen?.let { "${('A' + it)}) ${q.options[it]}" } ?: "Belgilanmagan"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isCorrect) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "To‘g‘ri javob: ${('A' + q.correctIndex)}) ${q.options[q.correctIndex]}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            q.explanationSteps.forEach { step ->
                                Text(
                                    text = "• $step",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Button(
                onClick = onExit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("quiz_done_exit_button")
            ) {
                Text("Boshqaruv paneliga qaytish")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun QuickCalculatorDialog(onDismiss: () -> Unit) {
    var display by remember { mutableStateOf("0") }
    var storedValue by remember { mutableStateOf<Double?>(null) }
    var pendingOp by remember { mutableStateOf<String?>(null) }
    var resetOnNext by remember { mutableStateOf(false) }

    fun applyOp(a: Double, b: Double, op: String): Double = when (op) {
        "+" -> a + b
        "−" -> a - b
        "×" -> a * b
        "÷" -> if (b != 0.0) a / b else 0.0
        else -> b
    }

    fun formatNum(value: Double): String {
        return if (value % 1.0 == 0.0 && kotlin.math.abs(value) < 1e12) {
            value.toLong().toString()
        } else {
            String.format(Locale.US, "%.4f", value).trimEnd('0').trimEnd('.')
        }
    }

    fun onDigit(d: String) {
        if (resetOnNext || display == "0") {
            display = if (d == ".") "0." else d
            resetOnNext = false
        } else {
            if (d == "." && "." in display) return
            display += d
        }
    }

    fun onOperator(op: String) {
        val current = display.toDoubleOrNull() ?: 0.0
        if (storedValue != null && pendingOp != null && !resetOnNext) {
            val res = applyOp(storedValue!!, current, pendingOp!!)
            storedValue = res
            display = formatNum(res)
        } else {
            storedValue = current
        }
        pendingOp = op
        resetOnNext = true
    }

    fun onEquals() {
        val current = display.toDoubleOrNull() ?: 0.0
        if (storedValue != null && pendingOp != null) {
            val res = applyOp(storedValue!!, current, pendingOp!!)
            display = formatNum(res)
            storedValue = null
            pendingOp = null
            resetOnNext = true
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tezkor Kalkulyator") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = display,
                        fontFamily = JetBrainsMonoFontFamily,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(14.dp)
                    )
                }
                val rows = listOf(
                    listOf("C", "√", "x²", "÷"),
                    listOf("7", "8", "9", "×"),
                    listOf("4", "5", "6", "−"),
                    listOf("1", "2", "3", "+"),
                    listOf("0", ".", "=", "±")
                )
                rows.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        row.forEach { key ->
                            FilledTonalButton(
                                onClick = {
                                    when (key) {
                                        "C" -> {
                                            display = "0"
                                            storedValue = null
                                            pendingOp = null
                                            resetOnNext = false
                                        }
                                        "√" -> {
                                            val v = display.toDoubleOrNull() ?: 0.0
                                            if (v >= 0) display = formatNum(sqrt(v))
                                            resetOnNext = true
                                        }
                                        "x²" -> {
                                            val v = display.toDoubleOrNull() ?: 0.0
                                            display = formatNum(v * v)
                                            resetOnNext = true
                                        }
                                        "±" -> {
                                            val v = display.toDoubleOrNull() ?: 0.0
                                            display = formatNum(-v)
                                        }
                                        "+", "−", "×", "÷" -> onOperator(key)
                                        "=" -> onEquals()
                                        else -> onDigit(key)
                                    }
                                },
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                            ) {
                                Text(text = key, fontFamily = JetBrainsMonoFontFamily)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Yopish")
            }
        }
    )
}
