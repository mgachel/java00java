package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Insertion Sort Algorithm Implementation
 * 
 * Time Complexity: O(n²) average and worst case, O(n) best case
 * Space Complexity: O(1)
 * 
 * Insertion sort builds the final sorted array one item at a time.
 * It is efficient for small datasets and nearly sorted arrays.
 */
public class InsertionSort {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Insertion Sort Algorithm ===");
        System.out.println("Builds sorted array one element at a time by inserting each element in correct position.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        boolean visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        int[] stats = insertionSort(sortedArray, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(stats[0], stats[1], endTime - startTime, array.length);
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
    
    private int[] insertionSort(int[] array, boolean visualize) {
        int n = array.length;
        int comparisons = 0;
        int shifts = 0;
        
        if (visualize) {
            System.out.println("\n--- Insertion Sort Visualization ---");
            VisualizationUtils.printSeparator();
            System.out.println("Starting with first element as sorted portion: [" + array[0] + "]");
        }
        
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            
            if (visualize) {
                System.out.println("\nStep " + i + " - Inserting element " + key + " from index " + i + ":");
                System.out.println("  Sorted portion: indices [0, " + (i - 1) + "]");
                System.out.println("  Element to insert: " + key);
                System.out.println("  Starting comparison from index " + j);
            }
            
            // Move elements greater than key one position ahead
            while (j >= 0 && array[j] > key) {
                comparisons++;
                
                if (visualize) {
                    System.out.println("    Comparing " + key + " with " + array[j] + " at index " + j);
                    System.out.println("    → " + array[j] + " > " + key + ", shifting " + array[j] + 
                                     " from index " + j + " to " + (j + 1));
                }
                
                array[j + 1] = array[j];
                shifts++;
                j = j - 1;
                
                if (visualize) {
                    VisualizationUtils.printArray(array, "    Array after shift:");
                    VisualizationUtils.pauseForVisualization();
                }
            }
            
            // If we made at least one comparison in the while loop but didn't enter it
            if (j >= 0) {
                comparisons++;
                if (visualize) {
                    System.out.println("    Comparing " + key + " with " + array[j] + " at index " + j);
                    System.out.println("    → " + array[j] + " <= " + key + ", found correct position");
                }
            }
            
            array[j + 1] = key;
            
            if (visualize) {
                System.out.println("  → Inserting " + key + " at index " + (j + 1));
                VisualizationUtils.printArray(array, "  Array after insertion:");
                System.out.println("  ✓ Sorted portion now: indices [0, " + i + "]");
                VisualizationUtils.printSeparator();
            }
        }
        
        return new int[]{comparisons, shifts};
    }
    
    private void displayResults(int comparisons, int shifts, long elapsedTime, int arraySize) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Time Complexity: O(n²) average/worst, O(n) best case");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Element shifts: " + shifts);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Stable (maintains relative order of equal elements)");
        System.out.println("Advantages: Efficient for small arrays, adaptive (fast for nearly sorted data)");
        System.out.println("Best case: Already sorted array requires only " + (arraySize - 1) + " comparisons");
    }
}
