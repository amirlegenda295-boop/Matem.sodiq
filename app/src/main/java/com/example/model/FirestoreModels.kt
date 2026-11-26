package com.example.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

enum class TargetExamTrack(val titleUz: String, val scaleSummary: String) {
    SAT("Digital SAT Math", "200–800 ball"),
    ACT("ACT Math", "1–36 ball"),
    GRE("GRE Quantitative", "130–170 ball"),
    GMAT("GMAT Focus Quant", "60–90 ball"),
    AP_CALCULUS("AP Calculus AB/BC", "1–5 ball"),
    MILLIY("Milliy Sertifikat / DTM", "A+ / 100%");

    companion object {
        fun fromRaw(raw: String?): TargetExamTrack {
            return entries.firstOrNull { it.name == raw } ?: SAT
        }
    }
}

@Keep
@IgnoreExtraProperties
data class UserProfile(
    @DocumentId val id: String = "",
    val userId: String = "",
    val displayName: String = "Matematachi",
    val targetExam: String = TargetExamTrack.SAT.name,
    val totalXp: Long = 0L,
    val streakDays: Long = 1L,
    val lessonsCompletedCount: Long = 0L,
    val quizzesCompletedCount: Long = 0L,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
) {
    val targetExamEnum: TargetExamTrack
        get() = TargetExamTrack.fromRaw(targetExam)
}

@Keep
@IgnoreExtraProperties
data class LessonProgress(
    @DocumentId val id: String = "",
    val userId: String = "",
    val lessonId: String = "",
    val stageId: String = "",
    val completed: Boolean = false,
    val masteryPercent: Long = 0L,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class ExamAttempt(
    @DocumentId val id: String = "",
    val userId: String = "",
    val examType: String = TargetExamTrack.SAT.name,
    val examTitle: String = "",
    val correctCount: Long = 0L,
    val totalCount: Long = 1L,
    val scaledScore: Long = 0L,
    val durationSeconds: Long = 0L,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class SavedQuestion(
    @DocumentId val id: String = "",
    val userId: String = "",
    val questionId: String = "",
    val topicTitle: String = "",
    val examTag: String = "",
    val prompt: String = "",
    val note: String = "",
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)
