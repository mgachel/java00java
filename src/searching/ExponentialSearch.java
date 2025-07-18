package searching;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Exponential Search Algorithm Implementation
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 * 
 * Exponential search finds the range where element is present
 * and then uses binary search in that range.
 */
public class ExponentialSearch {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Exponential Search Algorithm ===");
        System.out.println("Finds range exponentially, then uses binary search in that range.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        // Sort the array first
        Arrays.sort(array);
        VisualizationUtils.printArray(array, "Sorted array:");
        
        // Get search target
        int target = InputUtils.getInt(scanner, "Enter the value to search for:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        boolean visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Perform search
        long startTime = System.nanoTime();
        int result = exponentialSearch(array, target, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        displayResults(result, target, endTime - startTime, array.length);
    }
    
    private int[] getArrayInput(Scanner scanner) {
        int size = InputUtils.getInt(scanner, "Enter array size:", 1, 1000);
        int[] array = new int[size];
        
        System.out.println("Enter " + size + " integers (will be sorted automatically):");
        for (int i = 0; i < size; i++) {
            array[i] = InputUtils.getInt(scanner, "Element [" + i + "]:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        VisualizationUtils.printArray(array, "Input array:");
        return array;
    }
    
    private int exponentialSearch(int[] array, int target, boolean visualize) {
        int n = array.length;
        
        // If target is at first position
        if (array[0] == target) {
            if (visualize) {
                System.out.println("\n--- Exponential Search Visualization ---");
                System.out.println("✓ Target found at index 0!");
            }
            return 0;
        }
        
        if (visualize) {
            System.out.println("\n--- Exponential Search Visualization ---");
            System.out.println("Phase 1: Finding range exponentially");
            VisualizationUtils.printSeparator();
        }
        
        // Find range for binary search by repeated doubling
        int i = 1;
        int step = 1;
        while (i < n && array[i] <= target) {
            if (visualize) {
                System.out.println("Exponential step " + step + ":");
                System.out.println("  Checking index " + i + " (value: " + array[i] + ")");
                System.out.println("  " + array[i] + " <= " + target + " → Double the range");
                VisualizationUtils.pauseForVisualization();
                step++;
            }
            i = i * 2;
        }
        
        if (visualize) {
            int left = i / 2;
            int right = Math.min(i, n - 1);
            System.out.println("Range found: [" + left + ", " + right + "]");
            System.out.println("\nPhase 2: Binary search in identified range");
            VisualizationUtils.printSeparator();
        }
        
        // Call binary search for the found range
        return binarySearch(array, target, i / 2, Math.min(i, n - 1), visualize);
    }
    
    private int binarySearch(int[] array, int target, int left, int right, boolean visualize) {
        int step = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (visualize) {
                System.out.println("Binary step " + step + ":");
                System.out.println("  Search range: [" + left + ", " + right + "]");
                System.out.println("  Middle index: " + mid + " (value: " + array[mid] + ")");
                System.out.println("  Target: " + target);
                
                if (array[mid] == target) {
                    System.out.println("  ✓ Found! Target matches middle element.");
                } else if (array[mid] < target) {
                    System.out.println("  → Target is greater. Search right half.");
                } else {
                    System.out.println("  ← Target is smaller. Search left half.");
                }
                
                VisualizationUtils.pauseForVisualization();
                step++;
            }
            
            if (array[mid] == target) {
                return mid;
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Not found
    }
    
    private void displayResults(int result, int target, long elapsedTime, int arraySize) {
        System.out.println("\n--- Search Results ---");
        if (result != -1) {
            System.out.println("✓ Target " + target + " found at index: " + result);
        } else {
            System.out.println("✗ Target " + target + " not found in the array.");
        }
        
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Algorithm: Two-phase (exponential range finding + binary search)");
        System.out.println("Advantage: Unbounded searches (when array size is unknown)");
    }
}
