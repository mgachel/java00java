package searching;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Interpolation Search Algorithm Implementation
 * 
 * Time Complexity: O(log log n) for uniformly distributed data, O(n) worst case
 * Space Complexity: O(1)
 * 
 * Interpolation search improves upon binary search by estimating
 * the position of the target based on the value distribution.
 */
public class InterpolationSearch {
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Interpolation Search Algorithm ===");
        System.out.println("Estimates target position based on value distribution in uniformly distributed arrays.");
        
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
        int result = interpolationSearch(array, target, visualize);
        long endTime = System.nanoTime();
        
        // Display results
        displayResults(result, target, endTime - startTime, array.length);
    }
    
    private int[] getArrayInput(Scanner scanner) {
        int size = InputUtils.getInt(scanner, "Enter array size:", 1, 1000);
        int[] array = new int[size];
        
        System.out.println("Enter " + size + " integers (will be sorted automatically):");
        System.out.println("Note: Works best with uniformly distributed data.");
        for (int i = 0; i < size; i++) {
            array[i] = InputUtils.getInt(scanner, "Element [" + i + "]:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        VisualizationUtils.printArray(array, "Input array:");
        return array;
    }
    
    private int interpolationSearch(int[] array, int target, boolean visualize) {
        int low = 0;
        int high = array.length - 1;
        int step = 1;
        
        if (visualize) {
            System.out.println("\n--- Interpolation Search Visualization ---");
            VisualizationUtils.printSeparator();
        }
        
        while (low <= high && target >= array[low] && target <= array[high]) {
            // Handle case where low == high
            if (low == high) {
                if (array[low] == target) {
                    return low;
                }
                return -1;
            }
            
            // Calculate interpolated position
            // pos = low + [(target - array[low]) / (array[high] - array[low])] * (high - low)
            int pos = low + (int) (((double) (target - array[low]) / 
                                   (array[high] - array[low])) * (high - low));
            
            if (visualize) {
                System.out.println("Step " + step + ":");
                System.out.println("  Search range: [" + low + ", " + high + "]");
                System.out.println("  Values: [" + array[low] + ", " + array[high] + "]");
                System.out.println("  Target: " + target);
                System.out.println("  Interpolated position: " + pos);
                System.out.println("  Formula: " + low + " + [(" + target + " - " + array[low] + 
                                 ") / (" + array[high] + " - " + array[low] + ")] * (" + 
                                 high + " - " + low + ") = " + pos);
                
                if (pos < array.length && pos >= 0) {
                    System.out.println("  Value at position " + pos + ": " + array[pos]);
                    
                    if (array[pos] == target) {
                        System.out.println("  ✓ Found! Target matches interpolated position.");
                    } else if (array[pos] < target) {
                        System.out.println("  → Target is greater. Search right half.");
                    } else {
                        System.out.println("  ← Target is smaller. Search left half.");
                    }
                }
                
                VisualizationUtils.pauseForVisualization();
                step++;
            }
            
            // Ensure pos is within bounds
            if (pos < 0 || pos >= array.length) {
                return -1;
            }
            
            if (array[pos] == target) {
                return pos;
            }
            
            if (array[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
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
        
        System.out.println("Time Complexity: O(log log n) average, O(n) worst case");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Note: Performance depends on data distribution uniformity.");
    }
}
