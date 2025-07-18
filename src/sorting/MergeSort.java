package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Merge Sort Algorithm Implementation
 * 
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n)
 * 
 * Merge sort is a divide-and-conquer algorithm that divides the array
 * into halves, sorts them separately, and then merges them.
 */
public class MergeSort {
    
    private int comparisons = 0;
    private int merges = 0;
    private boolean visualize = false;
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Merge Sort Algorithm ===");
        System.out.println("Divide-and-conquer algorithm that recursively splits and merges arrays.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        this.visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Reset counters
        comparisons = 0;
        merges = 0;
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        mergeSort(sortedArray, 0, sortedArray.length - 1, 0);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, merges, endTime - startTime, array.length);
    }
    
    private int[] getArrayInput(Scanner scanner) {
        int size = InputUtils.getInt(scanner, "Enter array size:", 1, 100);
        int[] array = new int[size];
        
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = InputUtils.getInt(scanner, "Element [" + i + "]:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        return array;
    }
    
    private void mergeSort(int[] array, int left, int right, int depth) {
        if (left < right) {
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "Divide: [" + left + ", " + right + "] → splitting array");
                printSubArray(array, left, right, indent + "  ");
                VisualizationUtils.pauseForVisualization();
            }
            
            // Find the middle point
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSort(array, left, mid, depth + 1);
            mergeSort(array, mid + 1, right, depth + 1);
            
            // Merge the sorted halves
            merge(array, left, mid, right, depth);
        }
    }
    
    private void merge(int[] array, int left, int mid, int right, int depth) {
        // Create temporary arrays for left and right subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }
        
        if (visualize) {
            String indent = "  ".repeat(depth);
            System.out.println(indent + "Merge: Combining sorted subarrays");
            System.out.print(indent + "  Left: [");
            for (int i = 0; i < leftArray.length; i++) {
                System.out.print(leftArray[i]);
                if (i < leftArray.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.print(indent + "  Right: [");
            for (int i = 0; i < rightArray.length; i++) {
                System.out.print(rightArray[i]);
                if (i < rightArray.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
        
        // Merge the temporary arrays back into array[left..right]
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            comparisons++;
            
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "  Comparing " + leftArray[i] + " and " + rightArray[j]);
            }
            
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                if (visualize) {
                    String indent = "  ".repeat(depth);
                    System.out.println(indent + "    → Taking " + leftArray[i] + " from left");
                }
                i++;
            } else {
                array[k] = rightArray[j];
                if (visualize) {
                    String indent = "  ".repeat(depth);
                    System.out.println(indent + "    → Taking " + rightArray[j] + " from right");
                }
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray[], if any
        while (i < n1) {
            array[k] = leftArray[i];
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "  Copying remaining " + leftArray[i] + " from left");
            }
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray[], if any
        while (j < n2) {
            array[k] = rightArray[j];
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "  Copying remaining " + rightArray[j] + " from right");
            }
            j++;
            k++;
        }
        
        merges++;
        
        if (visualize) {
            String indent = "  ".repeat(depth);
            System.out.println(indent + "  Merged result:");
            printSubArray(array, left, right, indent + "    ");
            VisualizationUtils.pauseForVisualization();
        }
    }
    
    private void printSubArray(int[] array, int left, int right, String indent) {
        System.out.print(indent + "[");
        for (int i = left; i <= right; i++) {
            System.out.print(array[i]);
            if (i < right) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    private void displayResults(int comparisons, int merges, long elapsedTime, int arraySize) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Time Complexity: O(n log n) in all cases");
        System.out.println("Space Complexity: O(n)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Merge operations: " + merges);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Stable (maintains relative order of equal elements)");
        System.out.println("Advantages: Consistent O(n log n) performance, stable, parallelizable");
        System.out.println("Theoretical comparisons: ≤ n log n");
        System.out.println("Recursion depth: " + (int) Math.ceil(Math.log(arraySize) / Math.log(2)));
    }
}
