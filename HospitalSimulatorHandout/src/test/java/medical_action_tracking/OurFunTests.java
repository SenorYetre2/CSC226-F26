package medical_action_tracking;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.*;

class StudentCareTests {

    @Test
    void allStructuresStartEmpty() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        LinkedStack<String> stack = new LinkedStack<>();
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        TreatmentHistory history = new TreatmentHistory();
        assertTrue(queue.isEmpty());
        assertTrue(stack.isEmpty());
        assertTrue(waitingRoom.isEmpty());
        assertEquals("[]", history.displayHistory());
        assertNull(queue.dequeue());
        assertNull(stack.pop());
        assertNull(waitingRoom.nextPatient());
        assertNull(history.undoLastAction());
    }

    @Test
    void queueTransitionsFromEmptyToOneAndBackToEmpty() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertTrue(queue.isEmpty());

        queue.enqueue("patient");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals("patient", queue.peekFront());

        assertEquals("patient", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peekFront());
    }

    @Test
    void waitingRoomPreservesFifoForMultiplePatientsInSameLane() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();

        Patient first = patient("P101", 2);
        Patient second = patient("P102", 2);
        Patient third = patient("P103", 2);
        waitingRoom.addPatient(first);
        waitingRoom.addPatient(second);
        waitingRoom.addPatient(third);

        assertSame(first, waitingRoom.nextPatient());
        assertSame(second, waitingRoom.nextPatient());
        assertSame(third, waitingRoom.nextPatient());
        assertTrue(waitingRoom.isEmpty());
    }

    @Test
    void waitingRoomPrioritizesLevelOneWhileKeepingFifoInAnotherLane() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();

        Patient levelFour = patient("P401", 4);
        Patient firstLevelTwo = patient("P201", 2);
        Patient levelOne = patient("P101", 1);
        Patient secondLevelTwo = patient("P202", 2);

        waitingRoom.addPatient(levelFour);
        waitingRoom.addPatient(firstLevelTwo);
        waitingRoom.addPatient(levelOne);
        waitingRoom.addPatient(secondLevelTwo);
        assertSame(levelOne, waitingRoom.nextPatient());
        assertSame(firstLevelTwo, waitingRoom.nextPatient());
        assertSame(secondLevelTwo, waitingRoom.nextPatient());
        assertSame(levelFour, waitingRoom.nextPatient());
    }

    @Test
    void stackReturnsThreeItemsInReverseInsertionOrder() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("first");
        stack.push("second");
        stack.push("third");
        assertEquals(3, stack.size());

        assertEquals("third", stack.pop());
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void historyDisplayDoesNotRemoveNewestRecordBeforeUndo() {
        TreatmentHistory history = new TreatmentHistory();

        history.addTreatment("P001", "Assessment", "2026-09-27 10:00");
        history.addTreatment("P002", "Blood test", "2026-09-27 10:05");
        history.addTreatment("P003", "X-ray", "2026-09-27 10:10");

        String beforeDisplay = history.displayHistory();
        String afterDisplay = history.displayHistory();
        assertEquals(beforeDisplay, afterDisplay);
        TreatmentRecord newest = history.undoLastAction();
        assertEquals("P003", newest.getPatientID());
        assertEquals("X-ray", newest.getTreatmentName());
    }

    private Patient patient(String patientID, int triageLevel) {
        return new Patient(patientID, "Test", "Patient", 30, "Checkup", triageLevel,
                "Waiting", "ER-001", 9, "INS-001");
    }
}