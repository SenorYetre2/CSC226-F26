package staff_assignment;

public class StaffMember implements Comparable<StaffMember> {
    private final String staffID;
    private final String name;
    private final Seniority seniority;
    private int caseLoad;

    public StaffMember(String staffID, String name, Seniority seniority) {
        this.staffID = staffID;
        this.name = name;
        this.seniority = seniority;
        this.caseLoad = 0;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public int getCaseLoad() {
        return caseLoad;
    }

    // Package-private so only StaffRoster can change the load (see "Updating a Load" in the assignment).
    void setCaseLoad(int caseLoad) {
        this.caseLoad = caseLoad;
    }

// Order by caseLoad (lowest first), then by staffId to break ties.
@Override
public int compareTo(StaffMember other) {
    int loadComparison = Integer.compare(this.caseLoad, other.caseLoad);
    
    if (loadComparison != 0) {
        return loadComparison;
    }
    return this.staffID.compareTo(other.staffID);
}
    @Override
    public String toString() {
        return name + " (" + staffID + ", " + seniority + ", load " + caseLoad + ")";
    }
}
