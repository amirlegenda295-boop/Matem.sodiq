package com.example.data

import com.example.model.CurriculumStage
import com.example.model.MasteryLevel
import com.example.model.MathLesson
import com.example.model.MathQuestion
import com.example.model.WorkedExample

object CurriculumDataPart2 {

    val stages: List<CurriculumStage> = listOf(
        // ==================== STAGE 4: GEOMETRIYA & TRIGONOMETRIYA ====================
        CurriculumStage(
            id = "stage_4",
            number = 4,
            title = "Geometriya & Trigonometriya",
            subtitle = "Uchburchaklar, aylana teoremalari, sin/cos/tan va fazoviy jismlar",
            level = MasteryLevel.INTERMEDIATE,
            lessons = listOf(
                MathLesson(
                    id = "s4_l1",
                    stageId = "stage_4",
                    title = "Uchburchaklar va Pifagor teoremasi",
                    subtitle = "Maxsus to‘g‘ri burchakli uchburchaklar (30-60-90, 45-45-90) va o‘xshashlik",
                    estimatedMinutes = 11,
                    theoryParagraphs = listOf(
                        "Har qanday uchburchakning ichki burchaklari yig‘indisi 180° ga teng. To‘g‘ri burchakli uchburchakda katetlar a, b va gipotenuza c uchun a² + b² = c² (Pifagor teoremasi) bajariladi.",
                        "SAT va ACT imtihonlarida eng ko‘p uchraydigan Pifagor uchliklari: (3, 4, 5), (5, 12, 13), (8, 15, 17). Shuningdek, 30°-60°-90° uchburchakda tomonlar nisbati x : x√3 : 2x bo‘ladi."
                    ),
                    keyFormulas = listOf(
                        "Pifagor: a² + b² = c²",
                        "45°-45°-90°: tomonlar x, x, x√2",
                        "30°-60°-90°: tomonlar x, x√3, 2x"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Teng tomonli uchburchakning tomoni 6 sm. Uning balandligi va yuzini toping.",
                            steps = listOf(
                                "Balandlik 30°-60°-90° uchburchak hosil qiladi, gipotenuza 6, kichik katet 3.",
                                "Balandlik h = 3√3 sm.",
                                "Yuz S = (1/2) × 6 × 3√3 = 9√3 sm²."
                            ),
                            finalAnswer = "h = 3√3 sm, S = 9√3 sm²"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s4_l1_q1",
                        topicTitle = "Maxsus uchburchaklar",
                        examTag = "SAT",
                        prompt = "To‘g‘ri burchakli uchburchakning burchaklari 30°, 60° va 90°. Agar gipotenuzasi 14 sm bo‘lsa, 60° li burchak qarshisidagi katet uzunligi qancha?",
                        options = listOf("7 sm", "7√2 sm", "7√3 sm", "14√3 sm"),
                        correctIndex = 2,
                        explanationSteps = listOf(
                            "30° qarshisidagi katet = 14 / 2 = 7 sm.",
                            "60° qarshisidagi katet = 7 × √3 = 7√3 sm."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s4_l2",
                    stageId = "stage_4",
                    title = "Aylana tenglamasi va Doira",
                    subtitle = "Yoy uzunligi, sektor yuzi va koordinata tekisligida aylana",
                    estimatedMinutes = 10,
                    theoryParagraphs = listOf(
                        "Radiusi R bo‘lgan doira yuzi S = πR², aylana uzunligi L = 2πR. Agar markaziy burchak α gradus bo‘lsa, yoy uzunligi (α/360°)·2πR, sektor yuzi esa (α/360°)·πR² bo‘ladi.",
                        "Koordinata tekisligida markazi (h, k) va radiusi r bo‘lgan aylana tenglamasi: (x - h)² + (y - k)² = r²."
                    ),
                    keyFormulas = listOf(
                        "Aylana uzunligi: L = 2πR   |   Doira yuzi: S = πR²",
                        "Aylana tenglamasi: (x - h)² + (y - k)² = r²",
                        "Radian ↔ Gradus: π rad = 180°"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "x² - 6x + y² + 4y = 12 aylana radiusini toping.",
                            steps = listOf(
                                "To‘la kvadratga ajratamiz: (x² - 6x + 9) + (y² + 4y + 4) = 12 + 9 + 4.",
                                "(x - 3)² + (y + 2)² = 25 = 5².",
                                "Markazi (3, -2), radius r = 5."
                            ),
                            finalAnswer = "r = 5"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s4_l2_q1",
                        topicTitle = "Aylana tenglamasi",
                        examTag = "SAT",
                        prompt = "(x + 4)² + (y - 1)² = 36 aylananing diametri nechaga teng?",
                        options = listOf("6", "12", "18", "36"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "r² = 36 ⇒ r = 6.",
                            "Diametr d = 2r = 12."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s4_l3",
                    stageId = "stage_4",
                    title = "Trigonometriya asoslari",
                    subtitle = "SOH-CAH-TOA, sin(x) = cos(90° - x) va Kosinuslar teoremasi",
                    estimatedMinutes = 12,
                    theoryParagraphs = listOf(
                        "To‘g‘ri burchakli uchburchakda: sin(θ) = qarshi katet / gipotenuza, cos(θ) = yopishgan katet / gipotenuza, tan(θ) = qarshi / yopishgan.",
                        "SAT imtihonida juda mashhur ayniyat: agar α + β = 90° bo‘lsa, sin(α) = cos(β)! Shuningdek, asosiy trigonometrik ayniyat: sin²θ + cos²θ = 1."
                    ),
                    keyFormulas = listOf(
                        "sin²θ + cos²θ = 1   |   tanθ = sinθ / cosθ",
                        "sin(x) = cos(90° - x)",
                        "Kosinuslar teoremasi: c² = a² + b² - 2ab·cos(C)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "To‘g‘ri burchakli uchburchakda α + β = 90° va sin(α) = 5/13. cos(β) + sin(β) ni toping.",
                            steps = listOf(
                                "α + β = 90° bo‘lgani uchun cos(β) = sin(α) = 5/13.",
                                "sin²(β) = 1 - (5/13)² = 144/169 ⇒ sin(β) = 12/13.",
                                "Yig‘indi: 5/13 + 12/13 = 17/13."
                            ),
                            finalAnswer = "17/13"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s4_l3_q1",
                        topicTitle = "Trigonometriya",
                        examTag = "SAT",
                        prompt = "O‘tkir burchakli θ uchun cos(θ) = 0.6 bo‘lsa, tan(θ) nimaga teng?",
                        options = listOf("4/3", "3/4", "4/5", "5/3"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "cos(θ) = 3/5 ⇒ yopishgan = 3, gipotenuza = 5.",
                            "Pifagorga ko‘ra qarshi katet = 4.",
                            "tan(θ) = qarshi / yopishgan = 4/3."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s4_q1",
                    topicTitle = "O‘xshash uchburchaklar",
                    examTag = "SAT",
                    prompt = "Ikki o‘xshash uchburchakning perimetrlari nisbati 2 : 5 ga teng. Agar kichik uchburchak yuzi 12 sm² bo‘lsa, katta uchburchak yuzini toping.",
                    options = listOf("30 sm²", "60 sm²", "75 sm²", "48 sm²"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "Yuzalar nisbati tomonlar nisbatining kvadratiga teng: (2/5)² = 4/25.",
                        "12 / S = 4 / 25 → S = 12 × 25 / 4 = 75 sm²."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s4_q2",
                    topicTitle = "Fazoviy jismlar",
                    examTag = "ACT",
                    prompt = "Silindrning asos radiusi 3 sm, balandligi 10 sm. Uning hajmini toping.",
                    options = listOf("30π sm³", "60π sm³", "90π sm³", "120π sm³"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "V = πR²h = π × 3² × 10 = 90π sm³."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s4_q3",
                    topicTitle = "Yoy uzunligi",
                    examTag = "SAT",
                    prompt = "Radiusi 12 sm bo‘lgan aylanada 60° li markaziy burchakka mos yoy uzunligini toping.",
                    options = listOf("2π", "4π", "6π", "8π"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "L = (60° / 360°) × 2π × 12 = (1/6) × 24π = 4π."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s4_q4",
                    topicTitle = "Kosinuslar teoremasi",
                    examTag = "Milliy Sertifikat",
                    prompt = "Uchburchakning ikki tomoni 5 va 8, ular orasidagi burchak 60°. Uchinchi tomonni toping.",
                    options = listOf("7", "√89", "6", "√129"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "c² = 5² + 8² - 2(5)(8)cos(60°) = 25 + 64 - 80(0.5) = 89 - 40 = 49.",
                        "c = √49 = 7."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s4_q5",
                    topicTitle = "Aylana tenglamasi",
                    examTag = "SAT",
                    prompt = "x² + y² - 4x + 10y + 20 = 0 aylana markazining koordinatalarini toping.",
                    options = listOf("(2, -5)", "(-2, 5)", "(4, -10)", "(2, 5)"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "(x - 2)² + (y + 5)² = 4 + 25 - 20 = 9.",
                        "Markaz: (2, -5)."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        ),

        // ==================== STAGE 5: EHTIMOLLIK & STATISTIKA ====================
        CurriculumStage(
            id = "stage_5",
            number = 5,
            title = "Ehtimollik & Statistika",
            subtitle = "Statistik ko‘rsatkichlar, kombinatorika va ehtimollik nazariyasi",
            level = MasteryLevel.ADVANCED,
            lessons = listOf(
                MathLesson(
                    id = "s5_l1",
                    stageId = "stage_5",
                    title = "O‘rta qiymat, Mediana va Standart og‘ish",
                    subtitle = "Ma’lumotlar to‘plamini tahlil qilish",
                    estimatedMinutes = 9,
                    theoryParagraphs = listOf(
                        "O‘rta arifmetik (Mean) — barcha elementlar yig‘indisining ular soniga nisbati. Mediana — o‘sish tartibida joylashtirilgan to‘plamning markaziy elementi. Moda — eng ko‘p takrorlangan qiymat.",
                        "Standart og‘ish (Standard Deviation) ma’lumotlarning o‘rta qiymat atrofida qanchalik tarqalganini ko‘rsatadi: sonlar bir-biriga yaqin bo‘lsa standart og‘ish kichik, uzoq bo‘lsa katta bo‘ladi. Barcha sonlarga bir xil konstanta qo‘shilsa, standart og‘ish o‘zgarmaydi!"
                    ),
                    keyFormulas = listOf(
                        "Mean = (x₁ + x₂ + ... + xₙ) / n",
                        "Range (Kenglik) = Max - Min",
                        "To‘plamga +C qo‘shilsa: Mean + C, SD o‘zgarmaydi"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "5 ta sonning o‘rta arifmetigi 18. Ularga 30 soni qo‘shilsa, yangi o‘rta arifmetik nechaga teng bo‘ladi?",
                            steps = listOf(
                                "5 ta sonning yig‘indisi: 5 × 18 = 90.",
                                "Yangi yig‘indi: 90 + 30 = 120.",
                                "Yangi o‘rta: 120 / 6 = 20."
                            ),
                            finalAnswer = "20"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s5_l1_q1",
                        topicTitle = "Mediana va o‘rta qiymat",
                        examTag = "SAT",
                        prompt = "{4, 12, 7, 15, 7, 9} to‘plamning medianasi nechaga teng?",
                        options = listOf("7", "8", "9", "8.5"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "Tartiblaymiz: 4, 7, 7, 9, 12, 15.",
                            "Juft ta (6 ta) element bo‘lgani uchun o‘rtadagi ikkitasining o‘rtachasi: (7 + 9) / 2 = 8."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s5_l2",
                    stageId = "stage_5",
                    title = "Kombinatorika: O‘rinlashtirish va Guruhlash",
                    subtitle = "Faktorial, Permutatsiya (tartib muhim) va Kombinatsiya (tartib muhim emas)",
                    estimatedMinutes = 11,
                    theoryParagraphs = listOf(
                        "Agar tanlovda tartib muhim bo‘lsa (masalan, 1-o‘rin va 2-o‘rin, parol tuzish), Permutatsiya Aₙᵏ = n! / (n - k)! ishlatiladi.",
                        "Agar tartib ahamiyatga ega bo‘lmasa (masalan, 10 kishidan 3 kishilik qo‘mita tuzish), Kombinatsiya Cₙᵏ = n! / [k!(n - k)!] qo‘llaniladi."
                    ),
                    keyFormulas = listOf(
                        "n! = 1 × 2 × ... × n   (0! = 1)",
                        "Permutatsiya: P(n, k) = n! / (n - k)!",
                        "Kombinatsiya: C(n, k) = n! / [k!(n - k)!]"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "8 ta o‘quvchidan 3 kishilik matematika olimpiada jamoasini necha xil usulda tuzish mumkin?",
                            steps = listOf(
                                "Jamoa tarkibida tartib muhim emas, demak C(8, 3).",
                                "C(8, 3) = (8 × 7 × 6) / (3 × 2 × 1) = 56."
                            ),
                            finalAnswer = "56"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s5_l2_q1",
                        topicTitle = "Kombinatsiya",
                        examTag = "GRE",
                        prompt = "6 ta har xil kitobdan 2 tasini necha xil usulda tanlab olish mumkin?",
                        options = listOf("12", "15", "30", "36"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "C(6, 2) = (6 × 5) / 2 = 15."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s5_l3",
                    stageId = "stage_5",
                    title = "Ehtimollik va Shartli ehtimollik",
                    subtitle = "Klassik ehtimollik, mustaqil hodisalar va jadvaldan shartli ehtimol",
                    estimatedMinutes = 10,
                    theoryParagraphs = listOf(
                        "Hodisa ehtimoli P(A) = qulay natijalar soni / barcha mumkin bo‘lgan natijalar soni. Qarama-qarshi hodisa ehtimoli: P(A') = 1 - P(A).",
                        "Shartli ehtimol P(A | B) — B hodisa ro‘y bergani ma’lum bo‘lganda A ning ro‘y berish ehtimoli. SAT jadval savollarida B shartiga mos qator yoki ustunni maxraj qilib olish kifoya!"
                    ),
                    keyFormulas = listOf(
                        "P(A) = m / n   (0 ≤ P(A) ≤ 1)",
                        "Mustaqil hodisalar: P(A va B) = P(A) × P(B)",
                        "Shartli: P(A | B) = P(A ∩ B) / P(B)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Qutida 4 ta qizil va 6 ta ko‘k shar bor. Qaytarib qo‘ymasdan ketma-ket 2 ta qizil shar olish ehtimoli qancha?",
                            steps = listOf(
                                "1-shar qizil bo‘lish ehtimoli: 4/10.",
                                "Qutida 3 ta qizil va jami 9 ta shar qoldi. 2-shar qizil bo‘lishi: 3/9 = 1/3.",
                                "Ko‘paytma: (4/10) × (1/3) = 4/30 = 2/15."
                            ),
                            finalAnswer = "2/15"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s5_l3_q1",
                        topicTitle = "Ehtimollik",
                        examTag = "GMAT",
                        prompt = "Ikki marta o‘yin kubigi tashlanganda, tushgan ochkolar yig‘indisi 9 ga teng bo‘lish ehtimoli nechaga teng?",
                        options = listOf("1/9", "1/12", "5/36", "1/6"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "Jami natijalar: 6 × 6 = 36.",
                            "Yig‘indisi 9 bo‘ladigan juftliklar: (3,6), (4,5), (5,4), (6,3) — 4 ta.",
                            "Ehtimollik: 4/36 = 1/9."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s5_q1",
                    topicTitle = "Standart og‘ish",
                    examTag = "GRE",
                    prompt = "A = {2, 4, 6, 8} va B = {102, 104, 106, 108} to‘plamlarining standart og‘ishlari haqida qaysi fikr to‘g‘ri?",
                    options = listOf(
                        "B ning standart og‘ishi katta",
                        "Ikkalasining standart og‘ishi teng",
                        "A ning standart og‘ishi katta",
                        "Aniqlab bo‘lmaydi"
                    ),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "B to‘plami A ning har bir elementiga 100 qo‘shish orqali olingan.",
                        "Bir xil son qo‘shilganda tarqalish (standart og‘ish) o‘zgarmaydi."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s5_q2",
                    topicTitle = "Kombinatorika",
                    examTag = "Milliy Sertifikat",
                    prompt = "MATEMA so‘zidagi harflarning o‘rnini almashtirib nechta har xil so‘z (ma’noli yoki ma’nosiz) tuzish mumkin?",
                    options = listOf("720", "360", "180", "120"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "Jami 6 ta harf: M (2 marta), A (2 marta), T (1), E (1).",
                        "Takrorli permutatsiya: 6! / (2! · 2!) = 720 / 4 = 180."
                    ),
                    difficultyLabel = "Qiyin"
                ),
                MathQuestion(
                    id = "s5_q3",
                    topicTitle = "O‘rta qiymat",
                    examTag = "SAT",
                    prompt = "4 ta testning o‘rtacha bali 85. 5-testdan keyin o‘rtacha ball 88 bo‘lishi uchun 5-testdan necha ball olish kerak?",
                    options = listOf("95", "98", "100", "92"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "4 ta test yig‘indisi: 4 × 85 = 340.",
                        "5 ta test yig‘indisi: 5 × 88 = 440.",
                        "5-test bali: 440 - 340 = 100."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s5_q4",
                    topicTitle = "Ehtimollik",
                    examTag = "GMAT",
                    prompt = "Tanga 3 marta tashlanganda kamida 1 marta gerb tushish ehtimoli nechaga teng?",
                    options = listOf("7/8", "3/8", "1/2", "5/8"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Hech bo‘lmaganda 1 gerb = 1 - P(umuman gerb tushmasligi).",
                        "3 marta raqam tushish ehtimoli: (1/2)³ = 1/8.",
                        "1 - 1/8 = 7/8."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s5_q5",
                    topicTitle = "Shartli ehtimollik",
                    examTag = "SAT",
                    prompt = "40 ta talabaning 25 tasi ingliz tilini, 15 tasi fransuz tilini biladi (har biri faqat bitta til biladi). Ingliz tilini biladiganlarning 10 tasi qiz bola. Tasodifiy tanlangan ingliz tili biluvchisi qiz bola bo‘lish ehtimoli qancha?",
                    options = listOf("10/40", "10/25", "15/25", "10/15"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Shart: talaba ingliz tilini biladi (jami 25 ta).",
                        "Ulardan 10 tasi qiz bola → 10/25 = 2/5."
                    ),
                    difficultyLabel = "Oson"
                )
            )
        ),

        // ==================== STAGE 6: CALCULUS MASTER ====================
        CurriculumStage(
            id = "stage_6",
            number = 6,
            title = "Calculus Master (Oliy Matematika)",
            subtitle = "Limitlar, hosila, ekstremumlar va aniq/noaniq integrallar",
            level = MasteryLevel.MASTER,
            lessons = listOf(
                MathLesson(
                    id = "s6_l1",
                    stageId = "stage_6",
                    title = "Limitlar va 0/0 noaniqligi",
                    subtitle = "Limit qoidalari, qisqartirish va cheksizlikka intilish",
                    estimatedMinutes = 11,
                    theoryParagraphs = listOf(
                        "Limit lim(x→a) f(x) — x qiymati a ga cheksiz yaqinlashganda f(x) qaysi songa intilishini bildiradi. Avval a ni bevosita qo‘yib ko‘ring; agar 0/0 chiqsa, ko‘paytuvchilarga ajratish yoki qo‘shmasiga ko‘paytirish usulidan foydalaning.",
                        "x → ∞ da ko‘phadlar nisbatining limiti surat va maxrajdagi eng katta daraja koeffitsiyentlari nisbatiga bog‘liq."
                    ),
                    keyFormulas = listOf(
                        "lim(x→0) [sin(x) / x] = 1",
                        "lim(x→∞) (1 + 1/x)ˣ = e ≈ 2.718",
                        "lim(x→a) (x² - a²)/(x - a) = 2a"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Hisoblang: lim(x→3) (x² - 9) / (x - 3)",
                            steps = listOf(
                                "x = 3 qo‘ysak 0/0 chiqadi.",
                                "Suratni ajratamiz: (x - 3)(x + 3) / (x - 3).",
                                "x - 3 qisqaradi → lim(x→3) (x + 3) = 3 + 3 = 6."
                            ),
                            finalAnswer = "6"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s6_l1_q1",
                        topicTitle = "Limitlar",
                        examTag = "AP Calculus",
                        prompt = "lim(x→∞) (6x² + 5x - 1) / (2x² - 3x + 4) limitni hisoblang.",
                        options = listOf("0", "3", "6", "∞"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "Surat va maxrajning eng katta darajalari teng (x²).",
                            "Limit oldidagi koeffitsiyentlar nisbati: 6 / 2 = 3."
                        ),
                        difficultyLabel = "Oson"
                    )
                ),
                MathLesson(
                    id = "s6_l2",
                    stageId = "stage_6",
                    title = "Hosila (Derivative) va Ekstremumlar",
                    subtitle = "Daraja qoidasi, murakkab funksiya (Chain Rule) va urinma",
                    estimatedMinutes = 13,
                    theoryParagraphs = listOf(
                        "Hosila f'(x) funksiyaning berilgan nuqtadagi oniy o‘zgarish tezligini va grafik urinmasining burchak koeffitsiyentini (slope) bildiradi.",
                        "Funksiyaning maksimum yoki minimum nuqtalarini topish uchun hosila nolga tenglashtiriladi: f'(x) = 0 (kritik nuqtalar)."
                    ),
                    keyFormulas = listOf(
                        "(xⁿ)' = n · xⁿ⁻¹   |   (eˣ)' = eˣ   |   (ln x)' = 1/x",
                        "(sin x)' = cos x   |   (cos x)' = -sin x",
                        "Chain Rule: [f(g(x))]' = f'(g(x)) · g'(x)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "f(x) = x³ - 3x² + 4 funksiyaning lokal minimum nuqtasini toping.",
                            steps = listOf(
                                "Hosila: f'(x) = 3x² - 6x = 3x(x - 2) = 0 ⇒ x = 0 yoki x = 2.",
                                "Ikkinchi hosila: f''(x) = 6x - 6.",
                                "x = 2 da f''(2) = 6 > 0 (Demak x = 2 lokal minimum)."
                            ),
                            finalAnswer = "x = 2, f(2) = 0"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s6_l2_q1",
                        topicTitle = "Hosila va Chain Rule",
                        examTag = "AP Calculus",
                        prompt = "f(x) = (2x + 1)⁴ bo‘lsa, f'(1) qiymatini toping.",
                        options = listOf("108", "216", "27", "81"),
                        correctIndex = 1,
                        explanationSteps = listOf(
                            "Chain Rule: f'(x) = 4(2x + 1)³ · 2 = 8(2x + 1)³.",
                            "x = 1 ni qo‘yamiz: 8(3)³ = 8 × 27 = 216."
                        ),
                        difficultyLabel = "O‘rta"
                    )
                ),
                MathLesson(
                    id = "s6_l3",
                    stageId = "stage_6",
                    title = "Aniq va Noaniq Integrallar",
                    subtitle = "Boshlang‘ich funksiya, Nyuton-Leybnis formulasi va egri chiziq ostidagi yuza",
                    estimatedMinutes = 12,
                    theoryParagraphs = listOf(
                        "Integrallash — hosila olishning teskari amalidir. ∫xⁿ dx = xⁿ⁺¹ / (n + 1) + C (n ≠ -1).",
                        "Aniq integral ∫ₐᵇ f(x) dx grafik ostidagi yuzani beradi va Nyuton-Leybnis formulasi bilan hisoblanadi: F(b) - F(a)."
                    ),
                    keyFormulas = listOf(
                        "∫ xⁿ dx = xⁿ⁺¹ / (n + 1) + C  (n ≠ -1)",
                        "∫ eˣ dx = eˣ + C   |   ∫ (1/x) dx = ln|x| + C",
                        "Nyuton-Leybnis: ∫ₐᵇ f(x) dx = F(b) - F(a)"
                    ),
                    workedExamples = listOf(
                        WorkedExample(
                            problem = "Aniq integralni hisoblang: ∫₀² (3x² + 2) dx",
                            steps = listOf(
                                "Boshlang‘ich funksiya: F(x) = x³ + 2x.",
                                "F(2) = 2³ + 2(2) = 8 + 4 = 12.",
                                "F(0) = 0. Natija: 12 - 0 = 12."
                            ),
                            finalAnswer = "12"
                        )
                    ),
                    checkQuestion = MathQuestion(
                        id = "s6_l3_q1",
                        topicTitle = "Aniq integral",
                        examTag = "AP Calculus",
                        prompt = "∫₁³ 4x dx aniq integral qiymatini toping.",
                        options = listOf("16", "18", "12", "8"),
                        correctIndex = 0,
                        explanationSteps = listOf(
                            "Boshlang‘ich funksiya: F(x) = 2x².",
                            "F(3) - F(1) = 2(9) - 2(1) = 18 - 2 = 16."
                        ),
                        difficultyLabel = "Oson"
                    )
                )
            ),
            stageQuizQuestions = listOf(
                MathQuestion(
                    id = "s6_q1",
                    topicTitle = "Limitlar",
                    examTag = "AP Calculus",
                    prompt = "lim(x→0) [sin(5x) / x] limit qiymatini toping.",
                    options = listOf("1", "5", "1/5", "0"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "lim(x→0) [sin(5x) / (5x)] × 5 = 1 × 5 = 5."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s6_q2",
                    topicTitle = "Urinma tenglamasi",
                    examTag = "AP Calculus",
                    prompt = "y = x² + 3x egri chizig‘iga x = 2 nuqtada o‘tkazilgan urinmaning burchak koeffitsiyentini (slope) toping.",
                    options = listOf("4", "7", "10", "5"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "y' = 2x + 3.",
                        "x = 2 da y'(2) = 2(2) + 3 = 7."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s6_q3",
                    topicTitle = "Ko‘paytma hosilasi",
                    examTag = "Milliy Sertifikat",
                    prompt = "f(x) = x · eˣ funksiyaning hosilasi f'(x) ni toping.",
                    options = listOf("eˣ", "(x + 1)eˣ", "x·eˣ", "(x - 1)eˣ"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "(u·v)' = u'v + uv' = 1·eˣ + x·eˣ = (x + 1)eˣ."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "s6_q4",
                    topicTitle = "Aniq integral",
                    examTag = "AP Calculus",
                    prompt = "∫₀^(π/2) cos(x) dx aniq integral qiymatini toping.",
                    options = listOf("0", "1", "-1", "π/2"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Boshlang‘ich funksiya: sin(x).",
                        "sin(π/2) - sin(0) = 1 - 0 = 1."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "s6_q5",
                    topicTitle = "Ekstremum",
                    examTag = "Master",
                    prompt = "Perimetri 40 m bo‘lgan to‘g‘ri to‘rtburchak shaklidagi bog‘ning eng katta yuzasi necha m² bo‘lishi mumkin?",
                    options = listOf("80 m²", "100 m²", "120 m²", "160 m²"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "2(x + y) = 40 → y = 20 - x.",
                        "S(x) = x(20 - x) = 20x - x².",
                        "S'(x) = 20 - 2x = 0 → x = 10, y = 10 → S = 100 m²."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        )
    )
}
