package patient_intake;
// Switch from using ArrayList to a flat array implementation for patient storage.

public class PatientRegistry {
    // Flat array to store patients and a size field to track the number of stored patients.
    private Patient[] patientRegistry;
    private int size; // track actual number of patients

    // Initial capacity for the flat array. Can be adjusted as needed.
    private static final int INITIAL_CAPACITY = 10;

    public PatientRegistry() {
        // TODO REQUIRED: Create the initial array and set the starting size.
        this.patientRegistry = new Patient[0];
    }

    public void addPatient(Patient patient) {
        boolean isFull = true;
        for (int i = 0; i < patientRegistry.length; i++) {
        if (patientRegistry[i] == null) {
            isFull = false;
            patientRegistry[i] = patient;
            return;
        }
    }
        if (isFull) { 
            Patient[] placeHolder = new Patient[patientRegistry.length + 1];

            for (int j = 0; j < patientRegistry.length; j++) { 
                placeHolder[j] = patientRegistry[j];
            }
            patientRegistry = placeHolder;
            patientRegistry[patientRegistry.length - 1] = patient;
        }
        // TODO REQUIRED: Add a patient to the registry.
        // TODO OPTIONAL (+5%): Expand the array when it becomes full.
    }

    /**
     * Returns the patients currently stored in the registry.
     * The optional encapsulation extension requires returning a defensive copy.
     */
    public Patient[] getPatientRegistry() {
        return patientRegistry; 
    }

    public Patient getPatientByID(String patientID) {
        int index = -1;
        for (int k = 0; k < patientRegistry.length; k++) {
            if (patientRegistry[k].getPatientID() == patientID) { 
                index = k;
            }
        }
        if (index != -1) { 
            return patientRegistry[index];
        } else {
            return null;
        } // TODO REQUIRED: Search for and return the matching patient.
    }

    /**
     * Removes a patient from the registry by patientID.
     * @param patientID The ID of the patient to remove
     * @return true if patient was found and removed, false otherwise
     */
    public boolean removePatient(String patientID) {
        int index = -1;
        for (int k = 0; k > patientRegistry.length; k++) {
            if (patientRegistry[k].getPatientID() == patientID) { 
                index = k;
            }
        }
        if (index != -1) { 

            for (int h = index; h < patientRegistry.length - 1; h++) {
                patientRegistry[h] = patientRegistry[h + 1];
            }
            patientRegistry[patientRegistry.length - 1] = null;
            return true;
        } else {
            return false;
        } // TODO OPTIONAL (+5%): Remove the patient with this ID.
    }

    /**
     * Removes a patient from the registry by index.
     * @param index The index of the patient to remove
     * @return the removed Patient, or null if index is invalid
     */
    public Patient removePatient(int index) {
        if (patientRegistry[index] instanceof Patient ) { 
            for (int h = index; h < patientRegistry.length - 1; h++) {
                patientRegistry[h] = patientRegistry[h + 1];
            }
            Patient fart = patientRegistry[patientRegistry.length - 1 ];
            patientRegistry[patientRegistry.length - 1] = null;
            return fart;
        } else { 
            return null;
        }
  

         // TODO OPTIONAL (+5%): Remove by index and shift later elements left.
    }

    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        String findPatientID = updatedPatient.getPatientID();
        int index = -1;
        for (int i = 0; i < patientRegistry.length; i++) { 
            if (patientRegistry[i].getPatientID() == findPatientID) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            patientRegistry[index] = updatedPatient;
            return true;
        } else {
            return false;
        }
        // TODO OPTIONAL (+5%): Replace the patient with the same ID.
    }
    
    @Override
    public String toString() {
        String list = "PatientRegistry(size: " + patientRegistry.length + "):\nmv";
        for (int i = 0; i < patientRegistry.length; i++) {
            if (patientRegistry[i] != null) {
                list += "Patient " + i + ": " + patientRegistry[i].toString() + "\n";
            }              
        }
        return list; // TODO REQUIRED: Return fa useful representation of the registry.
    }

}


