package triage_efficiency;

import java.util.*;
import patient_intake.Patient;

public class EfficiencyTester {

    /**
     * REQUIRED (80%): Implement linear search.
     *
     * Search through the patient array one element at a time until the matching
     * patientID is found. Return the Patient if it exists; otherwise return null.
     *
     * This method must run in O(n) time.
     */
    public Patient linearSearch(Patient[] patients, String pid) {
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getPatientID().equals(pid)) {
                return patients[i];
            }
        }
        return null;
        // Search the entire array in order and return the matching Patient.
    }

    /**
     * REQUIRED (80%): Implement binary search.
     *
     * This method works only on an array that is sorted by patientID.
     * Repeatedly divide the search range in half until the target is found.
     *
     * This method must run in O(log n) time.
     */
    public Patient binarySearch(Patient[] patients, String pid) {
        int arrayRangeUpper = patients.length - 1;
        int arrayRangeLower = 0;
        while (arrayRangeLower <= arrayRangeUpper) {
            int middle = (arrayRangeUpper + arrayRangeLower) / 2;
            Patient patientIDCompare = patients[middle];
            int comparison = patientIDCompare.getPatientID().compareTo(pid);
            if (comparison > 0) {
                arrayRangeUpper = middle - 1;
            } else if (comparison < 0 ) { 
                arrayRangeLower = middle + 1;
            } else {
                return patientIDCompare;
            }
        }
        // TODO REQUIRED: Implement iterative binary search.
        // The array must be sorted by patientID before calling this method.
        return null; // Remove this line and implement the method.
    }


    //this is my expodential search.
    //I learned about it on stack overflow
    //It works because it expodentially jumps through the index until it finds a value higher than whats its looking for,
    // and then it goes through an upper and lower range. 
    public Patient exponentialSearch(Patient[] patients, String pid) {
        int prev = 0;
        int upperBound = -1;
        int current = 1;
        while (prev < patients.length && upperBound == -1) { 
            if (current >= patients.length) {
                upperBound = patients.length - 1;
            }
            else if (patients[current].getPatientID().compareTo(pid) == 0) { 
                return patients[current];
            } else {
                if (patients[current].getPatientID().compareTo(pid) < 0) {
                    prev = current;
                    current = current * 2;
                } else {
                    upperBound = current;
                }
            }
        }

        int lowerBound = prev; 

        for (int i = lowerBound; i < upperBound + 1; i++) {
            if (patients[i].getPatientID().equals(pid)){ 
                return patients[i];
            }
        }
        return null;

    }

    /**
     * OPTIONAL (+5%): Implement a different O(log n) search algorithm.
     *
     * Pick one of the following approaches and implement it:
     * - Exponential search
     * - Jump search
     * - Ternary search
     *
     * Add a short comment above the method explaining:
     * - which algorithm you chose
     * - where you learned about it
     * - why it works
     * 
     */

    public Patient logNSearch(Patient[] patients, String pid) {
        return binarySearch(patients, pid);
    }

    public Patient parallelMax(Patient[] patients, String pid) {
        int n = patients.length;
        Patient[] placeHold = new Patient[n];
        for (int i = 0; i < n; i++) {
            placeHold[i] = patients[i];
        }
        int active = n;
        while (active > 1) {
            for (int i = 0; i < active / 2; i++) {
                Patient left = placeHold[2 * i];
                Patient right = placeHold[2 * i + 1];
                if (left.getPatientID().compareTo(right.getPatientID()) > 0) {
                    placeHold[i] = left;
                } else {
                    placeHold[i] = right;
                }
            }
            active = active / 2;
        }
        return placeHold[0];
    }
    //NOTE: I MISREAD THE INSTRUCTIONS AND ACCIDENTALLY MADE THIS METHOD ABOVE, BUT ITS IMPRESSIVE TO ME SO IM KEEPING IT
    //heres my code inspired by some random smart guy on stack overflow:
    // If we assume there are N elements in the list, a possible solution would be to use N parallel computing elements [ CE0 .. CEN ]. 
    // In the base case of the algorithm, we let each computing element CEi in [ CEN/2 .. CEN ] compare list values x2i-N and x2i-N+1. 
    // Each computing element reports the larger of their two assigned values to CEi/2. The iterative steps of the algorithm is that each 
    // computing element CEk that receives two reported values reports the largest to CEk/2. This iterative logic continues until CE0 processes a report from itself. 
    // Instead of reporting to itself again, it outputs the result.
    public void timeDemo() {
        int[] sizes = {100, 1000, 10000, 100000};

        System.out.println("Dataset Size | Linear (ns) | Binary (ns) | Exponential (ns)");

        for (int size : sizes) {
            Patient[] patients = Main.generatePatients(size);
            Main.sortByPatientId(patients);

            String target = String.format("P%05d", size);

            long start = System.nanoTime();
            linearSearch(patients, target);
            long linearTime = System.nanoTime() - start;

            start = System.nanoTime();
            binarySearch(patients, target);
            long binaryTime = System.nanoTime() - start;

            start = System.nanoTime();
            exponentialSearch(patients, target);
            long exponentialTime = System.nanoTime() - start;

            System.out.println(size + " | " + linearTime + " | " + binaryTime + " | " + exponentialTime);
        }
    }
}
