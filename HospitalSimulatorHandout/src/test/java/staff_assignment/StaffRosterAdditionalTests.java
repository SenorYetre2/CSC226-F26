
package staff_assignment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StaffRosterAdditionalTests {

    @Test
    void repeatedAssignmentsDistributeWork() {
        StaffRoster roster = new StaffRoster();

        StaffMember a = new StaffMember("S01", "A", Seniority.TECH);
        StaffMember b = new StaffMember("S02", "B", Seniority.TECH);
        StaffMember c = new StaffMember("S03", "C", Seniority.TECH);

        roster.addStaff(a);
        roster.addStaff(b);
        roster.addStaff(c);

        roster.assign(Seniority.TECH);
        roster.assign(Seniority.TECH);
        roster.assign(Seniority.TECH);

        assertEquals(1, a.getCaseLoad());
        assertEquals(1, b.getCaseLoad());
        assertEquals(1, c.getCaseLoad());
    }

    @Test
    void staffCountRemainsConstant() {
        StaffRoster roster = new StaffRoster();

        StaffMember a = new StaffMember("S01", "A", Seniority.TECH);
        StaffMember b = new StaffMember("S02", "B", Seniority.NURSE);
        StaffMember c = new StaffMember("S03", "C", Seniority.ATTENDING);

        roster.addStaff(a);
        roster.addStaff(b);
        roster.addStaff(c);

        assertEquals(3, roster.size());

        for (int i = 0; i < 10; i++) {
            roster.assign(Seniority.TECH);
        }

        assertEquals(3, roster.size());

        roster.release(a);
        roster.release(b);
        roster.release(c);

        assertEquals(3, roster.size());
    }

    @Test
    void equalLoadsPreferLowerSeniority() {
        StaffRoster roster = new StaffRoster();

        StaffMember tech = new StaffMember("T1", "Tech", Seniority.TECH);
        StaffMember attending = new StaffMember("A1", "Attending", Seniority.ATTENDING);

        roster.addStaff(tech);
        roster.addStaff(attending);

        assertSame(tech, roster.assign(Seniority.TECH));
    }

    @Test
    void multipleUndosFollowLIFO() {
        StaffRoster roster = new StaffRoster();

        StaffMember tech = new StaffMember("T1", "Tech", Seniority.TECH);
        StaffMember attending = new StaffMember("A1", "Attending", Seniority.ATTENDING);

        roster.addStaff(tech);
        roster.addStaff(attending);

        StaffMember first = roster.assignTreatment(
            "P1", "X-Ray", "10:00", Seniority.TECH
        );

        StaffMember second = roster.assignTreatment(
            "P2", "Surgery", "10:15", Seniority.ATTENDING
        );

        assertSame(second, roster.undoLastAssignment());
        assertSame(first, roster.undoLastAssignment());
        assertNull(roster.undoLastAssignment());

        assertEquals(0, first.getCaseLoad());
        assertEquals(0, second.getCaseLoad());
    }
}
