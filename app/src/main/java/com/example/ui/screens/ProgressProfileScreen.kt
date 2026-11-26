package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Functions
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.ExamAttempt
import com.example.model.MathQuestion
import com.example.model.SavedQuestion
import com.example.model.TargetExamTrack
import com.example.model.UserProfile
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.MathFormulaStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProgressProfileScreen(
    profile: UserProfile?,
    userEmail: String?,
    examAttempts: List<ExamAttempt>,
    savedQuestions: List<SavedQuestion>,
    onSelectTargetExam: (TargetExamTrack) -> Unit,
    onUpdateSavedNote: (SavedQuestion, String) -> Unit,
    onDeleteSavedQuestion: (String) -> Unit,
    onOpenFormulas: () -> Unit,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    var editingQuestion by remember { mutableStateOf<SavedQuestion?>(null) }
    var noteDraft by remember { mutableStateOf("") }

    if (editingQuestion != null) {
        val target = editingQuestion!!
        AlertDialog(
            onDismissRequest = { editingQuestion = null },
            title = { Text("Shaxsiy eslatma") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = target.prompt,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    OutlinedTextField(
                        value = noteDraft,
                        onValueChange = { noteDraft = it.take(500) },
                        label = { Text("Yechim bo‘yicha eslatma") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onUpdateSavedNote(target, noteDraft)
                        editingQuestion = null
                    }
                ) {
                    Text("Saqlash")
                }
            },
            dismissButton = {
                TextButton(onClick = { editingQuestion = null }) {
                    Text("Bekor qilish")
                }
            }
        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp)
        ) {
            // User Header Card
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = CircleShape,
                                    modifier = Modifier.size(50.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Rounded.Person,
                                            contentDescription = "Foydalanuvchi profili",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = profile?.displayName ?: "Matematachi",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = userEmail ?: "Google hisobi ulangan",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            OutlinedButton(
                                onClick = onSignOut,
                                modifier = Modifier
                                    .height(48.dp)
                                    .testTag("profile_sign_out_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(stringResource(R.string.profile_sign_out))
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(R.string.profile_target_exam),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val currentTarget = profile?.targetExamEnum ?: TargetExamTrack.SAT
                            TargetExamTrack.entries.forEach { track ->
                                FilterChip(
                                    selected = track == currentTarget,
                                    onClick = { onSelectTargetExam(track) },
                                    label = { Text(track.titleUz) },
                                    modifier = Modifier.testTag("target_exam_chip_${track.name}")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = onOpenFormulas,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("profile_formulas_button")
                        ) {
                            Icon(Icons.Rounded.Functions, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(stringResource(R.string.home_formula_bank))
                        }
                    }
                }
            }

            // Accuracy Trend Chart Card
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.TrendingUp,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Imtihonlar Dinamikasi (%)",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        if (examAttempts.isEmpty()) {
                            Text(
                                text = "Hali birorta sinov testi topshirilmadi. Sertifikatlar bo‘limidan test boshlab natijangizni kuzating!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            val points = examAttempts.take(8).reversed().map {
                                ((it.correctCount.toFloat() / it.totalCount.coerceAtLeast(1L).toFloat()) * 100f)
                                    .coerceIn(0f, 100f)
                            }
                            AccuracyTrendChart(
                                percentages = points,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                            )
                        }
                    }
                }
            }

            // Saved Questions Section
            item {
                Text(
                    text = "${stringResource(R.string.profile_saved_problems)} (${savedQuestions.size})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (savedQuestions.isEmpty()) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Qiyin tuyulgan masalalarni test paytida saqlab qo‘ying — ular shu yerda ko‘rinadi.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                items(savedQuestions, key = { it.questionId }) { item ->
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = CircleShape
                                ) {
                                    Text(
                                        text = "${item.examTag} • ${item.topicTitle}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                                Row {
                                    IconButton(
                                        onClick = {
                                            editingQuestion = item
                                            noteDraft = item.note
                                        },
                                        modifier = Modifier.testTag("edit_saved_${item.questionId}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.EditNote,
                                            contentDescription = "Eslatmani tahrirlash"
                                        )
                                    }
                                    IconButton(
                                        onClick = { onDeleteSavedQuestion(item.questionId) },
                                        modifier = Modifier.testTag("delete_saved_${item.questionId}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.DeleteOutline,
                                            contentDescription = "O‘chirish",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = item.prompt, style = MathFormulaStyle)
                            if (item.note.isNotBlank()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Surface(
                                    color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.45f),
                                    shape = MaterialTheme.shapes.small,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Eslatma: ${item.note}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Exam History Section
            item {
                Text(
                    text = "${stringResource(R.string.profile_exam_history)} (${examAttempts.size})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            items(examAttempts.take(15), key = { it.id.ifBlank { it.examTitle + it.durationSeconds } }) { attempt ->
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = attempt.examTitle,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = "${attempt.correctCount}/${attempt.totalCount} to‘g‘ri • ${attempt.durationSeconds / 60} daq ${attempt.durationSeconds % 60} son",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "${attempt.scaledScore}",
                                fontFamily = JetBrainsMonoFontFamily,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AccuracyTrendChart(
    percentages: List<Float>,
    modifier: Modifier = Modifier
) {
    val lineColor = MaterialTheme.colorScheme.primary
    val pointColor = MaterialTheme.colorScheme.secondary
    val gridColor = MaterialTheme.colorScheme.outlineVariant

    Canvas(modifier = modifier) {
        val paddingX = 20f
        val paddingY = 18f
        val chartW = (size.width - paddingX * 2).coerceAtLeast(1f)
        val chartH = (size.height - paddingY * 2).coerceAtLeast(1f)

        // Draw subtle horizontal guide lines (0%, 50%, 100%)
        listOf(0f, 0.5f, 1f).forEach { frac ->
            val y = paddingY + chartH * (1f - frac)
            drawLine(
                color = gridColor,
                start = Offset(paddingX, y),
                end = Offset(paddingX + chartW, y),
                strokeWidth = 2f
            )
        }

        val coords = if (percentages.size == 1) {
            listOf(
                Offset(
                    paddingX + chartW / 2f,
                    paddingY + chartH * (1f - percentages.first() / 100f)
                )
            )
        } else {
            percentages.mapIndexed { idx, pct ->
                val x = paddingX + (idx.toFloat() / (percentages.size - 1)) * chartW
                val y = paddingY + chartH * (1f - pct / 100f)
                Offset(x, y)
            }
        }

        if (coords.size >= 2) {
            val path = Path().apply {
                moveTo(coords.first().x, coords.first().y)
                for (i in 1 until coords.size) {
                    lineTo(coords[i].x, coords[i].y)
                }
            }
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = 6f, cap = StrokeCap.Round)
            )
        }

        coords.forEach { pt ->
            drawCircle(
                color = pointColor,
                radius = 9f,
                center = pt
            )
        }
    }
}
