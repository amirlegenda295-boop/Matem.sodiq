const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_123";
const BOB_UID = "bob_456";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

test("Unauthenticated user: denied on all user collections", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  const now = new Date(Date.now() - 1000);

  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("examAttempts").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("savedQuestions").get());
  await assertFails(
    unauthDb.collection("users").doc(ALICE_UID).set({
      userId: ALICE_UID,
      displayName: "Hacker",
      targetExam: "SAT",
      totalXp: 0,
      streakDays: 0,
      lessonsCompletedCount: 0,
      quizzesCompletedCount: 0,
      createdAt: now,
      updatedAt: now,
    })
  );
});

test("UserProfile: owner create, partial update, cross-user denial, and shadow field rejection", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const created = new Date(Date.now() - 10000);

  // Valid create
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).set({
      userId: ALICE_UID,
      displayName: "Azizbek",
      targetExam: "SAT",
      totalXp: 120,
      streakDays: 3,
      lessonsCompletedCount: 2,
      quizzesCompletedCount: 1,
      createdAt: created,
      updatedAt: created,
    })
  );

  // Valid partial update
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).update({
      targetExam: "GRE",
      totalXp: 240,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Shadow field rejection
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      isAdmin: true,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Immutable createdAt rejection
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      createdAt: new Date(Date.now() - 500),
      updatedAt: new Date(Date.now() - 500),
    })
  );

  // Cross-user read & write denial
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());
  await assertFails(bobDb.collection("users").doc(ALICE_UID).update({ totalXp: 999999, updatedAt: new Date(Date.now() - 1000) }));
});

test("LessonProgress: owner CRUD and list succeeds; invalid mastery or ghost ID fails", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const created = new Date(Date.now() - 5000);

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").doc("stage1_fractions").set({
      userId: ALICE_UID,
      lessonId: "stage1_fractions",
      stageId: "stage_1",
      completed: true,
      masteryPercent: 100,
      createdAt: created,
      updatedAt: created,
    })
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get()
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").doc("stage1_fractions").update({
      completed: true,
      masteryPercent: 95,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject extra `@DocumentId` id field
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").doc("stage1_pct").set({
      id: "stage1_pct",
      userId: ALICE_UID,
      lessonId: "stage1_pct",
      stageId: "stage_1",
      completed: false,
      masteryPercent: 50,
      createdAt: created,
      updatedAt: created,
    })
  );

  // Reject out-of-range masteryPercent
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").doc("stage1_pct").set({
      userId: ALICE_UID,
      lessonId: "stage1_pct",
      stageId: "stage_1",
      completed: true,
      masteryPercent: 150,
      createdAt: created,
      updatedAt: created,
    })
  );

  // Cross-user list denial
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get());
});

test("ExamAttempts: create & list succeed; update blocked; invalid score or future timestamp fails", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const created = new Date(Date.now() - 2000);

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_1").set({
      userId: ALICE_UID,
      examType: "SAT",
      examTitle: "Digital SAT Math Mini Mock",
      correctCount: 9,
      totalCount: 10,
      scaledScore: 760,
      durationSeconds: 420,
      createdAt: created,
      updatedAt: created,
    })
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").get()
  );

  // Immutable attempts cannot be updated
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_1").update({
      scaledScore: 800,
    })
  );

  // correctCount > totalCount must fail
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_2").set({
      userId: ALICE_UID,
      examType: "SAT",
      examTitle: "Tampered",
      correctCount: 12,
      totalCount: 10,
      scaledScore: 800,
      durationSeconds: 100,
      createdAt: created,
      updatedAt: created,
    })
  );

  // Future timestamp must fail
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_3").set({
      userId: ALICE_UID,
      examType: "ACT",
      examTitle: "Future Test",
      correctCount: 5,
      totalCount: 10,
      scaledScore: 24,
      durationSeconds: 100,
      createdAt: new Date(Date.now() + 60000),
      updatedAt: new Date(Date.now() + 60000),
    })
  );
});

test("SavedQuestions: create, note update, list, delete succeed; cross-user blocked", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const created = new Date(Date.now() - 2000);

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("savedQuestions").doc("q_sat_1").set({
      userId: ALICE_UID,
      questionId: "q_sat_1",
      topicTitle: "Kvadrat tenglamalar",
      examTag: "SAT",
      prompt: "If 2x^2 - 8x + k = 0 has one real solution, find k.",
      note: "D = b^2 - 4ac = 0 formulasini esla!",
      createdAt: created,
      updatedAt: created,
    })
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("savedQuestions").doc("q_sat_1").update({
      note: "64 - 8k = 0 -> k = 8",
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("savedQuestions").doc("q_sat_1").delete());
  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).collection("savedQuestions").doc("q_sat_1").delete());
});
