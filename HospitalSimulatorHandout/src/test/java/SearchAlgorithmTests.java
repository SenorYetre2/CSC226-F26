

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;
import triage_efficiency.EfficiencyTester;
import triage_efficiency.Main;

public class SearchAlgorithmTests {


    @Test
    public void testLinearSearchNotFound() {
        Patient[] patients = Main.generatePatients(5);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.linearSearch(patients, "P99999");

        assertNull(result);
    }

    @Test

    public void testLinearSearchFound() {
        Patient[] patients = Main.generatePatients(5);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.linearSearch(patients, "P00003");

        assertNotNull(result);
        assertEquals("P00003", result.getPatientID());
    }

    @Test

    public void testBinarySearchFound() {
        Patient[] patients = Main.generatePatients(5);
        Main.sortByPatientId(patients);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.binarySearch(patients, "P00003");

        assertNotNull(result);
        assertEquals("P00003", result.getPatientID());
    }   

    @Test

    public void testBinarySearchNotFound() {
        Patient[] patients = Main.generatePatients(5);
        Main.sortByPatientId(patients);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.binarySearch(patients, "P67676");

        assertNull(result);
    }

    @Test

    public void testLinearSearchFirst() {
        Patient[] patients = Main.generatePatients(5);
        EfficiencyTester tester = new EfficiencyTester(); 

        Patient result = tester.linearSearch(patients, "P00001");

        assertNotNull(result);
        assertEquals("P00001", result.getPatientID());

    }
    
    @Test
    public void testExponentialSearchFound() {
        Patient[] patients = Main.generatePatients(10);
        Main.sortByPatientId(patients);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.exponentialSearch(patients, "P00007");

        assertNotNull(result);
        assertEquals("P00007", result.getPatientID());
    }

    @Test
    public void testExponentialSearchNotFound() {
        Patient[] patients = Main.generatePatients(10);
        Main.sortByPatientId(patients);
        EfficiencyTester tester = new EfficiencyTester();

        Patient result = tester.exponentialSearch(patients, "P99999");

        assertNull(result);
    }


}