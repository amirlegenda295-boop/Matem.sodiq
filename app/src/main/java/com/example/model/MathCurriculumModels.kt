package com.example.model

enum class MasteryLevel(val labelUz: String) {
    BEGINNER("Boshlang‘ich (Noldan)"),
    FOUNDATION("Poydevor"),
    INTERMEDIATE("O‘rta daraja"),
    ADVANCED("Yuqori daraja"),
    MASTER("Master")
}

data class WorkedExample(
    val problem: String,
    val steps: List<String>,
    val finalAnswer: String
)

data class MathLesson(
    val id: String,
    val stageId: String,
    val title: String,
    val subtitle: String,
    val estimatedMinutes: Int,
    val theoryParagraphs: List<String>,
    val keyFormulas: List<String>,
    val workedExamples: List<WorkedExample>,
    val checkQuestion: MathQuestion
)

data class CurriculumStage(
    val id: String,
    val number: Int,
    val title: String,
    val subtitle: String,
    val level: MasteryLevel,
    val lessons: List<MathLesson>,
    val stageQuizQuestions: List<MathQuestion>
)

data class MathQuestion(
    val id: String,
    val topicTitle: String,
    val examTag: String,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanationSteps: List<String>,
    val difficultyLabel: String = "O‘rta"
)

data class CertificateTrack(
    val id: String,
    val examType: TargetExamTrack,
    val title: String,
    val subtitle: String,
    val scoreRangeLabel: String,
    val durationMinutes: Int,
    val descriptionUz: String,
    val keyTopics: List<String>,
    val examTips: List<String>,
    val questions: List<MathQuestion>
)

data class FormulaEntry(
    val name: String,
    val formula: String,
    val note: String
)

data class FormulaCategory(
    val id: String,
    val title: String,
    val formulas: List<FormulaEntry>
)
