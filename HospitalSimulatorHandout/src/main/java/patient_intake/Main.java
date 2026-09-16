package patient_intake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      String filePath = "HospitalSimulatorHandout/src/main/java/patient_intake/patients.csv";
      PatientRegistry patients = new PatientRegistry();

      try (Scanner fileReader = new Scanner(new File(filePath))) {
         if (fileReader.hasNextLine()) {
            fileReader.nextLine(); // Skip the CSV header.
         }

         while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(",");

           
            String patientID = parts[0];
            String name = parts[1];
            String[] nameParts = name.split(" ");

            String firstName = nameParts[0];
            String lastName = nameParts[1];
            int age = Integer.parseInt(parts[2]);
            String chiefComplaint = parts[3];
            int triageLevel = Integer.parseInt(parts[4]);
            String currentStage = parts[5];
            String assignedRoom = parts[6];
            int arrivalHour = Integer.parseInt(parts[7]);
            String insuranceID = parts[8];

            Patient patient = new Patient(patientID, firstName, lastName, age, chiefComplaint, triageLevel, currentStage, assignedRoom, 
               arrivalHour, insuranceID);

            patients.addPatient(patient);

              
              



         }

         System.out.println(patients);
      } catch (FileNotFoundException exception) {
         System.out.println("Input file not found.");
      }
   }
}