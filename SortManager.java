/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vision2030kpisystem;

import java.io.IOException;

/**
 *
 * @author dana
 */
public class SortManager {

    // Store timing information (in milliseconds)
    static long selectionSortTime = 0;
    static long insertionSortTime = 0;
    static long mergeSortTime = 0;
    static long quickSortTime = 0;
    
     //Execute all sorting algorithms and save results to files
    public static void sortAndOutput(LinkedList kpiList) throws IOException {
        System.out.println();                 // T(n) = 1
        
        // Convert linked list to array ONCE
        KPI[] baseArray = kpiList.toArray(); // T(n) = n 
        
        // Create copies for each sorting algorithm
        KPI[] array1 = baseArray.clone();    // T(n) = n 
        KPI[] array2 = baseArray.clone();    // T(n) = n 
        KPI[] array3 = baseArray.clone();    // T(n) = n 
        KPI[] array4 = baseArray.clone();    // T(n) = n 
        
        // Selection Sort
        selectionSort(array1);               // T(n) = n²
        FileHandler.writeSortedOutput("Sorted_Output_SS.txt", array1, 
                   "Selection Sort - By Priority Score (Descending)");  // T(n) = n
       
        // Insertion Sort
        insertionSort(array2);               // T(n) = n²
        FileHandler.writeSortedOutput("Sorted_Output_IS.txt", array2, 
                   "Insertion Sort - By Current Progress (Descending)");  // T(n) = n
      
        // Merge Sort
        mergeSort(array3);                   // T(n) = n log n
        FileHandler.writeSortedOutput("Sorted_Output_MS.txt", array3, 
                   "Merge Sort - By Priority Level (Descending)");  // T(n) = n
       
        // Quick Sort
        quickSort(array4);                   // T(n) = n log n
        FileHandler.writeSortedOutput("Sorted_Output_QS.txt", array4, 
                   "Quick Sort - By Quarterly Change (Descending)");  // T(n) = n
        
       //O(n^2), T(n)=  2n^2+2nxlog2(n)+9n+1
        
    }
     //Selection Sort implementation
    
    private static void selectionSort(KPI[] array) {
        long startTime = System.currentTimeMillis();  // T(n) = 1
        
        int n = array.length;                // T(n) = 1
        
        for (int i = 0; i < n - 1; i++) {   // T(n) = n
            int maxIndex = i;                // T(n) = 1
            
            for (int j = i + 1; j < n; j++) {  // T(n) = n
                if (array[j].getPriorityScore() > array[maxIndex].getPriorityScore()) {  // T(n) = 1
                    maxIndex = j;            // T(n) = 1
                }
            }   
            if (maxIndex != i) {             // T(n) = 1
                KPI temp = array[i];         // T(n) = 1
                array[i] = array[maxIndex];  // T(n) = 1
                array[maxIndex] = temp;      // T(n) = 1
            }
        }
        
        long endTime = System.currentTimeMillis();  // T(n) = 1 
        selectionSortTime = endTime - startTime;   // T(n) = 1 (TIME DIFFERENCE = b - a)
        //O(n^2), T(n)=2n^2+4n-1
    }
     //Insertion Sort implementation
    private static void insertionSort(KPI[] array) {
        long startTime = System.currentTimeMillis();  // T(n) = 1 
        
        int n = array.length;                // T(n) = 1
        for (int i = 1; i < n; i++) {       // T(n) = n
            KPI key = array[i];              // T(n) = 1
            int j = i - 1;                   // T(n) = 1
            
            while (j >= 0 && array[j].getCurrentProgress() < key.getCurrentProgress()) {// T(n) = n
                array[j + 1] = array[j];     // T(n) = 1
                j--;                         // T(n) = 1
            }
            array[j + 1] = key;              // T(n) = 1
        }        
        long endTime = System.currentTimeMillis();  // T(n) = 1 
        insertionSortTime = endTime - startTime;   // T(n) = 1 (TIME DIFFERENCE = b - a)
        //O(n^2), T(n)=4n
    }
     //Merge Sort implementation
    private static void mergeSort(KPI[] array) {
        long startTime = System.currentTimeMillis();  // T(n) = 1 (START TIME)
        
        if (array.length > 1) {              // T(n) = 1
            mergeSortHelper(array, 0, array.length - 1);  // T(n) = n log n
        } 
        long endTime = System.currentTimeMillis();  // T(n) = 1 
        mergeSortTime = endTime - startTime;       // T(n) = 1 (TIME DIFFERENCE = b - a)
        //O(nlogn), T(n)= nlogn+4
    }
     //Method for recursive merge sort
    private static void mergeSortHelper(KPI[] array, int left, int right) {
        if (left < right) {                  // T(n) = 1
            int mid = (left + right) / 2;    // T(n) = 1
            
            mergeSortHelper(array, left, mid);  // T(n) = n log n
            
            mergeSortHelper(array, mid + 1, right);  // T(n) = n log n
            
            merge(array, left, mid, right);  // T(n) = n
        }
           //O(nlogn), T(n)= nlogn+2n-1
    }
     //Merge two sorted subarrays
    private static void merge(KPI[] array, int left, int mid, int right) {
        KPI[] leftArr = new KPI[mid - left + 1];  // T(n) = 1
        KPI[] rightArr = new KPI[right - mid];    // T(n) = 1
        
        for (int i = 0; i < leftArr.length; i++) {  // T(n) = n
            leftArr[i] = array[left + i];    // T(n) = 1
        }
        
        for (int i = 0; i < rightArr.length; i++) {  // T(n) = n
            rightArr[i] = array[mid + 1 + i];  // T(n) = 1
        }
        
        int i = 0, j = 0, k = left;          // T(n) = 1
        
        while (i < leftArr.length && j < rightArr.length) {  // T(n) = n
            if (leftArr[i].getPriorityLevel() >= rightArr[j].getPriorityLevel()) {  // T(n) = 1
                array[k] = leftArr[i];       // T(n) = 1
                i++;                         // T(n) = 1
            } else {
                array[k] = rightArr[j];      // T(n) = 1
                j++;                         // T(n) = 1
            }
            k++;                             // T(n) = 1
        }
        
        while (i < leftArr.length) {         // T(n) = n
            array[k] = leftArr[i];           // T(n) = 1
            i++;                             // T(n) = 1
            k++;                             // T(n) = 1
        }
        
        while (j < rightArr.length) {        // T(n) = n
            array[k] = rightArr[j];          // T(n) = 1
            j++;                             // T(n) = 1
            k++;                             // T(n) = 1
        }
        //O(n), T(n)=8n-1
    }   
    
     //Quick Sort implementation
    private static void quickSort(KPI[] array) {
        long startTime = System.currentTimeMillis();  // T(n) = 1 
        
        if (array.length > 1) {              // T(n) = 1
            quickSortHelper(array, 0, array.length - 1);  // T(n) = n log n
        }
        long endTime = System.currentTimeMillis();  // T(n) = 1
        quickSortTime = endTime - startTime;       // T(n) = 1 (TIME DIFFERENCE = b - a)
        //O(nlogn), T(n)=nlogn+4
    }
      //Method for recursive quick sort
    private static void quickSortHelper(KPI[] array, int low, int high) {
        if (low < high) {                    // T(n) = 1
            int pi = partition(array, low, high);  // T(n) = n
            
            quickSortHelper(array, low, pi - 1);  // T(n) = n log n
            
            quickSortHelper(array, pi + 1, high);  // T(n) = n log n
        } 
    } //O(nlogn), T(n)= nlogn+n

     //Partition array around a pivot element
    private static int partition(KPI[] array, int low, int high) {
        KPI pivot = array[high];             // T(n) = 1
        int i = low - 1;                     // T(n) = 1
        
        for (int j = low; j < high; j++) {  // T(n) = n
            if (array[j].getQuarterlyChange() > pivot.getQuarterlyChange()) {  // T(n) = 1
                i++;                         // T(n) = 1
                KPI temp = array[i];         // T(n) = 1
                array[i] = array[j];         // T(n) = 1
                array[j] = temp;             // T(n) = 1
            }
        } 
        KPI temp = array[i + 1];             // T(n) = 1
        array[i + 1] = array[high];          // T(n) = 1
        array[high] = temp;                  // T(n) = 1
        
        return i + 1;                        // T(n) = 1
    }  //O(n), T(n)=5n+1
    
    //Print performance report f or all sorting algorithms
    public static void printPerformanceReport() {
        System.out.println();                // T(n) = 1
        System.out.println("=== Sorting Algorithm Performance Report ===");  // T(n) = 1
        System.out.println("  Selection Sort:     " + selectionSortTime + " ms");  // T(n) = 1
        System.out.println("  Insertion Sort:     " + insertionSortTime + " ms");  // T(n) = 1
        System.out.println("  Merge Sort:         " + mergeSortTime + " ms");      // T(n) = 1
        System.out.println("  Quick Sort:         " + quickSortTime + " ms");      // T(n) = 1
        System.out.println();                // T(n) = 1
    }//O(1), T(n)=7
}

