package staff_assignment;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.NoSuchElementException;

import medical_action_tracking.TreatmentHistory;
import medical_action_tracking.TreatmentRecord;

public class StaffRoster {
    // Week 3 stack-based history; every assignment is recorded here so it can be undone.
    private final TreatmentHistory history = new TreatmentHistory();

    // Outer map groups staff by seniority; each inner set is sorted by StaffMember's ordering.
    private final TreeMap<Seniority, TreeSet<StaffMember>> tiers = new TreeMap<>();

    // TODO: Add the staff member to the correct tier, and reject a duplicate staffId.
    public void addStaff(StaffMember staff) {
        if (findByID(staff.getStaffID()) != null) {
            throw new IllegalArgumentException("Duplicate staff ID");
        }
        Seniority level = staff.getSeniority();
        if (tiers.get(level) == null) {
            tiers.put(level, new TreeSet<StaffMember>());
        }
        TreeSet<StaffMember> selectedArea = tiers.get(level);
        selectedArea.add(staff);
    }

    // TODO: Pick the least-loaded staff member at or above "required", raise their load by 1, and return them.
    public StaffMember assign(Seniority required) {
        int recordLoad = Integer.MAX_VALUE;
        int testLoad = -1;
        StaffMember recordStaff = null;
        for (TreeSet<StaffMember> groupTested : tiers.tailMap(required, true).values()) {

            for (StaffMember staff : groupTested) {
                testLoad = staff.getCaseLoad();
                if (testLoad < recordLoad) {
                    recordStaff = staff;
                    recordLoad = testLoad;
                }
            }
        }
        if (recordStaff == null) {
            throw new NoSuchElementException("No qualified staff available");
        }

        int caseLoad = recordStaff.getCaseLoad();
        changeLoad(recordStaff, caseLoad + 1);
        return recordStaff;
    }

    // TODO: Lower the staff member's load by 1 (never below 0) and keep the tree ordered.
    public void release(StaffMember staff) {
        int getLoad = staff.getCaseLoad();

        if (getLoad > 0) {
            changeLoad(staff, getLoad - 1);
        }
    }

    // TODO: Return how many staff members are in the roster.
    public int size() {
        int counter = 0;
        for (TreeSet<StaffMember> groupTested : tiers.values()) {
            for (StaffMember poopy : groupTested) {
                counter++;
            }
        }
        return counter;
    }

    // TODO: Print each tier, most senior first, listing its staff in order.
    public void displayRoster() {
    for (Seniority level : tiers.descendingKeySet()) {
        System.out.println("Seniority: " + level);

        for (StaffMember member : tiers.get(level)) {
            System.out.println(member);
        }
    }
}

    // TODO: Remove the member, set the new load, then add them back (the only safe way to change a load).
    private void changeLoad(StaffMember member, int newLoad) {
        Seniority level = member.getSeniority();
        TreeSet<StaffMember> fart = tiers.get(level);
        if (fart != null && fart.remove(member)) {
            member.setCaseLoad(newLoad);
            fart.add(member);
        }
    }

    // TODO: Call assign(), record the treatment in history with the chosen staffId, and return that staff member.
    public StaffMember assignTreatment(String patientID, String treatment, String timestamp, Seniority required) {
        StaffMember chosen = assign(required);
        if (chosen == null) { 
            return null;
        }

        history.addTreatment(patientID, treatment, timestamp, chosen.getStaffID());
        return chosen;
    }

    // TODO: Pop the globally most recent treatment with your Week 3 undo, release whoever performed it, and return them (null if none).
    public StaffMember undoLastAssignment() {
        TreatmentRecord bort = history.undoLastAction();
        if (bort == null) {
            return null;
        }
        String borter = bort.getPerformedBy();
        StaffMember staff = findByID(borter);

        if (staff != null) {
        release(staff);
        }

        return staff;
    }

    // TODO: Return the staff member with this staffId, or null if they are not in the roster.
    private StaffMember findByID(String staffID) {
        for (TreeSet<StaffMember> chosenTree : tiers.values()) {
            for (StaffMember staff : chosenTree) {
                if (staff.getStaffID().equals(staffID)) {
                    return staff;
                }
            }
        }
        return null;
    }
}
