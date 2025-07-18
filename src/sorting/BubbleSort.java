package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Bubble Sort Algorithm Implementation
 * 
 * Time Complexity: O(n²) average and worst case, O(n) best case (optimized version)
 * Space Complexity: O(1)
 * 
 * Bubble sort repeatedly steps through the list, compares adjacent elements
 * and swaps them if they are in wrong order.
 */
public class BubbleSort {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Bubble Sort Algorithm ===");
        System.out.println("Repeatedly compares adjacent elements and swaps if needed.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        boolean visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Ask for optimization
        System.out.print("Use optimized version (early termination)? (y/n): ");
        boolean optimized = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        int comparisons = optimized ? 
            bubbleSortOptimized(sortedArray, visualize) :
            bubbleSortBasic(sortedArray, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, endTime - startTime, array.length, optimized);
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
    
    private int bubbleSortBasic(int[] array, boolean visualize) {
        int n = array.length;
        int comparisons = 0;
        
        if (visualize) {
            System.out.println("\n--- Bubble Sort Visualization (Basic) ---");
            VisualizationUtils.printSeparator();
        }
        
        for (int i = 0; i < n - 1; i++) {
            if (visualize) {
                System.out.println("Pass " + (i + 1) + ":");
            }
            
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                
                if (visualize) {
                    System.out.println("  Comparing " + array[j] + " and " + array[j + 1] + 
                                     " at indices " + j + " and " + (j + 1));
                }
                
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    
                    if (visualize) {
                        System.out.println("    → Swapped! New order: " + array[j] + ", " + array[j + 1]);
                        VisualizationUtils.printArray(array, "    Current array:");
                    }
                } else if (visualize) {
                    System.out.println("    → No swap needed.");
                }
                
                if (visualize) {
                    VisualizationUtils.pauseForVisualization();
                }
            }
            
            if (visualize) {
                System.out.println("  Pass " + (i + 1) + " complete. Largest element (" + 
                                 array[n - i - 1] + ") is now in position " + (n - i - 1));
                VisualizationUtils.printSeparator();
            }
        }
        
        return comparisons;
    }
    
    private int bubbleSortOptimized(int[] array, boolean visualize) {
        int n = array.length;
        int comparisons = 0;
        
        if (visualize) {
            System.out.println("\n--- Bubble Sort Visualization (Optimized) ---");
            VisualizationUtils.printSeparator();
        }
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            if (visualize) {
                System.out.println("Pass " + (i + 1) + ":");
            }
            
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                
                if (visualize) {
                    System.out.println("  Comparing " + array[j] + " and " + array[j + 1] + 
                                     " at indices " + j + " and " + (j + 1));
                }
                
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    
                    if (visualize) {
                        System.out.println("    → Swapped! New order: " + array[j] + ", " + array[j + 1]);
                        VisualizationUtils.printArray(array, "    Current array:");
                    }
                } else if (visualize) {
                    System.out.println("    → No swap needed.");
                }
                
                if (visualize) {
                    VisualizationUtils.pauseForVisualization();
                }
            }
            
            if (visualize) {
                System.out.println("  Pass " + (i + 1) + " complete.");
                if (!swapped) {
                    System.out.println("  ✓ No swaps made - array is sorted!");
                }
                VisualizationUtils.printSeparator();
            }
            
            // If no swapping happened, array is sorted
            if (!swapped) {
                break;
            }
        }
        
        return comparisons;
    }
    
    private void displayResults(int comparisons, long elapsedTime, int arraySize, boolean optimized) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Version: " + (optimized ? "Optimized (with early termination)" : "Basic"));
        System.out.println("Time Complexity: O(n²) average/worst, " + (optimized ? "O(n) best" : "O(n²) best"));
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Stable (maintains relative order of equal elements)");
    }
}
