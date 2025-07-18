package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Selection Sort Algorithm Implementation
 * 
 * Time Complexity: O(n²) in all cases
 * Space Complexity: O(1)
 * 
 * Selection sort finds the minimum element from unsorted part
 * and puts it at the beginning.
 */
public class SelectionSort {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Selection Sort Algorithm ===");
        System.out.println("Finds minimum element from unsorted part and places it at the beginning.");
        
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
        int comparisons = selectionSort(sortedArray, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, endTime - startTime, array.length);
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
    
    private int selectionSort(int[] array, boolean visualize) {
        int n = array.length;
        int comparisons = 0;
        
        if (visualize) {
            System.out.println("\n--- Selection Sort Visualization ---");
            VisualizationUtils.printSeparator();
        }
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            if (visualize) {
                System.out.println("Pass " + (i + 1) + " - Finding minimum in unsorted part:");
                System.out.println("  Sorted part: indices [0, " + (i - 1) + "]");
                System.out.println("  Unsorted part: indices [" + i + ", " + (n - 1) + "]");
                System.out.println("  Current minimum candidate: " + array[minIndex] + " at index " + minIndex);
            }
            
            // Find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                
                if (visualize) {
                    System.out.println("    Comparing " + array[minIndex] + " (min candidate) with " + 
                                     array[j] + " at index " + j);
                }
                
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                    
                    if (visualize) {
                        System.out.println("      → New minimum found! " + array[minIndex] + 
                                         " at index " + minIndex);
                    }
                } else if (visualize) {
                    System.out.println("      → " + array[minIndex] + " remains the minimum.");
                }
                
                if (visualize) {
                    VisualizationUtils.pauseForVisualization();
                }
            }
            
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                
                if (visualize) {
                    System.out.println("  → Swapping " + array[minIndex] + " (index " + minIndex + 
                                     ") with " + array[i] + " (index " + i + ")");
                }
            } else if (visualize) {
                System.out.println("  → No swap needed. Element " + array[i] + 
                                 " is already in correct position.");
            }
            
            if (visualize) {
                VisualizationUtils.printArray(array, "  Array after pass " + (i + 1) + ":");
                System.out.println("  ✓ Position " + i + " is now correctly sorted with value " + array[i]);
                VisualizationUtils.printSeparator();
            }
        }
        
        return comparisons;
    }
    
    private void displayResults(int comparisons, long elapsedTime, int arraySize) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Time Complexity: O(n²) in all cases");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Swaps made: " + (arraySize - 1) + " (maximum)");
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Not stable (may change relative order of equal elements)");
        System.out.println("Advantage: Minimizes number of swaps (at most n-1 swaps)");
    }
}
