package searching;

import java.util.Arrays;
import java.util.Scanner;
import utils.InputUtils;
import utils.VisualizationUtils;

/**
 * Binary Search Algorithm Implementation
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1) for iterative, O(log n) for recursive
 * 
 * Binary search works on sorted arrays by repeatedly dividing
 * the search interval in half.
 */
public class BinarySearch {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Binary Search Algorithm ===");
        System.out.println("Efficiently searches sorted arrays by dividing search space in half.");
        
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
        
        // Choose implementation
        System.out.print("Use recursive implementation? (y/n): ");
        boolean recursive = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Perform search
        long startTime = System.nanoTime();
        int result = recursive ? 
            binarySearchRecursive(array, target, 0, array.length - 1, visualize, 1) :
            binarySearchIterative(array, target, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        displayResults(result, target, endTime - startTime, array.length, recursive);
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
    
    private int binarySearchIterative(int[] array, int target, boolean visualize) {
        int left = 0;
        int right = array.length - 1;
        int step = 1;
        
        if (visualize) {
            System.out.println("\n--- Binary Search Visualization (Iterative) ---");
            VisualizationUtils.printSeparator();
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (visualize) {
                System.out.println("Step " + step + ":");
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
    
    private int binarySearchRecursive(int[] array, int target, int left, int right, boolean visualize, int step) {
        if (left > right) {
            return -1; // Not found
        }
        
        int mid = left + (right - left) / 2;
        
        if (visualize) {
            System.out.println("Step " + step + " (Recursive):");
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
        }
        
        if (array[mid] == target) {
            return mid;
        }
        
        if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right, visualize, step + 1);
        } else {
            return binarySearchRecursive(array, target, left, mid - 1, visualize, step + 1);
        }
    }
    
    private void displayResults(int result, int target, long elapsedTime, int arraySize, boolean recursive) {
        System.out.println("\n--- Search Results ---");
        if (result != -1) {
            System.out.println("✓ Target " + target + " found at index: " + result);
        } else {
            System.out.println("✗ Target " + target + " not found in the array.");
        }
        
        System.out.println("Implementation: " + (recursive ? "Recursive" : "Iterative"));
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: " + (recursive ? "O(log n)" : "O(1)"));
        System.out.println("Array size: " + arraySize);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Max comparisons: " + (int) Math.ceil(Math.log(arraySize) / Math.log(2)));
    }
}
