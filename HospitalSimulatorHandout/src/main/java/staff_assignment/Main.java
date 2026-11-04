package staff_assignment;

public class Main {
    public static void main(String[] args) {

        StaffRoster roster = new StaffRoster();

        // Add staff
        StaffMember staff1 = new StaffMember("S001", "John", Seniority.TECH);
        StaffMember staff2 = new StaffMember("S002", "Sarah", Seniority.NURSE);
        StaffMember staff3 = new StaffMember("S003", "Mike", Seniority.ATTENDING);

        roster.addStaff(staff1);
        roster.addStaff(staff2);
        roster.addStaff(staff3);

        System.out.println("INITIAL ROSTER");
        roster.displayRoster();

        // Assign treatments
        System.out.println("\nASSIGNING TREATMENTS");

        StaffMember assigned1 = roster.assignTreatment(
            "P001", "X-Ray", "10:00", Seniority.TECH
        );

        StaffMember assigned2 = roster.assignTreatment(
            "P002", "Pee Test", "10:15", Seniority.NURSE
        );

        StaffMember assigned3 = roster.assignTreatment(
            "P003", "Knee Surgery", "10:30", Seniority.ATTENDING
        );

        // Display performedBy staff IDs
        System.out.println("P001 X-Ray performedBy: " +
            (assigned1 == null ? "None" : assigned1.getStaffID()));

        System.out.println("P002 Pee Test performedBy: " +
            (assigned2 == null ? "None" : assigned2.getStaffID()));

        System.out.println("P003 Knee Surgery performedBy: " +
            (assigned3 == null ? "None" : assigned3.getStaffID()));

        // Undo most recent assignment
        System.out.println("\nUNDOING LAST ASSIGNMENT");

        StaffMember undone = roster.undoLastAssignment();

        if (undone != null) {
            System.out.println("Undid assignment for: " + undone.getStaffID());
        }

        // Release a case
        System.out.println("\nRELEASING CASE");

        if (assigned1 != null) {
            roster.release(assigned1);
            System.out.println("Released case from: " + assigned1.getStaffID());
        }

        // Final roster
        System.out.println("\nFINAL ROSTER");
        roster.displayRoster();

        System.out.println("\nTotal staff: " + roster.size());
    }
}