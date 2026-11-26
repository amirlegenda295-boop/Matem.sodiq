package com.example.data

import android.content.Context
import com.example.R
import com.example.model.ExamAttempt
import com.example.model.LessonProgress
import com.example.model.MathQuestion
import com.example.model.SavedQuestion
import com.example.model.TargetExamTrack
import com.example.model.UserProfile
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class MathRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth = Firebase.auth
) {
    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private fun requireUserId(): String {
        return auth.currentUser?.uid?.takeIf { it.isNotBlank() }
            ?: throw IllegalStateException("Google hisobingiz bilan tizimga kiring.")
    }

    fun observeUserProfile(userId: String): Flow<UserProfile?> = flow {
        require(userId.isNotBlank()) { "User ID bo‘sh bo‘lmasligi kerak" }
        val path = "users/$userId"
        emitAll(
            db.collection("users").document(userId)
                .snapshots()
                .map { snapshot ->
                    if (!snapshot.exists()) null
                    else snapshot.toObject(
                        UserProfile::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && auth.currentUser?.uid == userId) {
                        handleFirestoreError(exceptionToLog, OperationType.GET, path)
                    }
                    throw error
                }
        )
    }

    fun observeLessonProgress(userId: String): Flow<List<LessonProgress>> = flow {
        require(userId.isNotBlank()) { "User ID bo‘sh bo‘lmasligi kerak" }
        val path = "users/$userId/lessonProgress"
        emitAll(
            db.collection("users").document(userId)
                .collection("lessonProgress")
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(
                        LessonProgress::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && auth.currentUser?.uid == userId) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    fun observeExamAttempts(userId: String): Flow<List<ExamAttempt>> = flow {
        require(userId.isNotBlank()) { "User ID bo‘sh bo‘lmasligi kerak" }
        val path = "users/$userId/examAttempts"
        emitAll(
            db.collection("users").document(userId)
                .collection("examAttempts")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(
                        ExamAttempt::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && auth.currentUser?.uid == userId) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    fun observeSavedQuestions(userId: String): Flow<List<SavedQuestion>> = flow {
        require(userId.isNotBlank()) { "User ID bo‘sh bo‘lmasligi kerak" }
        val path = "users/$userId/savedQuestions"
        emitAll(
            db.collection("users").document(userId)
                .collection("savedQuestions")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(
                        SavedQuestion::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null && auth.currentUser?.uid == userId) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    suspend fun ensureUserProfile(displayNameFallback: String): Result<Unit> {
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}"
        return try {
            val uid = requireUserId()
            val docRef = db.collection("users").document(uid)
            docPath = docRef.path
            val snapshot = docRef.get().await()
            if (!snapshot.exists()) {
                val safeName = displayNameFallback.trim().ifBlank { "Matematachi" }.take(80)
                val payload = mapOf(
                    "userId" to uid,
                    "displayName" to safeName,
                    "targetExam" to TargetExamTrack.SAT.name,
                    "totalXp" to 0L,
                    "streakDays" to 1L,
                    "lessonsCompletedCount" to 0L,
                    "quizzesCompletedCount" to 0L,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docPath)
            Result.failure(e)
        }
    }

    suspend fun updateTargetExam(track: TargetExamTrack): Result<Unit> {
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}"
        return try {
            val uid = requireUserId()
            val docRef = db.collection("users").document(uid)
            docPath = docRef.path
            docRef.update(
                mapOf(
                    "targetExam" to track.name,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, docPath)
            Result.failure(e)
        }
    }

    suspend fun markLessonMastered(
        lessonId: String,
        stageId: String,
        masteryPercent: Int,
        alreadyCompleted: Boolean
    ): Result<Unit> {
        val safeLessonId = lessonId.trim().take(64)
        val safeStageId = stageId.trim().take(64)
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}/lessonProgress/$safeLessonId"
        return try {
            val uid = requireUserId()
            require(safeLessonId.matches(Regex("^[a-zA-Z0-9_\\-]+$"))) { "Noto‘g‘ri dars ID" }
            require(safeStageId.isNotBlank()) { "Bosqich ID bo‘sh bo‘lmasligi kerak" }
            val clampedMastery = masteryPercent.coerceIn(0, 100).toLong()

            val progressRef = db.collection("users").document(uid)
                .collection("lessonProgress").document(safeLessonId)
            docPath = progressRef.path

            val existing = progressRef.get().await()
            if (existing.exists()) {
                progressRef.update(
                    mapOf(
                        "completed" to true,
                        "masteryPercent" to clampedMastery,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            } else {
                progressRef.set(
                    mapOf(
                        "userId" to uid,
                        "lessonId" to safeLessonId,
                        "stageId" to safeStageId,
                        "completed" to true,
                        "masteryPercent" to clampedMastery,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }

            if (!alreadyCompleted) {
                db.collection("users").document(uid).update(
                    mapOf(
                        "totalXp" to FieldValue.increment(50L),
                        "lessonsCompletedCount" to FieldValue.increment(1L),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docPath)
            Result.failure(e)
        }
    }

    suspend fun recordExamAttempt(
        examType: String,
        examTitle: String,
        correctCount: Int,
        totalCount: Int,
        scaledScore: Int,
        durationSeconds: Int
    ): Result<Unit> {
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}/examAttempts"
        return try {
            val uid = requireUserId()
            val safeTotal = totalCount.coerceIn(1, 200)
            val safeCorrect = correctCount.coerceIn(0, safeTotal)
            val safeScaled = scaledScore.coerceIn(0, 1600)
            val safeDuration = durationSeconds.coerceIn(0, 36000)
            val safeTitle = examTitle.trim().ifBlank { "Matematika testi" }.take(100)
            val validExamTypes = setOf("SAT", "ACT", "GRE", "GMAT", "AP_CALCULUS", "MILLIY", "STAGE_QUIZ")
            val safeType = if (examType in validExamTypes) examType else "STAGE_QUIZ"

            val attemptRef = db.collection("users").document(uid)
                .collection("examAttempts").document()
            docPath = attemptRef.path

            val payload = mapOf(
                "userId" to uid,
                "examType" to safeType,
                "examTitle" to safeTitle,
                "correctCount" to safeCorrect.toLong(),
                "totalCount" to safeTotal.toLong(),
                "scaledScore" to safeScaled.toLong(),
                "durationSeconds" to safeDuration.toLong(),
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            attemptRef.set(payload).await()

            val earnedXp = (safeCorrect * 20L + 30L).coerceAtMost(500L)
            db.collection("users").document(uid).update(
                mapOf(
                    "totalXp" to FieldValue.increment(earnedXp),
                    "quizzesCompletedCount" to FieldValue.increment(1L),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, docPath)
            Result.failure(e)
        }
    }

    suspend fun saveOrUpdateQuestion(
        question: MathQuestion,
        note: String
    ): Result<Unit> {
        val safeQuestionId = question.id.trim().take(64)
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}/savedQuestions/$safeQuestionId"
        return try {
            val uid = requireUserId()
            require(safeQuestionId.matches(Regex("^[a-zA-Z0-9_\\-]+$"))) { "Noto‘g‘ri savol ID" }
            val safeNote = note.trim().take(500)
            val docRef = db.collection("users").document(uid)
                .collection("savedQuestions").document(safeQuestionId)
            docPath = docRef.path

            val existing = docRef.get().await()
            if (existing.exists()) {
                docRef.update(
                    mapOf(
                        "note" to safeNote,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            } else {
                docRef.set(
                    mapOf(
                        "userId" to uid,
                        "questionId" to safeQuestionId,
                        "topicTitle" to question.topicTitle.trim().ifBlank { "Matematika" }.take(100),
                        "examTag" to question.examTag.trim().ifBlank { "SAT" }.take(40),
                        "prompt" to question.prompt.trim().ifBlank { "Masala" }.take(1000),
                        "note" to safeNote,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docPath)
            Result.failure(e)
        }
    }

    suspend fun deleteSavedQuestion(questionId: String): Result<Unit> {
        val safeId = questionId.trim().take(64)
        var docPath = "users/${auth.currentUser?.uid ?: "unauthenticated"}/savedQuestions/$safeId"
        return try {
            val uid = requireUserId()
            require(safeId.matches(Regex("^[a-zA-Z0-9_\\-]+$"))) { "Noto‘g‘ri savol ID" }
            val docRef = db.collection("users").document(uid)
                .collection("savedQuestions").document(safeId)
            docPath = docRef.path
            docRef.delete().await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docPath)
            Result.failure(e)
        }
    }
}
