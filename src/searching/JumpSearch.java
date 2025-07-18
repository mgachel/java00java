package searching;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Jump Search Algorithm Implementation
 * 
 * Time Complexity: O(√n)
 * Space Complexity: O(1)
 * 
 * Jump search works on sorted arrays by jumping ahead by fixed steps
 * and then performing linear search in the identified block.
 */
public class JumpSearch {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Jump Search Algorithm ===");
        System.out.println("Jumps ahead by √n steps, then performs linear search in identified block.");
        
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
        int result = jumpSearch(array, target, visualize);
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
    
    private int jumpSearch(int[] array, int target, boolean visualize) {
        int n = array.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;
        
        if (visualize) {
            System.out.println("\n--- Jump Search Visualization ---");
            System.out.println("Jump step size: " + step + " (√" + n + ")");
            VisualizationUtils.printSeparator();
        }
        
        // Jump phase: find the block where element is present
        int jumpStep = 1;
        while (array[Math.min(step, n) - 1] < target) {
            if (visualize) {
                System.out.println("Jump " + jumpStep + ":");
                System.out.println("  Checking index " + (Math.min(step, n) - 1) + 
                                 " (value: " + array[Math.min(step, n) - 1] + ")");
                System.out.println("  " + array[Math.min(step, n) - 1] + " < " + target + 
                                 " → Jump to next block");
                VisualizationUtils.pauseForVisualization();
                jumpStep++;
            }
            
            prev = step;
            step += (int) Math.sqrt(n);
            
            if (prev >= n) {
                return -1; // Element not found
            }
        }
        
        if (visualize) {
            System.out.println("Jump phase complete. Target should be in range [" + 
                             prev + ", " + Math.min(step, n) + "]");
            System.out.println("\n--- Linear Search Phase ---");
        }
        
        // Linear search phase: search in the identified block
        int linearStep = 1;
        while (array[prev] < target) {
            if (visualize) {
                System.out.println("Linear step " + linearStep + ":");
                System.out.println("  Checking index " + prev + " (value: " + array[prev] + ")");
                System.out.println("  " + array[prev] + " < " + target + " → Continue");
                VisualizationUtils.pauseForVisualization();
                linearStep++;
            }
            
            prev++;
            
            // If we reached next block or end of array
            if (prev == Math.min(step, n)) {
                return -1; // Element not found
            }
        }
        
        // Check if element is found
        if (array[prev] == target) {
            if (visualize) {
                System.out.println("Linear step " + linearStep + ":");
                System.out.println("  Checking index " + prev + " (value: " + array[prev] + ")");
                System.out.println("  ✓ Found! " + array[prev] + " = " + target);
            }
            return prev;
        }
        
        return -1; // Element not found
    }
    
    private void displayResults(int result, int target, long elapsedTime, int arraySize) {
        System.out.println("\n--- Search Results ---");
        if (result != -1) {
            System.out.println("✓ Target " + target + " found at index: " + result);
        } else {
            System.out.println("✗ Target " + target + " not found in the array.");
        }
        
        System.out.println("Time Complexity: O(√n)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Jump step size: " + (int) Math.sqrt(arraySize));
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Max comparisons: " + (int) Math.ceil(Math.sqrt(arraySize)) + " jumps + " + 
                         (int) Math.ceil(Math.sqrt(arraySize)) + " linear = " + 
                         (2 * (int) Math.ceil(Math.sqrt(arraySize))));
    }
}
