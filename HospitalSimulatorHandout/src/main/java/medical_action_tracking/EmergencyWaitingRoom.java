package medical_action_tracking;

import patient_intake.Patient;

public class EmergencyWaitingRoom {

    private int level1Capacity = 50;
    private int level2Capacity = 60;
    private int level3Capacity = 67;
    private int level4Capacity = 68;
    private LinkedQueue<Patient> level1;
    private LinkedQueue<Patient> level2;
    private LinkedQueue<Patient> level3;
    private LinkedQueue<Patient> level4;

    public EmergencyWaitingRoom() {
        level1 = new LinkedQueue<>();
        level2 = new LinkedQueue<>();
        level3 = new LinkedQueue<>();
        level4 = new LinkedQueue<>();
        // TODO: Create one empty FIFO queue for each triage level.
        
    }

    /**
     * Add a patient to the FIFO lane matching their triage level.
     *
     * @return true if added; false for null patients or levels outside 1-4
     */

    //HERES MY EXTRA CREDIT CODE
    public boolean addPatient(Patient patient) {
        if (patient == null) {
            return false;
        }
        switch (patient.getTriageLevel()) {
        case 1:
            if (level1.size() < level1Capacity) {
                level1.enqueue(patient);
                return true;
            } else {
                return false;
            }
        case 2:
            if (level2.size() < level2Capacity) {
                level2.enqueue(patient);
                return true;
            } else {
                return false;
            }
        case 3:
            if (level3.size() < level3Capacity) {
                level3.enqueue(patient);
                return true;
            } else {
                return false;
            }
        case 4:
            if (level4.size() < level4Capacity) {
                level4.enqueue(patient);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Remove the next patient, checking triage level 1 before level 2, and so on.
     */
    public Patient nextPatient() {
        if (level1.size() != 0) {
            return level1.dequeue();
        } else if (level2.size() != 0) {
            return level2.dequeue();
        } else if (level3.size() != 0) {
            return level3.dequeue();
        } else if (level4.size() != 0) {
            return level4.dequeue();
        }
        // TODO: Dequeue from the first non-empty lane, or return null if all are empty.
        return null;
    }

    public boolean isEmpty() {
        if (level1.size() == 0 && level2.size() == 0 && level3.size() == 0 && level4.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return level1.size() + level2.size() + level3.size() + level4.size();
    }
}
