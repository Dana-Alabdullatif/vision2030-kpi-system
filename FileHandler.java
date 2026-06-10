/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vision2030kpisystem;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author dana
 */
public class FileHandler {

    //Read KPI data from input file
    public static int readFile(String filename, LinkedList list) throws IOException {

        Scanner input = new Scanner(new File(filename));  // T(n) = 1, opens file and reads it
        int valid = 0; // T(n) = 1, how many KPIs were read
        int invalid = 0; // T(n) = 1, tracks the line number for error

        while (input.hasNextLine()) {  // T(n) = n, read line by line
            invalid++;                  // T(n) = n
            String line = input.nextLine();      //T(n) = n, takes the next line
            if (line.trim().isEmpty()) {//T(n) = n, if its empty then it will skip that line
                continue;//T(n) = n
            }
            boolean parse = parseKPI(line, list);//T(n) = 1
            if (parse) {  // T(n) = n, converts the line into an object through KPI class and then into the list
                valid++;          // T(n) = n
            } else {
                System.out.println("Please note that line " + invalid + " has been skipped "); //T(n) = n 
            }
        }

        return valid;               // T(n) = 1

        //O(n),T(n)=8+8n
    }

    // Parsing each KPI project and add KPI to the linked list if valid
    private static boolean parseKPI(String line, LinkedList list) throws NumberFormatException {

        String[] parts = line.split(";"); // T(n) = 1,seperation of semicolon to obtain data

        if (parts.length != 7) {           // T(n) = 2, Data that must be recieved is 7 exactly
            return false;                  // T(n) = 1
        }
        /* Since this is the layout of the data KPI Code; Pillar Code; Current Progress; Target Year; Priority Level; Reporting Ministry; Quarterly Change;
            each will be proccessed,fitted to its category, and validated*/

        String kpiCode = parts[0].trim(); // T(n) = 2
        if (kpiCode.isEmpty() || kpiCode.length() != 8) {          // T(n) = 3
            return false;                  // T(n) = 1
        }

        char pillarCode = parts[1].trim().toUpperCase().charAt(0);  // T(n) = 3
        if (pillarCode != 'A' && pillarCode != 'T' && pillarCode != 'V') {  // T(n) = 3
            return false;                  // T(n) = 1
        }

        double currentProgress = Double.parseDouble(parts[2].trim());  // T(n) = 2, converted to double
        if (currentProgress < 0 || currentProgress > 100) {  // T(n) = 2
            return false;                  // T(n) = 1
        }

        int targetYear = Integer.parseInt(parts[3].trim());  // T(n) = 2, converted to int
        if (targetYear < 2010 || targetYear > 2060) {  // T(n) = 2
            return false;                  // T(n) = 1
        }

        int priorityLevel = Integer.parseInt(parts[4].trim());  // T(n) = 2, converted to int
        if (priorityLevel < 1 || priorityLevel > 5) {  // T(n) = 2
            return false;                  // T(n) = 1
        }

        String ministry = parts[5].trim();  // T(n) = 2
        if (ministry.isEmpty() || ministry.length() != 3) {         // T(n) = 3
            return false;                  // T(n) = 1
        }

        double quarterlyChange = Double.parseDouble(parts[6].trim());  // T(n) = 2, converted to double
        if (quarterlyChange < -100 || quarterlyChange > 100) {// T(n) = 3 
            return false;// T(n) = 1
        }

        KPI kpi = new KPI(kpiCode, pillarCode, currentProgress, // T(n) = 1,KPI object then added to list
                targetYear, priorityLevel, ministry,
                quarterlyChange);
        list.add(kpi);                  // T(n) = 1

      
        return true;                       // T(n) = 1
        

        //O(1),T(n):47
    }

    //Write KPI data to output file
    public static void writeFileByDescending(String filename, LinkedList list) throws IOException {

        PrintWriter pw = new PrintWriter(new File(filename));  // T(n) = 1, creating output file
        //format    
        pw.write("KPI Code; Pillar Code; Current Progress; Target Year; Priority Level; Reporting Ministry; Quarterly Change; Priority Score;\n");  // T(n) = 1

        // converting linked list to array 
        KPI[] array = list.toArray();// T(n) = 1

        // sorting array by descending priority score using compareTo
        Arrays.sort(array);// T(n) = 1

        // write each KPI to the file
        for (KPI kpi : array) {// T(n) = n
            pw.write(kpi.toString() + ";\n");// T(n) = n
        }

        pw.close();                    // T(n) = 1

        //O(n),T(n)=5+2n
    }

    // Write sorted KPI data to output file
    public static void writeSortedOutput(String filename, KPI[] array, String sortType) throws IOException {

        PrintWriter pw = new PrintWriter(new File(filename));  // T(n) = 1, creating output file

        pw.write("Sort Type: " + sortType + "\n");  // T(n) = 1, writes which sorting algorthim at the to of the file
        pw.write("KPI Code; Pillar Code; Current Progress; Target Year; Priority Level; Reporting Ministry; Quarterly Change; Priority Score;\n");  // T(n) = 1

        for (KPI kpi : array) {           // T(n) = n,iterates through each KPI in the array
            pw.write(kpi.toString() + ";\n");  // T(n) = n
        }

        pw.close();                    // T(n) = 1

        //O(n),T(n)=4+2n
    }
}
