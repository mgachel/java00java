package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Shell Sort Algorithm Implementation
 * 
 * Time Complexity: Depends on gap sequence - O(n^1.25) to O(n^2)
 * Space Complexity: O(1)
 * 
 * Shell sort is an optimization of insertion sort that allows
 * the exchange of items that are far apart.
 */
public class ShellSort {
    
    private int comparisons = 0;
    private int shifts = 0;
    private boolean visualize = false;
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Shell Sort Algorithm ===");
        System.out.println("Optimized insertion sort that compares elements separated by a gap.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        this.visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Choose gap sequence
        System.out.println("Choose gap sequence:");
        System.out.println("1. Shell's original (n/2, n/4, n/8, ...)");
        System.out.println("2. Knuth's sequence (3k+1: 1, 4, 13, 40, ...)");
        System.out.println("3. Hibbard's sequence (2^k-1: 1, 3, 7, 15, ...)");
        int gapChoice = InputUtils.getInt(scanner, "Enter choice:", 1, 3);
        
        // Reset counters
        comparisons = 0;
        shifts = 0;
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        shellSort(sortedArray, gapChoice);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, shifts, endTime - startTime, array.length, gapChoice);
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
    
    private void shellSort(int[] array, int gapChoice) {
        int n = array.length;
        
        if (visualize) {
            System.out.println("\n--- Shell Sort Visualization ---");
            VisualizationUtils.printSeparator();
        }
        
        // Generate gap sequence based on choice
        int[] gaps = generateGapSequence(n, gapChoice);
        
        if (visualize) {
            System.out.print("Gap sequence: [");
            for (int i = 0; i < gaps.length; i++) {
                System.out.print(gaps[i]);
                if (i < gaps.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            VisualizationUtils.printSeparator();
        }
        
        // Start with the largest gap and work down to gap of 1
        for (int gapIndex = 0; gapIndex < gaps.length; gapIndex++) {
            int gap = gaps[gapIndex];
            
            if (visualize) {
                System.out.println("Phase " + (gapIndex + 1) + " - Gap size: " + gap);
                System.out.println("Performing insertion sort on " + gap + " interleaved sublists");
            }
            
            // Perform gapped insertion sort
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                
                if (visualize) {
                    System.out.println("  Inserting element " + temp + " from position " + i);
                    System.out.println("  Comparing with elements at gap distance: " + 
                                     (i - gap >= 0 ? "position " + (i - gap) : "none"));
                }
                
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    comparisons++;
                    
                    if (visualize) {
                        System.out.println("    Comparing " + temp + " with " + array[j - gap] + 
                                         " (positions " + j + " and " + (j - gap) + ")");
                        System.out.println("    → " + array[j - gap] + " > " + temp + 
                                         ", shifting " + array[j - gap] + " from " + (j - gap) + " to " + j);
                    }
                    
                    array[j] = array[j - gap];
                    shifts++;
                    
                    if (visualize) {
                        VisualizationUtils.printArray(array, "    Array after shift:");
                        VisualizationUtils.pauseForVisualization();
                    }
                }
                
                // Final comparison if we exited the loop due to j < gap
                if (j >= gap) {
                    comparisons++;
                    if (visualize) {
                        System.out.println("    Comparing " + temp + " with " + array[j - gap] + 
                                         " (positions " + j + " and " + (j - gap) + ")");
                        System.out.println("    → " + array[j - gap] + " <= " + temp + 
                                         ", found correct position");
                    }
                }
                
                array[j] = temp;
                
                if (visualize) {
                    System.out.println("  → Placed " + temp + " at position " + j);
                    VisualizationUtils.printArray(array, "  Array after insertion:");
                }
            }
            
            if (visualize) {
                System.out.println("✓ Gap " + gap + " phase complete");
                VisualizationUtils.printArray(array, "Array after gap " + gap + ":");
                VisualizationUtils.printSeparator();
            }
        }
    }
    
    private int[] generateGapSequence(int n, int gapChoice) {
        java.util.List<Integer> gapList = new java.util.ArrayList<>();
        
        switch (gapChoice) {
            case 1: // Shell's original sequence
                for (int gap = n / 2; gap > 0; gap /= 2) {
                    gapList.add(gap);
                }
                break;
            case 2: // Knuth's sequence
                int k = 1;
                while (k < n) {
                    gapList.add(0, k); // Add at beginning to reverse order
                    k = 3 * k + 1;
                }
                break;
            case 3: // Hibbard's sequence
                k = 1;
                int hibbard = 1;
                while (hibbard < n) {
                    gapList.add(0, hibbard); // Add at beginning to reverse order
                    k++;
                    hibbard = (1 << k) - 1; // 2^k - 1
                }
                break;
        }
        
        return gapList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void displayResults(int comparisons, int shifts, long elapsedTime, int arraySize, int gapChoice) {
        String[] gapNames = {"", "Shell's original (n/2, n/4, ...)", 
                           "Knuth's sequence (3k+1)", "Hibbard's sequence (2^k-1)"};
        
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Gap sequence: " + gapNames[gapChoice]);
        System.out.println("Time Complexity: Depends on gap sequence (O(n^1.25) to O(n^2))");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Element shifts: " + shifts);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Not stable (may change relative order of equal elements)");
        System.out.println("Advantages: Better than insertion sort for larger arrays");
        System.out.println("Algorithm: Generalization of insertion sort with gap-based comparisons");
    }
}
