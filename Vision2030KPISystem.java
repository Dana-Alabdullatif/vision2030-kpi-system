/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vision2030kpisystem;

/**
 *
 * @author dana
 */
import java.io.IOException;
import java.time.LocalDateTime;

public class Vision2030KPISystem {

    public static void main(String[] args) throws IOException {
        // File names data is obtained from and written to
        String inFile = "Input.txt";   // T(n) = 1
        String outFile = "Output.txt"; // T(n) = 1

        // Header
        System.out.println("==== Vision 2030 KPI Progress Tracking and Analysis System ===");  // T(n) = 1
        // Storing the time it takes to operate (in milliseconds(for accuracy))
        long readTime = 0; // T(n)=1
        long processTime = 0; // T(n) =1
        long writeTime = 0; // T(n) =1

        // Storing the current year
        int currentYear = LocalDateTime.now().getYear(); // T(n) =1

        // Firstly it reads input file, stores in LinkedList, then calculates the time it takes
        LinkedList kpiList = new LinkedList();  // T(n) = 1
        long startReadTime = System.currentTimeMillis();  // a = start time, T(n) = 1
        int kpiCount = FileHandler.readFile(inFile, kpiList);  // stores KPIs from file and returns how many were read,T(n) = n
        long endReadTime = System.currentTimeMillis();    // b = end time,T(n) = 1
        readTime = endReadTime - startReadTime;           // alculates the time it takes to read,Time = b - a (ms),T(n) = 2

        // Checks if any KPIs were read 
        if (kpiList.isEmpty()) {             // T(n) = 1
            System.out.println();            // T(n) = 1
            throw new IllegalArgumentException("Error: No valid KPI records found! Exiting application.");  // T(n) = 1

        }

        // Secondly it processes KPIs
        long startProcessTime = System.currentTimeMillis();  // a = start time,T(n) = 1
        KPI.processKPIs(kpiList, currentYear);  // processes and calulates priority score ,T(n) = n
        long endProcessTime = System.currentTimeMillis();    // b = end time,T(n) = 1
        processTime = endProcessTime - startProcessTime;     // calculates the time it takes to processes KPIs, Time = b - a (ms),T(n) = 2

        // Thirdly writes results to a file
        long startWriteTime = System.currentTimeMillis();  // a = start time,T(n) = 1
        FileHandler.writeFileByDescending(outFile, kpiList);  // writes everything in the list to the Output file,T(n) = n
        long endWriteTime = System.currentTimeMillis();    // b = end time,T(n) = 1
        writeTime = endWriteTime - startWriteTime;         // calculates the time it takes to write, Time = b - a (ms),T(n) = 2

        // Fourthly applys sorting algorithms
        SortManager.sortAndOutput(kpiList);  // applys  all 4 sorting algorithms and writes in an output file,T(n) = n² (all 4 sorts)

        // Step 5: Print performance reports
        System.out.println();                // T(n) = 1
        System.out.println("=== I/O Performance Report ===");  // T(n) = 1
        System.out.println("Reading Time:         " + readTime + " ms");  // T(n) = 1
        System.out.println("Processing Time:      " + processTime + " ms");  // T(n) = 1
        System.out.println("Writing Time:         " + writeTime + " ms");   // T(n) = 1
// prints all 4 sorting algorithms
        SortManager.printPerformanceReport();  // T(n) = 1

        System.out.println("=== System Completed Successfully ===");  // T(n) = 1
        
        //O(n^2),T(n)=30+3n+n^2
    }

}
