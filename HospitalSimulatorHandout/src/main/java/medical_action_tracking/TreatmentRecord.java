package medical_action_tracking;

public class TreatmentRecord {
    private String patientID;
    private String treatmentName;
    private String timestamp;
    private String performedBy;

    public TreatmentRecord(String patientID, String treatmentName, String timestamp, String performedBy) {
        this.patientID = patientID;
        this.treatmentName = treatmentName;
        this.timestamp = timestamp;
        this.performedBy = performedBy;
    }

    public TreatmentRecord(String patientID, String treatmentName, String timestamp) {
        this(patientID, treatmentName, timestamp, null);
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public String getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        // TODO: Return "timestamp | patientID | treatmentName".
        return timestamp + " | " + patientID + " | " + treatmentName;
    }
}
