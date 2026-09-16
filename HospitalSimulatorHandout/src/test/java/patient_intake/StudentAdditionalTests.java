package patient_intake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;




public class StudentAdditionalTests {

    @Test
    void testPatientStoresID() {
        // Makes sure that the array successfully stores a patient AND the patient class stores the ID
        Patient patient = new Patient("123", "John", "Smith", 25, "Headache", 2, "Waiting", "101", 10, "999");
        
        assertEquals("123", patient.getPatientID());

        // Test #1
    }

    @Test
    void testPatientStoresName() {
        // Makes sure that the array successfully stores a patient AND the patient class stores the name
        Patient patient = new Patient("142", "Farty", "McFartPants", 67, "Butt Itches", 2, "Waiting", "103", 6, "342");
        // Test #2

        assertEquals("Farty", patient.getFirstName());
    }

    @Test
    void testRegistryHasSize0() {
        // makes sure that the array is initialized with size 0

        PatientRegistry patientRegistry = new PatientRegistry();

        assertTrue(patientRegistry.toString().contains("size: 0"));
        // Test #3
    }

    @Test
    void testAddingAPatient() {

        
        // tests the ability to add a patient
        PatientRegistry registry = new PatientRegistry();

        Patient patient1 = new Patient("142", "Farty", "McFartPants", 67, "Butt Itches", 2, "Waiting", "103", 6, "342");
        Patient patient2 = new Patient("123", "John", "Smith", 25, "Headache", 2, "Waiting", "101", 10, "999");

        registry.addPatient(patient1);
        registry.addPatient(patient2);

        String result = registry.toString();

        assertTrue(result.contains("Farty"));
        assertTrue(result.contains("Smith"));

        // Test #4
    }

    @Test
    void testEdgeCase() {
        // Searching by an existing ID should return the correct patient
    
        PatientRegistry registry = new PatientRegistry();

        Patient patient = new Patient("123", "John", "Smith", 25, "Headache", 2, "Waiting", "101", 10, "999");

        registry.addPatient(patient);

        Patient found = registry.getPatientByID("123");

        assertEquals(patient, found);
        // Test #5
    }

    @Test
    void testFindNonexistentPatient() {
        // Searching for an ID that does not exist should return null rather than causing an error

        PatientRegistry registry = new PatientRegistry();

        Patient found = registry.getPatientByID("999");

        assertEquals(null, found);
    }
}