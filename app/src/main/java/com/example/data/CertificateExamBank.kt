package com.example.data

import com.example.model.CertificateTrack
import com.example.model.FormulaCategory
import com.example.model.FormulaEntry
import com.example.model.MathQuestion
import com.example.model.TargetExamTrack

object CertificateExamBank {

    val allStages by lazy { CurriculumDataPart1.stages + CurriculumDataPart2.stages }

    fun findLessonById(lessonId: String) = allStages
        .flatMap { it.lessons }
        .firstOrNull { it.id == lessonId }

    fun findStageById(stageId: String) = allStages
        .firstOrNull { it.id == stageId }

    val dailyChallengeQuestion = MathQuestion(
        id = "daily_sat_2026_1",
        topicTitle = "Digital SAT Algebra siri",
        examTag = "SAT",
        prompt = "Agar 5x - 3y = 19 va x + 2y = 9 bo‘lsa, 6x - y ifodaning qiymatini toping.",
        options = listOf("28", "10", "32", "24"),
        correctIndex = 0,
        explanationSteps = listOf(
            "SAT usuli: x va y ni alohida topish shart emas!",
            "Ikkala tenglamani qo‘shamiz: (5x - 3y) + (x + 2y) = 19 + 9.",
            "6x - y = 28."
        ),
        difficultyLabel = "SAT Strategiya"
    )

    val certificateTracks: List<CertificateTrack> = listOf(
        CertificateTrack(
            id = "cert_sat",
            examType = TargetExamTrack.SAT,
            title = "Digital SAT Math",
            subtitle = "AQSH va xalqaro universitetlar (200–800 ball)",
            scoreRangeLabel = "200–800 ball",
            durationMinutes = 15,
            descriptionUz = "Digital SAT Math bo‘limi 4 ta asosiy yo‘nalishni qamrab oladi: Algebra (35%), Advanced Math (35%), Problem-Solving & Data Analysis (15%) hamda Geometry & Trigonometry (15%).",
            keyTopics = listOf(
                "Chiziqli tenglamalar va tizimlar",
                "Kvadrat funksiya, Viyet va Desmos strategiyasi",
                "Foizlar, eksponensial o‘sish va statistika",
                "Aylana tenglamasi va sin(x) = cos(90° - x)"
            ),
            examTips = listOf(
                "Viyet teoremasi yordamida ildizlar yig‘indisi (-b/a) ni 3 soniyada toping.",
                "Tenglamalar sistemasida 'cheksiz yechim' yoki 'yechimsiz' shartiga doim koeffitsiyentlar nisbatini tekshiring.",
                "Ko‘p hollarda so‘ralgan ifodani (masalan, 3x + 2y) tenglamalarni qo‘shib/ayirib topish mumkin."
            ),
            questions = listOf(
                MathQuestion(
                    id = "sat_q1",
                    topicTitle = "Heart of Algebra",
                    examTag = "SAT",
                    prompt = "3(2x - 4) + 5x = 11x - k tenglama cheksiz ko‘p yechimga ega bo‘lsa, k nechaga teng?",
                    options = listOf("12", "-12", "4", "-4"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Chap tomonni ochamiz: 6x - 12 + 5x = 11x - 12.",
                        "11x - 12 = 11x - k cheksiz yechim berishi uchun -12 = -k → k = 12."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "sat_q2",
                    topicTitle = "Advanced Math",
                    examTag = "SAT",
                    prompt = "f(x) = a(x - 3)(x + 7) parabola (1, -32) nuqtadan o‘tadi. Parabola uchining (vertex) x-koordinatasi nechaga teng?",
                    options = listOf("-2", "2", "-4", "4"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Parabola ildizlari x₁ = 3 va x₂ = -7.",
                        "Simmetriya o‘qi (vertex x): (3 + (-7)) / 2 = -4 / 2 = -2."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "sat_q3",
                    topicTitle = "Data & Percentages",
                    examTag = "SAT",
                    prompt = "Shahar aholisi 2020-yilda 80 000 kishi edi. Har yili aholi 5% ga oshsa, t yildan keyingi aholi sonini ifodalovchi funksiya qaysi?",
                    options = listOf(
                        "P(t) = 80000(0.05)ᵗ",
                        "P(t) = 80000(1.05)ᵗ",
                        "P(t) = 80000 + 4000t",
                        "P(t) = 80000(1.5)ᵗ"
                    ),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Har yili 5% o‘sish ko‘paytuvchisi: 1 + 0.05 = 1.05.",
                        "Eksponensial formula: P(t) = 80000(1.05)ᵗ."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "sat_q4",
                    topicTitle = "Geometry & Trig",
                    examTag = "SAT",
                    prompt = "To‘g‘ri burchakli ABC uchburchakda ∠C = 90° va sin(A) = 7/25. cos(B) nimaga teng?",
                    options = listOf("24/25", "7/25", "7/24", "24/7"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "∠A + ∠B = 90° bo‘lgani uchun cos(B) = sin(A).",
                        "Demak, cos(B) = 7/25."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "sat_q5",
                    topicTitle = "Quadratic Discriminant",
                    examTag = "SAT",
                    prompt = "x² + bx + 16 = 0 tenglama hech qanday haqiqiy yechimga ega bo‘lmasligi uchun b ning musbat butun qiymatlaridan eng kattasi necha?",
                    options = listOf("7", "8", "15", "6"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Haqiqiy yechim yo‘q ⇒ D < 0.",
                        "b² - 4(1)(16) < 0 ⇒ b² < 64 ⇒ -8 < b < 8.",
                        "Eng katta musbat butun qiymat: 7."
                    ),
                    difficultyLabel = "Qiyin"
                ),
                MathQuestion(
                    id = "sat_q6",
                    topicTitle = "Circle Equation",
                    examTag = "SAT",
                    prompt = "(x - 5)² + (y + 2)² = 49 aylananing markazi (h, k) va radiusi r bo‘lsa, h + k + r nimaga teng?",
                    options = listOf("10", "14", "12", "4"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Markaz: h = 5, k = -2.",
                        "Radius: r = √49 = 7.",
                        "h + k + r = 5 + (-2) + 7 = 10."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "sat_q7",
                    topicTitle = "Rational Equations",
                    examTag = "SAT",
                    prompt = "Agar (x + 6) / (x - 2) = 3 bo‘lsa, x ning qiymati nechaga teng?",
                    options = listOf("4", "6", "8", "3"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "x + 6 = 3(x - 2) → x + 6 = 3x - 6.",
                        "2x = 12 → x = 6."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "sat_q8",
                    topicTitle = "Linear Word Problem",
                    examTag = "SAT",
                    prompt = "Do‘kon 1 ta daftar va 2 ta ruchkani 17 000 so‘mga, 3 ta daftar va 2 ta ruchkani 35 000 so‘mga sotadi. 1 ta daftar narxi qancha?",
                    options = listOf("8 000 so‘m", "9 000 so‘m", "10 000 so‘m", "7 500 so‘m"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Ikkinchi tenglamadan birinchisini ayiramiz: 2 ta daftar = 35 000 - 17 000 = 18 000.",
                        "1 ta daftar = 9 000 so‘m."
                    ),
                    difficultyLabel = "Oson"
                )
            )
        ),

        CertificateTrack(
            id = "cert_act",
            examType = TargetExamTrack.ACT,
            title = "ACT Math",
            subtitle = "Tezkor 60 daqiqa / 60 savol formati (1–36 ball)",
            scoreRangeLabel = "1–36 ball",
            durationMinutes = 12,
            descriptionUz = "ACT Math 1 savolga 1 daqiqa tamoyilida ishlaydi. Pre-Algebra, Elementary/Intermediate Algebra, Koordinata va Tekislik Geometriyasi hamda Trigonometriyani qamrab oladi.",
            keyTopics = listOf(
                "Tezkor arifmetika va matritsalar",
                "Koordinata geometriyasi va konus kesimlari",
                "Trigonometriya ayniyatlari",
                "Kombinatorika va ehtimollik"
            ),
            examTips = listOf(
                "ACT da har bir savolga o‘rtacha 60 soniya beriladi — variantlarni o‘rniga qo‘yib tekshirish (Backsolving) usulidan foydalaning.",
                "Matritsalarni qo‘shishda mos elementlarni qo‘shish kifoya."
            ),
            questions = listOf(
                MathQuestion(
                    id = "act_q1",
                    topicTitle = "Koordinata geometriyasi",
                    examTag = "ACT",
                    prompt = "A(2, -3) va B(8, 5) nuqtalar orasidagi masofa nechaga teng?",
                    options = listOf("8", "10", "12", "14"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "d = √[(8 - 2)² + (5 - (-3))²] = √(6² + 8²) = √(36 + 64) = 10."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "act_q2",
                    topicTitle = "Matritsalar",
                    examTag = "ACT",
                    prompt = "Agar A = [[2, -1], [4, 3]] va B = [[5, 2], [-1, 6]] bo‘lsa, A + 2B matritsaning yuqori o‘ng elementi nechaga teng?",
                    options = listOf("1", "3", "5", "4"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Yuqori o‘ng element: A₁₂ + 2·B₁₂ = -1 + 2(2) = -1 + 4 = 3."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "act_q3",
                    topicTitle = "Geometriya",
                    examTag = "ACT",
                    prompt = "Muntazam sakkizburchakning (octagon) bitta ichki burchagi necha gradus?",
                    options = listOf("120°", "135°", "140°", "150°"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Tashqi burchak = 360° / 8 = 45°.",
                        "Ichki burchak = 180° - 45° = 135°."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "act_q4",
                    topicTitle = "Algebra",
                    examTag = "ACT",
                    prompt = "Agar f(x) = 3x - 2 va g(x) = x² + 1 bo‘lsa, f(g(2)) qiymatini toping.",
                    options = listOf("13", "17", "15", "11"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Avval g(2) = 2² + 1 = 5.",
                        "So‘ng f(5) = 3(5) - 2 = 13."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "act_q5",
                    topicTitle = "Trigonometriya",
                    examTag = "ACT",
                    prompt = "(1 - cos²θ) / sinθ ifodani soddalashtiring (sinθ ≠ 0).",
                    options = listOf("cosθ", "sinθ", "tanθ", "1"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "sin²θ + cos²θ = 1 dan 1 - cos²θ = sin²θ.",
                        "sin²θ / sinθ = sinθ."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "act_q6",
                    topicTitle = "Foiz va soliq",
                    examTag = "ACT",
                    prompt = "Kitob 40 dollardan 20% chegirma bilan sotildi, so‘ngra chegirmali narxga 10% soliq qo‘shildi. Yakuniy narx necha dollar?",
                    options = listOf("$35.20", "$36.00", "$34.40", "$32.00"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Chegirmadan keyin: 40 × 0.8 = $32.",
                        "10% soliq bilan: 32 × 1.1 = $35.20."
                    ),
                    difficultyLabel = "Oson"
                )
            )
        ),

        CertificateTrack(
            id = "cert_gre",
            examType = TargetExamTrack.GRE,
            title = "GRE Quantitative",
            subtitle = "Magistratura va PhD grantlari uchun (130–170 ball)",
            scoreRangeLabel = "130–170 ball",
            durationMinutes = 14,
            descriptionUz = "GRE Quant mantiqiy tahlil, sonlar nazariyasi, Quantitative Comparison (A va B kattaliklarni taqqoslash) va ma’lumotlar interpretatsiyasini sinaydi.",
            keyTopics = listOf(
                "Quantitative Comparison strategiyasi",
                "Sonlar nazariyasi va qoldiqlar",
                "Kombinatorika va Normal taqsimot",
                "Tengsizliklar diapazoni"
            ),
            examTips = listOf(
                "Taqqoslash savollarida x = 0, x = 1, x = -1 va x = 1/2 (kasr) qiymatlarini sinab ko‘rishni unutmang!",
                "Normal taqsimotda ±1σ oralig‘iga ~68% ma’lumot tushadi."
            ),
            questions = listOf(
                MathQuestion(
                    id = "gre_q1",
                    topicTitle = "Quantitative Comparison",
                    examTag = "GRE",
                    prompt = "x > 0 bo‘lsa, A = x² + 1 va B = 2x kattaliklarini taqqoslang.",
                    options = listOf(
                        "A kattaroq",
                        "B kattaroq",
                        "Ikkalasi teng",
                        "Ma’lumot yetarli emas"
                    ),
                    correctIndex = 3,
                    explanationSteps = listOf(
                        "x = 1 da A = 2, B = 2 (Teng).",
                        "x = 3 da A = 10, B = 6 (A katta).",
                        "Turli x larda natija o‘zgargani uchun: Ma’lumot yetarli emas."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gre_q2",
                    topicTitle = "Sonlar nazariyasi",
                    examTag = "GRE",
                    prompt = "n musbat butun sonini 7 ga bo‘lganda qoldiq 4 chiqsa, 3n + 5 sonini 7 ga bo‘lgandagi qoldiq nechaga teng?",
                    options = listOf("2", "3", "4", "5"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Eng kichik n = 4 ni olamiz.",
                        "3(4) + 5 = 17.",
                        "17 ÷ 7 = 2 (qoldiq 3)."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gre_q3",
                    topicTitle = "Ish unumdorligi",
                    examTag = "GRE",
                    prompt = "1-nasos hovuzni 6 soatda, 2-nasos 3 soatda to‘ldiradi. Ikkalasi birga hovuzni necha soatda to‘ldiradi?",
                    options = listOf("2 soat", "4.5 soat", "1.5 soat", "2.5 soat"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "1 soatda birgalikda: 1/6 + 1/3 = 1/6 + 2/6 = 3/6 = 1/2.",
                        "Demak, to‘liq hovuz 2 soatda to‘ladi."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "gre_q4",
                    topicTitle = "Geometriya",
                    examTag = "GRE",
                    prompt = "Kvadratning diagonali 6√2 ga teng. Uning perimetrini toping.",
                    options = listOf("12", "24", "36", "12√2"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Diagonal d = a√2 = 6√2 ⇒ tomon a = 6.",
                        "Perimetr P = 4a = 24."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "gre_q5",
                    topicTitle = "Ehtimollik",
                    examTag = "GRE",
                    prompt = "1 dan 20 gacha bo‘lgan butun sonlardan tasodifiy bittasi tanlandi. Uning 2 ga yoki 3 ga bo‘linish ehtimoli qancha?",
                    options = listOf("13/20", "16/20", "10/20", "14/20"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "2 ga bo‘linadiganlar: 10 ta. 3 ga bo‘linadiganlar: 6 ta.",
                        "6 ga (ikkalasiga) bo‘linadiganlar: 3 ta (6, 12, 18).",
                        "Jami = 10 + 6 - 3 = 13 ta → 13/20."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gre_q6",
                    topicTitle = "Ketma-ket sonlar",
                    examTag = "GRE",
                    prompt = "5 ta ketma-ket toq sonning yig‘indisi 115 ga teng. Ularning eng kattasini toping.",
                    options = listOf("23", "25", "27", "29"),
                    correctIndex = 2,
                    explanationSteps = listOf(
                        "O‘rtadagi son = 115 / 5 = 23.",
                        "To‘plam: 19, 21, 23, 25, 27 → eng kattasi 27."
                    ),
                    difficultyLabel = "Oson"
                )
            )
        ),

        CertificateTrack(
            id = "cert_gmat",
            examType = TargetExamTrack.GMAT,
            title = "GMAT Focus Quant",
            subtitle = "MBA va biznes maktablari uchun (60–90 ball)",
            scoreRangeLabel = "60–90 ball",
            durationMinutes = 14,
            descriptionUz = "GMAT Focus Edition mantiqiy fikrlash, foizlar, aralashmalar, tezlik masalalari va biznes-tahlilga asoslangan masalalarni o‘z ichiga oladi.",
            keyTopics = listOf(
                "Aralashmalar va konsentratsiya",
                "Tezlik, vaqt va nisbiy harakat",
                "Sonlar nazariyasi va mantiqiy cheklovlar",
                "Kombinatorika va Data Insights"
            ),
            examTips = listOf(
                "Aralashma masalalarida sof modda miqdorini tenglashtirish eng tezkor usuldir.",
                "O‘rtacha tezlik hech qachon tezliklarning oddiy o‘rta arifmetigi emas: V_o‘rta = Jami masofa / Jami vaqt."
            ),
            questions = listOf(
                MathQuestion(
                    id = "gmat_q1",
                    topicTitle = "O‘rtacha tezlik",
                    examTag = "GMAT",
                    prompt = "Avtomobil A dan B ga 60 km/soat tezlikda borib, 40 km/soat tezlikda qaytdi. Butun yo‘ldagi o‘rtacha tezlik qancha?",
                    options = listOf("50 km/soat", "48 km/soat", "45 km/soat", "52 km/soat"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Masofani 120 km deb olaylik: borish 2 soat, qaytish 3 soat.",
                        "O‘rtacha tezlik = (120 + 120) / (2 + 3) = 240 / 5 = 48 km/soat."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gmat_q2",
                    topicTitle = "Aralashmalar",
                    examTag = "GMAT",
                    prompt = "20% li 30 litr tuzli eritmaga necha litr 50% li eritma qo‘shilsa, 30% li eritma hosil bo‘ladi?",
                    options = listOf("10 litr", "15 litr", "20 litr", "12 litr"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "0.2(30) + 0.5x = 0.3(30 + x) → 6 + 0.5x = 9 + 0.3x.",
                        "0.2x = 3 → x = 15 litr."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gmat_q3",
                    topicTitle = "Foyda va zarar",
                    examTag = "GMAT",
                    prompt = "Tovar tannarxidan 25% foyda bilan $150 ga sotildi. Agar 10% zarar bilan sotilganda narxi qancha bo‘lar edi?",
                    options = listOf("$108", "$110", "$115", "$105"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "1.25 × Tannarx = 150 → Tannarx = $120.",
                        "10% zarar: 120 × 0.90 = $108."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "gmat_q4",
                    topicTitle = "Tenglama",
                    examTag = "GMAT",
                    prompt = "Agar x + 1/x = 4 bo‘lsa, x² + 1/x² qiymatini toping.",
                    options = listOf("16", "14", "18", "12"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "(x + 1/x)² = x² + 2 + 1/x² = 16.",
                        "x² + 1/x² = 16 - 2 = 14."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "gmat_q5",
                    topicTitle = "To‘plamlar",
                    examTag = "GMAT",
                    prompt = "100 nafar xodimdan 70 tasi Excel, 55 tasi SQL biladi. Har bir xodim kamida bitta dasturni bilsa, nechtasi ikkalasini ham biladi?",
                    options = listOf("15", "25", "30", "20"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "|A ∩ B| = 70 + 55 - 100 = 25."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "gmat_q6",
                    topicTitle = "Oxirgi raqam",
                    examTag = "GMAT",
                    prompt = "7²⁰²⁶ soni qaysi raqam bilan tugaydi?",
                    options = listOf("1", "3", "7", "9"),
                    correctIndex = 3,
                    explanationSteps = listOf(
                        "7 ning oxirgi raqamlari davri 4: 7, 9, 3, 1.",
                        "2026 = 4 × 506 + 2 (qoldiq 2).",
                        "2-o‘rindagi oxirgi raqam: 9."
                    ),
                    difficultyLabel = "Qiyin"
                )
            )
        ),

        CertificateTrack(
            id = "cert_ap_calc",
            examType = TargetExamTrack.AP_CALCULUS,
            title = "AP Calculus AB / BC",
            subtitle = "Xalqaro kollej kredit imtihoni (1–5 ball)",
            scoreRangeLabel = "1–5 ball",
            durationMinutes = 15,
            descriptionUz = "Limitlar, hosila, bog‘liq o‘zgarish tezliklari (Related Rates), optimallashtirish, aniq integral va differensial tenglamalar.",
            keyTopics = listOf(
                "Limitlar va L'Hôpital qoidasi",
                "Chain Rule, Product va Quotient Rule",
                "Aniq integral va egri chiziq yuzasi",
                "O‘rtacha qiymat teoremasi (MVT)"
            ),
            examTips = listOf(
                "0/0 noaniqligida L'Hôpital qoidasi: surat va maxrajdan alohida hosila oling!",
                "Urinma tenglamasini tuzishda ham nuqta (x₀, y₀), ham slope f'(x₀) kerak bo‘ladi."
            ),
            questions = listOf(
                MathQuestion(
                    id = "ap_q1",
                    topicTitle = "Quotient Rule",
                    examTag = "AP Calculus",
                    prompt = "f(x) = ln(x) / x bo‘lsa, f'(e) qiymatini toping.",
                    options = listOf("0", "1/e²", "-1/e²", "2/e²"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "f'(x) = [(1/x)·x - ln(x)·1] / x² = (1 - ln x) / x².",
                        "x = e da: (1 - 1) / e² = 0."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "ap_q2",
                    topicTitle = "Integral va Yuza",
                    examTag = "AP Calculus",
                    prompt = "y = x² va y = 2x chiziqlari bilan chegaralangan soha yuzini toping.",
                    options = listOf("4/3", "2/3", "8/3", "1"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Kesishish nuqtalari: x² = 2x ⇒ x = 0 va x = 2.",
                        "S = ∫₀² (2x - x²) dx = [x² - x³/3]₀² = 4 - 8/3 = 4/3."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "ap_q3",
                    topicTitle = "L'Hôpital qoidasi",
                    examTag = "AP Calculus",
                    prompt = "lim(x→0) (e²ˣ - 1) / x limitni toping.",
                    options = listOf("1", "2", "e", "0"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "0/0 noaniqligi. Surat hosilasi: 2e²ˣ, maxraj hosilasi: 1.",
                        "x = 0 da: 2e⁰ / 1 = 2."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "ap_q4",
                    topicTitle = "Tezlik va Tezlanish",
                    examTag = "AP Calculus",
                    prompt = "Jismning koordinatasi s(t) = t³ - 6t² + 9t. t = 3 da jismning tezlanishi a(3) nechaga teng?",
                    options = listOf("0", "6", "12", "-6"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Tezlik v(t) = s'(t) = 3t² - 12t + 9.",
                        "Tezlanish a(t) = v'(t) = 6t - 12.",
                        "a(3) = 6(3) - 12 = 6."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "ap_q5",
                    topicTitle = "O‘rniga qo‘yish integrali",
                    examTag = "AP Calculus",
                    prompt = "∫₀¹ 2x·e^(x²) dx integral qiymatini toping.",
                    options = listOf("e - 1", "e", "2e - 1", "e + 1"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "u = x² ⇒ du = 2x dx.",
                        "Chegaralar: x = 0 → u = 0; x = 1 → u = 1.",
                        "∫₀¹ eᵘ du = e¹ - e⁰ = e - 1."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "ap_q6",
                    topicTitle = "Differensial tenglama",
                    examTag = "AP Calculus",
                    prompt = "dy/dx = 2y va y(0) = 5 shartini qanoatlantiruvchi funksiya qaysi?",
                    options = listOf("y = 5e²ˣ", "y = 2e⁵ˣ", "y = 5 + 2x", "y = 5eˣ"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "dy/dx = ky yechimi y = C·e^(kx).",
                        "k = 2 va y(0) = C = 5 ⇒ y = 5e²ˣ."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        ),

        CertificateTrack(
            id = "cert_milliy",
            examType = TargetExamTrack.MILLIY,
            title = "Milliy Sertifikat / DTM",
            subtitle = "O‘zbekiston OTM kirish va A+ sertifikat standarti",
            scoreRangeLabel = "0–100% (A+)",
            durationMinutes = 16,
            descriptionUz = "O‘zbekiston Milliy Sertifikat va DTM standartiga mos: sonlar nazariyasi, ildiz va modullar, progressiyalar, trigonometriya, logarifm, geometriya va boshlang‘ich funksiya.",
            keyTopics = listOf(
                "Arifmetik va geometrik progressiya",
                "Irratsional va logarifmik tenglamalar",
                "Trigonometrik tenglamalar",
                "Planimetriya, stereometriya va integral"
            ),
            examTips = listOf(
                "Progressiyada Sₙ = [(a₁ + aₙ)/2] · n formulasini eslab qoling.",
                "Logarifmik tengsizliklarda asos 0 < a < 1 bo‘lsa ishora o‘zgarishini va aniqlanish sohasini (ODZ) tekshiring."
            ),
            questions = listOf(
                MathQuestion(
                    id = "mil_q1",
                    topicTitle = "Arifmetik progressiya",
                    examTag = "Milliy Sertifikat",
                    prompt = "Arifmetik progressiyada a₁ = 4 va a₅ = 20 bo‘lsa, dastlabki 10 ta had yig‘indisi S₁₀ ni toping.",
                    options = listOf("220", "240", "180", "200"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "a₅ = a₁ + 4d ⇒ 20 = 4 + 4d ⇒ d = 4.",
                        "a₁₀ = 4 + 9(4) = 40.",
                        "S₁₀ = [(4 + 40) / 2] × 10 = 22 × 10 = 220."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "mil_q2",
                    topicTitle = "Geometrik progressiya",
                    examTag = "Milliy Sertifikat",
                    prompt = "Cheksiz kamayuvchi geometrik progressiyada b₁ = 12 va q = 1/3. Barcha hadlari yig‘indisini toping.",
                    options = listOf("16", "18", "24", "36"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "S = b₁ / (1 - q) = 12 / (1 - 1/3) = 12 / (2/3) = 18."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "mil_q3",
                    topicTitle = "Irratsional tenglama",
                    examTag = "Milliy Sertifikat",
                    prompt = "√(2x + 5) = x - 5 tenglamaning haqiqiy ildizini toping.",
                    options = listOf("2", "10", "2 va 10", "5"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Shart: x - 5 ≥ 0 ⇒ x ≥ 5.",
                        "2x + 5 = (x - 5)² = x² - 10x + 25 → x² - 12x + 20 = 0.",
                        "Ildizlar: 10 va 2. x ≥ 5 shartiga faqat x = 10 mos keladi."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "mil_q4",
                    topicTitle = "Trigonometriya",
                    examTag = "Milliy Sertifikat",
                    prompt = "sin(75°) · cos(75°) ifodaning qiymatini toping.",
                    options = listOf("1/2", "1/4", "√3/4", "√3/2"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "sin(2α) = 2sinα·cosα formuladan: sin(75°)cos(75°) = (1/2)sin(150°).",
                        "sin(150°) = 1/2 ⇒ (1/2)(1/2) = 1/4."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "mil_q5",
                    topicTitle = "Logarifm",
                    examTag = "Milliy Sertifikat",
                    prompt = "log₂(x) + log₂(x - 2) = 3 tenglamani yeching.",
                    options = listOf("4", "-2", "4 va -2", "6"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Aniqlanish sohasi: x > 2.",
                        "log₂[x(x - 2)] = 3 ⇒ x² - 2x = 8 ⇒ x² - 2x - 8 = 0.",
                        "Ildizlar: 4 va -2. x > 2 bo‘lgani uchun javob: 4."
                    ),
                    difficultyLabel = "O‘rta"
                ),
                MathQuestion(
                    id = "mil_q6",
                    topicTitle = "Planimetriya",
                    examTag = "Milliy Sertifikat",
                    prompt = "Rombning diagonallari 12 sm va 16 sm. Rombning perimetri necha sm?",
                    options = listOf("40 sm", "48 sm", "56 sm", "32 sm"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "Diagonallar o‘zaro perpendikulyar va teng ikkiga bo‘linadi: yarimlari 6 va 8 sm.",
                        "Tomoni a = √(6² + 8²) = 10 sm.",
                        "Perimetr = 4 × 10 = 40 sm."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "mil_q7",
                    topicTitle = "Hosila",
                    examTag = "Milliy Sertifikat",
                    prompt = "f(x) = x⁴ - 4x + 5 funksiyaning x = 1 nuqtadagi hosilasi f'(1) ni toping.",
                    options = listOf("0", "4", "-4", "2"),
                    correctIndex = 0,
                    explanationSteps = listOf(
                        "f'(x) = 4x³ - 4.",
                        "f'(1) = 4(1)³ - 4 = 0."
                    ),
                    difficultyLabel = "Oson"
                ),
                MathQuestion(
                    id = "mil_q8",
                    topicTitle = "Stereometriya",
                    examTag = "Milliy Sertifikat",
                    prompt = "Konusning radiusi 6 sm, balandligi 8 sm. Konusning yon sirti yuzini toping.",
                    options = listOf("48π sm²", "60π sm²", "80π sm²", "96π sm²"),
                    correctIndex = 1,
                    explanationSteps = listOf(
                        "Yasovchi l = √(6² + 8²) = 10 sm.",
                        "Yon sirt yuzi S = πRl = π × 6 × 10 = 60π sm²."
                    ),
                    difficultyLabel = "O‘rta"
                )
            )
        )
    )

    fun computeScaledScore(examType: TargetExamTrack, correct: Int, total: Int): Int {
        if (total <= 0) return 0
        val ratio = correct.toDouble() / total.toDouble()
        return when (examType) {
            TargetExamTrack.SAT -> (200 + (ratio * 600)).toInt().coerceIn(200, 800)
            TargetExamTrack.ACT -> (1 + (ratio * 35)).toInt().coerceIn(1, 36)
            TargetExamTrack.GRE -> (130 + (ratio * 40)).toInt().coerceIn(130, 170)
            TargetExamTrack.GMAT -> (60 + (ratio * 30)).toInt().coerceIn(60, 90)
            TargetExamTrack.AP_CALCULUS -> when {
                ratio >= 0.80 -> 5
                ratio >= 0.65 -> 4
                ratio >= 0.50 -> 3
                ratio >= 0.35 -> 2
                else -> 1
            }
            TargetExamTrack.MILLIY -> (ratio * 100).toInt().coerceIn(0, 100)
        }
    }

    val formulaCategories: List<FormulaCategory> = listOf(
        FormulaCategory(
            id = "f_alg",
            title = "Algebra & Kvadrat",
            formulas = listOf(
                FormulaEntry("Kvadrat formula", "x = (-b ± √(b² - 4ac)) / 2a", "Diskriminant D = b² - 4ac"),
                FormulaEntry("Viyet teoremasi", "x₁ + x₂ = -b/a,  x₁·x₂ = c/a", "SAT da eng ko‘p ishlatiladigan formula"),
                FormulaEntry("Parabola uchi", "x₀ = -b / (2a),  y₀ = f(x₀)", "Vertex ko‘rinishi: a(x - h)² + k"),
                FormulaEntry("Kvadratlar ayirmasi", "a² - b² = (a - b)(a + b)", "Tezkor soddalashtirish uchun"),
                FormulaEntry("Arifmetik progressiya", "aₙ = a₁ + (n - 1)d,  Sₙ = n(a₁ + aₙ)/2", "Milliy Sertifikat va GRE uchun"),
                FormulaEntry("Geometrik progressiya", "bₙ = b₁·qⁿ⁻¹,  S_∞ = b₁/(1 - q)", "|q| < 1 bo‘lganda cheksiz yig‘indi")
            )
        ),
        FormulaCategory(
            id = "f_geom",
            title = "Geometriya & Trig",
            formulas = listOf(
                FormulaEntry("Pifagor teoremasi", "a² + b² = c²", "Uchliklar: 3-4-5, 5-12-13, 8-15-17"),
                FormulaEntry("Maxsus uchburchaklar", "30-60-90: x, x√3, 2x  |  45-45-90: x, x, x√2", "SAT Geometry bo‘limi asosi"),
                FormulaEntry("Aylana tenglamasi", "(x - h)² + (y - k)² = r²", "Markaz (h, k), radius r"),
                FormulaEntry("Kosinuslar teoremasi", "c² = a² + b² - 2ab·cos(C)", "Ixtiyoriy uchburchak uchun"),
                FormulaEntry("Asosiy ayniyat", "sin²θ + cos²θ = 1,  sin(x) = cos(90° - x)", "O‘tkir burchaklar uchun")
            )
        ),
        FormulaCategory(
            id = "f_stat",
            title = "Statistika & Logarifm",
            formulas = listOf(
                FormulaEntry("Logarifm xossalari", "logₐ(xy) = logₐx + logₐy", "logₐ(xⁿ) = n·logₐx"),
                FormulaEntry("Kombinatsiya", "C(n, k) = n! / [k!(n - k)!]", "Tartib muhim bo‘lmaganda"),
                FormulaEntry("Permutatsiya", "P(n, k) = n! / (n - k)!", "Tartib muhim bo‘lganda"),
                FormulaEntry("Shartli ehtimollik", "P(A | B) = P(A ∩ B) / P(B)", "Jadval masalalarida")
            )
        ),
        FormulaCategory(
            id = "f_calc",
            title = "Calculus Master",
            formulas = listOf(
                FormulaEntry("Asosiy limitlar", "lim(x→0) sin(x)/x = 1", "lim(x→∞) (1 + 1/x)ˣ = e"),
                FormulaEntry("Hosila (Daraja)", "(xⁿ)' = n·xⁿ⁻¹", "(eˣ)' = eˣ,  (ln x)' = 1/x"),
                FormulaEntry("Chain Rule", "[f(g(x))]' = f'(g(x)) · g'(x)", "Murakkab funksiya hosilasi"),
                FormulaEntry("Aniq integral", "∫ₐᵇ f(x) dx = F(b) - F(a)", "Nyuton-Leybnis formulasi")
            )
        )
    )
}
