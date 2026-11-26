package com.example.data

import com.example.model.CurriculumStage
import com.example.model.MasteryLevel
import com.example.model.MathLesson
import com.example.model.MathQuestion
import com.example.model.WorkedExample

object CurriculumDataPart1 {

    val stages: List<CurriculumStage> = listOf(
        // ==================== STAGE 1: ASOSLAR ====================
        CurriculumStage(
            id = "stage_1",
            number = 1,
            title = "Asoslar: Noldan Boshlash",
            subtitle = "Arifmetika, kasrlar, foizlar, nisbat va daraja xossalari",
            level = MasteryLevel.BEGINNER,
            lessons = listOf(
                MathLesson(
                    id = "s1_l1",
                    stageId = "stage_1",
                    title = "Amallar tartibi va son xossalari",
                    subtitle = "PEMDAS qoidasi, manfiy sonlar va bo‘linish belgilari",
                    estimatedMinutes = 8,
                    theoryParagraphs = listOf(
                        "Matematikada murakkab ifodalarni hisoblash qat’iy ketma-ketlikka (PEMDAS) bo‘ysunadi: avval qavslar ichi, so‘ng daraja va ildizlar, keyin chapdan o‘ngga qarab ko‘paytirish/bo‘lish va nihoyat qo‘shish/ayirish bajariladi.",
                        "Bo‘linish belgilari tezkor hisoblashda va SAT hamda DTM testlarida vaqt tejashga yordam beradi: son 3 ga bo‘linishi uchun raqamlari yig‘indisi 3 ga bo‘linishi, 9 ga bo‘linishi uchun raqamlar yig‘indisi 9 ga bo‘linishi kerak. Oxirgi ikki raqami 4 ga bo‘linsa, son 4 ga bo‘linadi."
                    ),
                    keyFormulas = listOf(
                        "PEMDAS: Qavs → Daraja → × / ÷ → + / −",
                        "(-a) × (-b) = +ab,   (-a) × (+b) = -ab",
                        "a⁰ = 1 (a ≠ 0)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Hisoblang: 24 ÷ 3 × 2 + (5 - 2)²",
                            steps = listOf(
                                "1-qadam (Qavs): 5 - 2 = 3",
                                "2-qadam (Daraja): 3² = 9",
                                "3-qadam (Chapdan o‘ngga ÷ va ×): 24 ÷ 3 = 8, so‘ng 8 × 2 = 16",
                                "4-qadam (Qo‘shish): 16 + 9 = 25"
                            ),
                            finalAnswer = "25"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s1_l1_q1",
                        topicTitle = "Amallar tartibi",
                        examTag = "Asoslar",
                        prompt = "Ifodaning qiymatini toping: 36 ÷ 6 × 3 - 2³",
                        options = listOf("10", "-6", "14", "8"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "2³ = 8.",
                            "Chapdan o‘ngga: 36 ÷ 6 = 6, so‘ng 6 × 3 = 18.",
                            "18 - 8 = 10."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s1_l2",
                    stageId = "stage_1",
                    title = "Kasrlar va Foizlar (%)",
                    subtitle = "Oddiy va o‘nli kasrlar, foizli o‘sish va chegirmalar",
                    estimatedMinutes = 10,
                    theoryParagraphs = listOf(
                        "Kasrlarni qo‘shish va ayirish uchun umumiy maxraj topiladi. Ko‘paytirishda surat suratga, maxraj maxrajga ko‘paytiriladi. Bo‘lishda esa ikkinchi kasr teskarisiga aylantirilib ko‘paytiriladi.",
                        "Foiz — sonning yuzdan bir ulushi (1% = 0.01). SAT va GMAT imtihonlarida ketma-ket foiz o‘zgarishlari ko‘p uchraydi: narx p% oshsa, yangi narx Asl × (1 + p/100) ga teng bo‘ladi; q% kamaysa, Asl × (1 - q/100) bo‘ladi."
                    ),
                    keyFormulas = listOf(
                        "a/b + c/d = (ad + bc) / bd",
                        "(a/b) ÷ (c/d) = (a/b) × (d/c)",
                        "Foiz o‘zgarishi = [(Yangi - Asl) / Asl] × 100%"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Mahsulot narxi avval 20% oshirildi, keyin yangi narx 20% tushirildi. Dastlabki narx necha foizga o‘zgardi?",
                            steps = listOf(
                                "Dastlabki narxni 100 deb olaylik.",
                                "20% oshganda: 100 × 1.20 = 120.",
                                "120 dan 20% tushganda: 120 × 0.80 = 96.",
                                "O‘zgarish: 96 - 100 = -4, ya’ni 4% kamaydi."
                            ),
                            finalAnswer = "4% kamaydi"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s1_l2_q1",
                        topicTitle = "Foizlar",
                        examTag = "SAT",
                        prompt = "Kurtka 25% chegirma bilan 150 000 so‘mga sotildi. Kurtkaning asl narxi qancha bo‘lgan?",
                        options = listOf("187 500 so‘m", "200 000 so‘m", "190 000 so‘m", "225 000 so‘m"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "25% chegirma qolgan 75% narxni bildiradi: 0.75 × x = 150 000.",
                            "x = 150 000 ÷ 0.75 = 200 000 so‘m."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                ),
                MathLesson(
                    id = "s1_l3",
                    stageId = "stage_1",
                    title = "Nisbat, Proporsiya va Darajalar",
                    subtitle = "To‘g‘ri va teskari proporsional bog‘liqlik, daraja qonunlari",
                    estimatedMinutes = 9,
                    theoryParagraphs = listOf(
                        "Proporsiya a/b = c/d ko‘rinishidagi tenglik bo‘lib, asosiy xossasi: a × d = b × c (o‘rta hadlar ko‘paytmasi chetki hadlar ko‘paytmasiga teng).",
                        "Daraja xossalari butun Algebra va Calculus poydevoridir: bir xil asosli darajalarni ko‘paytirganda ko‘rsatkichlar qo‘shiladi, bo‘lganda ayriladi, darajani darajaga ko‘targanda esa ko‘paytiriladi."
                    ),
                    keyFormulas = listOf(
                        "aᵐ × aⁿ = aᵐ⁺ⁿ   |   aᵐ ÷ aⁿ = aᵐ⁻ⁿ",
                        "(aᵐ)ⁿ = aᵐⁿ   |   a⁻ⁿ = 1 / aⁿ",
                        "a^(m/n) = ⁿ√(aᵐ)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Soddalashtiring: (2³ × 4²) / 2⁵",
                            steps = listOf(
                                "4 ni 2² shaklida yozamiz: 4² = (2²)² = 2⁴.",
                                "Surat: 2³ × 2⁴ = 2⁷.",
                                "Bo‘lish: 2⁷ / 2⁵ = 2⁷⁻⁵ = 2² = 4."
                            ),
                            finalAnswer = "4"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s1_l3_q1",
                        topicTitle = "Daraja xossalari",
                        examTag = "Asoslar",
                        prompt = "Agar 3ˣ = 5 bo‘lsa, 9ˣ⁺¹ nimaga teng?",
                        options = listOf("45", "225", "75", "150"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "9ˣ⁺¹ = (3²)ˣ⁺¹ = 3²ˣ⁺² = (3ˣ)² × 3².",
                            "3ˣ = 5 ni qo‘yamiz: 5² × 9 = 25 × 9 = 225."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s1_q1",
                    topicTitle = "Bo‘linish va qoldiq",
                    examTag = "Milliy Sertifikat",
                    prompt = "472k uch xonali son emas, to‘rt xonali 472k soni 9 ga qoldiqsiz bo‘linishi uchun k raqami nechaga teng bo‘lishi kerak?",
                    options = listOf("2", "5", "7", "8"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Raqamlar yig‘indisi: 4 + 7 + 2 + k = 13 + k.",
                        "13 dan katta 9 ga karrali eng yaqin son 18.",
                        "13 + k = 18 → k = 5."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s1_q2",
                    topicTitle = "Kasrlar",
                    examTag = "Asoslar",
                    prompt = "(3/4 + 1/6) ÷ (11/12) ifodaning qiymatini toping.",
                    options = listOf("1", "11/12", "12/11", "2"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "3/4 + 1/6 = 9/12 + 2/12 = 11/12.",
                        "(11/12) ÷ (11/12) = 1."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s1_q3",
                    topicTitle = "Nisbat va proporsiya",
                    examTag = "SAT",
                    prompt = "Xaritada 3 sm masofa haqiqiy hayotda 45 km ni bildiradi. Ikki shahar orasidagi haqiqiy masofa 120 km bo‘lsa, xaritada necha sm bo‘ladi?",
                    options = listOf("6 sm", "8 sm", "9 sm", "10 sm"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "1 sm = 45 ÷ 3 = 15 km.",
                        "120 ÷ 15 = 8 sm."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s1_q4",
                    topicTitle = "Foiz masalasi",
                    examTag = "GRE",
                    prompt = "Sinfdagi o‘quvchilarning 40% i qizlar. Agar o‘g‘il bolalar soni 18 ta bo‘lsa, sinfda jami nechta o‘quvchi bor?",
                    options = listOf("24", "28", "30", "36"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "O‘g‘il bolalar ulushi: 100% - 40% = 60%.",
                        "0.60 × Jami = 18 → Jami = 18 / 0.6 = 30."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s1_q5",
                    topicTitle = "Daraja va ildiz",
                    examTag = "ACT",
                    prompt = "√(50) + √(18) - √(8) ifodani soddalashtiring.",
                    options = listOf("6√2", "8√2", "5√2", "4√2"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "√50 = 5√2, √18 = 3√2, √8 = 2√2.",
                        "5√2 + 3√2 - 2√2 = 6√2."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        ),

        // ==================== STAGE 2: ALGEBRA I ====================
        CurriculumStage(
            id = "stage_2",
            number = 2,
            title = "Algebra I: Chiziqli Tizimlar",
            subtitle = "Tenglamalar, tengsizliklar, slope va tenglamalar sistemasi",
            level = MasteryLevel.FOUNDATION,
            lessons = listOf(
                MathLesson(
                    id = "s2_l1",
                    stageId = "stage_2",
                    title = "Chiziqli tenglamalar va tengsizliklar",
                    subtitle = "Noma’lumni ajratish va modul tenglamalari",
                    estimatedMinutes = 9,
                    theoryParagraphs = listOf(
                        "Chiziqli tenglama ax + b = c ko‘rinishida bo‘ladi. Tengsizlikni manfiy songa ko‘paytirgan yoki bo‘lgan paytda tengsizlik ishorasi qarama-qarshi tomonga o‘zgarishini aslo unutmang!",
                        "Modul tenglamalarda |A| = B (B ≥ 0) bo‘lsa, ikkita holat tekshiriladi: A = B yoki A = -B."
                    ),
                    keyFormulas = listOf(
                        "ax + b > c  (a < 0)  ⇒  x < (c - b) / a",
                        "|x - a| ≤ r  ⇔  a - r ≤ x ≤ a + r"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Tengsizlikni yeching: -3x + 7 ≤ 22",
                            steps = listOf(
                                "Ikkala tomondan 7 ni ayiramiz: -3x ≤ 15.",
                                "-3 ga bo‘lamiz va ishorani o‘zgartiramiz: x ≥ -5."
                            ),
                            finalAnswer = "x ≥ -5"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s2_l1_q1",
                        topicTitle = "Modul tenglama",
                        examTag = "SAT",
                        prompt = "|2x - 5| = 9 tenglama ildizlarining yig‘indisini toping.",
                        options = listOf("5", "7", "9", "2"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "1-holat: 2x - 5 = 9 → 2x = 14 → x₁ = 7.",
                            "2-holat: 2x - 5 = -9 → 2x = -4 → x₂ = -2.",
                            "Yig‘indi: 7 + (-2) = 5."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                ),
                MathLesson(
                    id = "s2_l2",
                    stageId = "stage_2",
                    title = "Chiziqli funksiyalar va Slope",
                    subtitle = "y = mx + b, parallel va perpendikulyar to‘g‘ri chiziqlar",
                    estimatedMinutes = 11,
                    theoryParagraphs = listOf(
                        "y = mx + b tenglamasida m — burchak koeffitsiyenti (slope, og‘ish darajasi), b esa y-o‘qini kesish nuqtasi (y-intercept). Ikki nuqta (x₁, y₁) va (x₂, y₂) orqali m = (y₂ - y₁) / (x₂ - x₁) topiladi.",
                        "Parallel chiziqlarning slope lari teng: m₁ = m₂. Perpendikulyar chiziqlarning slope lari ko‘paytmasi -1 ga teng: m₁ × m₂ = -1."
                    ),
                    keyFormulas = listOf(
                        "m = (y₂ - y₁) / (x₂ - x₁)",
                        "Parallel: m₁ = m₂   |   Perpendikulyar: m₁ · m₂ = -1",
                        "Nuqta-slope shakli: y - y₁ = m(x - x₁)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "y = 2x - 5 chizig‘iga perpendikulyar bo‘lib, (4, 3) nuqtadan o‘tuvchi to‘g‘ri chiziq tenglamasini tuzing.",
                            steps = listOf(
                                "Berilgan slope m₁ = 2. Perpendikulyar slope m₂ = -1/2.",
                                "y - 3 = (-1/2)(x - 4) ⇒ y = -0.5x + 2 + 3.",
                                "Natija: y = -0.5x + 5."
                            ),
                            finalAnswer = "y = -0.5x + 5"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s2_l2_q1",
                        topicTitle = "Slope va chiziqlar",
                        examTag = "SAT",
                        prompt = "(2, 5) va (6, 17) nuqtalardan o‘tuvchi to‘g‘ri chiziqning y-o‘qini kesish nuqtasi (y-intercept) nechaga teng?",
                        options = listOf("-1", "1", "3", "-3"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "m = (17 - 5) / (6 - 2) = 12 / 4 = 3.",
                            "5 = 3(2) + b → 5 = 6 + b → b = -1."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                ),
                MathLesson(
                    id = "s2_l3",
                    stageId = "stage_2",
                    title = "Tenglamalar sistemasi",
                    subtitle = "Qo‘shish usuli, o‘rniga qo‘yish va yechimlar soni",
                    estimatedMinutes = 10,
                    theoryParagraphs = listOf(
                        "Ikki chiziqli tenglama sistemasi 3 xil natija berishi mumkin: 1) Bitta yechim (chiziqlar kesishadi, a₁/a₂ ≠ b₁/b₂); 2) Yechim yo‘q (parallel, a₁/a₂ = b₁/b₂ ≠ c₁/c₂); 3) Cheksiz ko‘p yechim (ustma-ust tushadi, a₁/a₂ = b₁/b₂ = c₁/c₂).",
                        "Digital SAT imtihonida 'cheksiz ko‘p yechim' yoki 'yechimi yo‘q' bo‘lish shartiga oid savollar deyarli har bir testda uchraydi."
                    ),
                    keyFormulas = listOf(
                        "Yechimsiz (Parallel): a₁/a₂ = b₁/b₂ ≠ c₁/c₂",
                        "Cheksiz yechim: a₁/a₂ = b₁/b₂ = c₁/c₂"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "kx + 6y = 12 va 4x + 3y = 6 sistemasi cheksiz ko‘p yechimga ega bo‘lsa, k nechaga teng?",
                            steps = listOf(
                                "Cheksiz ko‘p yechim sharti: k/4 = 6/3 = 12/6.",
                                "6/3 = 2, demak k/4 = 2 → k = 8."
                            ),
                            finalAnswer = "8"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s2_l3_q1",
                        topicTitle = "Tenglamalar sistemasi",
                        examTag = "SAT",
                        prompt = "3x + 2y = 14 va 5x - 2y = 18 bo‘lsa, x + y qiymatini toping.",
                        options = listOf("4", "5", "6", "7"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "Ikkala tenglamani qo‘shamiz: 8x = 32 → x = 4.",
                            "3(4) + 2y = 14 → 2y = 2 → y = 1.",
                            "x + y = 4 + 1 = 5."
                        ),
                        difficultyLabel = "Oson"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s2_q1",
                    topicTitle = "Chiziqli tenglama",
                    examTag = "SAT",
                    prompt = "Agar 4(x - 3) + 2 = 3x + 5 bo‘lsa, 2x - 1 nimaga teng?",
                    options = listOf("29", "30", "15", "27"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "4x - 12 + 2 = 3x + 5 → 4x - 10 = 3x + 5 → x = 15.",
                        "2x - 1 = 2(15) - 1 = 29."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s2_q2",
                    topicTitle = "Parallel chiziqlar",
                    examTag = "ACT",
                    prompt = "ax + 4y = 10 va 6x - 8y = 5 chiziqlari o‘zaro parallel bo‘lsa, a nechaga teng?",
                    options = listOf("-3", "3", "-12", "12"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Parallel sharti: a/6 = 4/(-8) = -1/2.",
                        "a = 6 × (-1/2) = -3."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s2_q3",
                    topicTitle = "Tengsizliklar",
                    examTag = "Milliy Sertifikat",
                    prompt = "5 - 2x > 11 tengsizlikning eng katta butun yechimini toping.",
                    options = listOf("-3", "-4", "-2", "3"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "-2x > 6 → x < -3.",
                        "-3 dan kichik eng katta butun son: -4."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s2_q4",
                    topicTitle = "Matnli masala",
                    examTag = "GMAT",
                    prompt = "Taksida o‘tirish haqi 8 000 so‘m va har bir km uchun 2 500 so‘m olinadi. Yo‘lovchi 28 000 so‘m to‘lagan bo‘lsa, necha km yurgan?",
                    options = listOf("6 km", "7 km", "8 km", "9 km"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "8000 + 2500x = 28000 → 2500x = 20000.",
                        "x = 20000 / 2500 = 8 km."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s2_q5",
                    topicTitle = "Perpendikulyar chiziq",
                    examTag = "SAT",
                    prompt = "3x - y = 7 to‘g‘ri chizig‘iga perpendikulyar bo‘lgan to‘g‘ri chiziqning burchak koeffitsiyenti (slope) nechaga teng?",
                    options = listOf("3", "-3", "1/3", "-1/3"),
                    correctIndex = 3,
                    explanationSteps = listOf(
                        "y = 3x - 7 ⇒ m₁ = 3.",
                        "m₂ = -1 / m₁ = -1/3."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        ),

        // ==================== STAGE 3: ALGEBRA II ====================
        CurriculumStage(
            id = "stage_3",
            number = 3,
            title = "Algebra II: Kvadrat & Logarifm",
            subtitle = "Kvadrat tenglamalar, ko‘phadlar, parabola va logarifmlar",
            level = MasteryLevel.INTERMEDIATE,
            lessons = listOf(
                MathLesson(
                    id = "s3_l1",
                    stageId = "stage_3",
                    title = "Kvadrat tenglamalar va Viyet teoremasi",
                    subtitle = "Diskriminant, ildizlar yig‘indisi va ko‘paytmasi, parabola uchi",
                    estimatedMinutes = 12,
                    theoryParagraphs = listOf(
                        "ax² + bx + c = 0 (a ≠ 0) kvadrat tenglama yechimlari soni Diskriminant D = b² - 4ac orqali aniqlanadi: D > 0 bo‘lsa 2 ta haqiqiy ildiz, D = 0 bo‘lsa 1 ta ildiz, D < 0 bo‘lsa haqiqiy ildiz yo‘q.",
                        "Viyet teoremasi SAT da eng ko‘p ishlatiladigan teoremalardan biridir: ildizlarni topmasdan turib, x₁ + x₂ = -b/a va x₁ · x₂ = c/a ni darhol topish mumkin!"
                    ),
                    keyFormulas = listOf(
                        "D = b² - 4ac   |   x₁,₂ = (-b ± √D) / 2a",
                        "Viyet: x₁ + x₂ = -b/a   |   x₁ · x₂ = c/a",
                        "Parabola uchi (vertex): x₀ = -b / (2a)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "3x² - 12x + k = 0 tenglama faqat bitta haqiqiy ildizga ega bo‘lsa, k ni toping.",
                            steps = listOf(
                                "Bitta ildiz sharti: D = 0.",
                                "(-12)² - 4(3)(k) = 0 ⇒ 144 - 12k = 0.",
                                "12k = 144 ⇒ k = 12."
                            ),
                            finalAnswer = "12"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s3_l1_q1",
                        topicTitle = "Viyet teoremasi",
                        examTag = "SAT",
                        prompt = "2x² - 10x + 7 = 0 tenglamaning ildizlari x₁ va x₂ bo‘lsa, x₁² + x₂² qiymatini toping.",
                        options = listOf("18", "25", "11", "32"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "x₁ + x₂ = 10/2 = 5, x₁·x₂ = 7/2 = 3.5.",
                            "x₁² + x₂² = (x₁ + x₂)² - 2x₁x₂ = 25 - 7 = 18."
                        ),
                        difficultyLabel = "Qiyin"
                    )
                ),
                MathLesson(
                    id = "s3_l2",
                    stageId = "stage_3",
                    title = "Ko‘phadlar va Qisqa ko‘paytirish",
                    subtitle = "Ko‘paytuvchilarga ajratish va Qoldiq teoremasi",
                    estimatedMinutes = 10,
                    theoryParagraphs = listOf(
                        "Qisqa ko‘paytirish formulalari murakkab algebraik kasrlarni bir soniyada qisqartirish imkonini beradi: a² - b² = (a - b)(a + b) va (a ± b)² = a² ± 2ab + b².",
                        "Qoldiq teoremasi (Remainder Theorem): P(x) ko‘phadni (x - c) ga bo‘lgandagi qoldiq P(c) ga teng! Agar P(c) = 0 bo‘lsa, (x - c) ko‘phadning bo‘luvchisi (factor) hisoblanadi."
                    ),
                    keyFormulas = listOf(
                        "a² - b² = (a - b)(a + b)",
                        "a³ ± b³ = (a ± b)(a² ∓ ab + b²)",
                        "P(x) ÷ (x - c) qoldig‘i = P(c)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "P(x) = x³ - 4x + 7 ko‘phadni (x - 2) ga bo‘lgandagi qoldiqni toping.",
                            steps = listOf(
                                "Qoldiq teoremasi bo‘yicha x = 2 ni qo‘yamiz.",
                                "P(2) = 2³ - 4(2) + 7 = 8 - 8 + 7 = 7."
                            ),
                            finalAnswer = "7"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s3_l2_q1",
                        topicTitle = "Qoldiq teoremasi",
                        examTag = "SAT",
                        prompt = "P(x) = x² + kx - 6 ko‘phad (x - 3) ga qoldiqsiz bo‘linsa, k nechaga teng?",
                        options = listOf("-1", "1", "-2", "2"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "P(3) = 0 bo‘lishi kerak.",
                            "3² + 3k - 6 = 0 → 3 + 3k = 0 → k = -1."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                ),
                MathLesson(
                    id = "s3_l3",
                    stageId = "stage_3",
                    title = "Logarifm va Eksponensial funksiyalar",
                    subtitle = "Logarifm xossalari, o‘sish/kamayish modellari",
                    estimatedMinutes = 11,
                    theoryParagraphs = listOf(
                        "logₐ(b) = c degani aᶜ = b deganidir (bu yerda a > 0, a ≠ 1 va b > 0). Logarifm ko‘paytmani yig‘indiga, bo‘linmani ayirmaga aylantiradi.",
                        "Eksponensial o‘sish modeli: y = A · (1 + r)ᵗ. Masalan, bakteriyalar soni har 3 soatda 2 barobar oshsa, t soatdan keyin N(t) = N₀ · 2^(t/3) bo‘ladi."
                    ),
                    keyFormulas = listOf(
                        "logₐ(xy) = logₐx + logₐy   |   logₐ(x/y) = logₐx - logₐy",
                        "logₐ(xⁿ) = n · logₐx   |   logₐ(a) = 1",
                        "Asosni almashtirish: logₐb = ln(b) / ln(a)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Hisoblang: log₂(24) - log₂(3)",
                            steps = listOf(
                                "Ayirmani bo‘linma logarifmiga o‘tkazamiz: log₂(24 / 3) = log₂(8).",
                                "2³ = 8 bo‘lgani uchun log₂(8) = 3."
                            ),
                            finalAnswer = "3"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s3_l3_q1",
                        topicTitle = "Logarifmik tenglama",
                        examTag = "Milliy Sertifikat",
                        prompt = "log₃(x - 2) = 2 tenglamani yeching.",
                        options = listOf("8", "11", "7", "10"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "Ta’rifga ko‘ra x - 2 = 3² = 9.",
                            "x = 9 + 2 = 11 (aniqlanish sohasi x > 2 ga mos)."
                        ),
                        difficultyLabel = "Oson"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s3_q1",
                    topicTitle = "Parabola uchi",
                    examTag = "SAT",
                    prompt = "f(x) = 2x² - 8x + 11 funksiyaning eng kichik (minimum) qiymatini toping.",
                    options = listOf("2", "3", "5", "11"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Parabola uchi x₀ = -(-8) / (2·2) = 8 / 4 = 2.",
                        "f(2) = 2(4) - 8(2) + 11 = 8 - 16 + 11 = 3."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s3_q2",
                    topicTitle = "Qisqa ko‘paytirish",
                    examTag = "GRE",
                    prompt = "Agar x + y = 7 va x - y = 3 bo‘lsa, x² - y² nimaga teng?",
                    options = listOf("10", "21", "40", "58"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "x² - y² = (x - y)(x + y) = 3 × 7 = 21."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s3_q3",
                    topicTitle = "Logarifm xossalari",
                    examTag = "Milliy Sertifikat",
                    prompt = "log₅(125) + log₂(1/8) ifodaning qiymatini toping.",
                    options = listOf("0", "6", "-1", "2"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "log₅(5³) = 3.",
                        "log₂(2⁻³) = -3.",
                        "3 + (-3) = 0."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s3_q4",
                    topicTitle = "Eksponensial model",
                    examTag = "SAT",
                    prompt = "Laboratoriyadagi bakteriyalar soni har 4 soatda 2 barobar ko‘payadi. Dastlab 500 ta bakteriya bo‘lsa, 12 soatdan keyin nechta bo‘ladi?",
                    options = listOf("1500", "2000", "4000", "8000"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "12 soatda 12 / 4 = 3 marta ikkilanadi.",
                        "500 × 2³ = 500 × 8 = 4000."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s3_q5",
                    topicTitle = "Kompleks sonlar",
                    examTag = "ACT",
                    prompt = "i = √(-1) bo‘lsa, (3 + 2i)(3 - 2i) ko‘paytma nimaga teng?",
                    options = listOf("5", "13", "9 - 4i", "13i"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "(3 + 2i)(3 - 2i) = 3² - (2i)² = 9 - 4i².",
                        "i² = -1 bo‘lgani uchun 9 - 4(-1) = 9 + 4 = 13."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        )
    )
}
