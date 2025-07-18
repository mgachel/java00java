package searching;

import java.util.Scanner;
import utils.InputUtils;
import utils.VisualizationUtils;

/**
 * Linear Search Algorithm Implementation
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Linear search checks every element in the array sequentially
 * until it finds the target value or reaches the end.
 */
public class LinearSearch {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Linear Search Algorithm ===");
        System.out.println("Searches through each element sequentially.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        // Get search target
        int target = InputUtils.getInt(scanner, "Enter the value to search for:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        boolean visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Perform search
        long startTime = System.nanoTime();
        int result = linearSearch(array, target, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        displayResults(result, target, endTime - startTime, array.length);
    }
    
    private int[] getArrayInput(Scanner scanner) {
        int size = InputUtils.getInt(scanner, "Enter array size:", 1, 1000);
        int[] array = new int[size];
        
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = InputUtils.getInt(scanner, "Element [" + i + "]:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        VisualizationUtils.printArray(array, "Input array:");
        return array;
    }
    
    private int linearSearch(int[] array, int target, boolean visualize) {
        if (visualize) {
            System.out.println("\n--- Linear Search Visualization ---");
            VisualizationUtils.printSeparator();
        }
        
        for (int i = 0; i < array.length; i++) {
            if (visualize) {
                System.out.println("Step " + (i + 1) + ": Checking index " + i + " (value: " + array[i] + ")");
                
                if (array[i] == target) {
                    System.out.println("✓ Found! Target " + target + " matches value at index " + i);
                } else {
                    System.out.println("✗ No match. " + array[i] + " ≠ " + target);
                }
                
                if (i < array.length - 1) {
                    VisualizationUtils.pauseForVisualization();
                }
            }
            
            if (array[i] == target) {
                return i; // Found at index i
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
        
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Comparisons made: " + (result == -1 ? arraySize : result + 1));
    }
}
