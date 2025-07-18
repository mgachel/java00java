package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Radix Sort Algorithm Implementation
 * 
 * Time Complexity: O(d * (n + k)) where d is digits, k is range
 * Space Complexity: O(n + k)
 * 
 * Radix sort is a non-comparative sorting algorithm that sorts
 * by processing digits from least significant to most significant.
 */
public class RadixSort {
    
    private int passes = 0;
    private boolean visualize = false;
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Radix Sort Algorithm ===");
        System.out.println("Non-comparative algorithm that sorts by individual digits/characters.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Check for negative numbers
        boolean hasNegative = Arrays.stream(array).anyMatch(x -> x < 0);
        if (hasNegative) {
            System.out.println("Warning: Array contains negative numbers.");
            System.out.print("Continue with absolute values? (y/n): ");
            scanner.nextLine(); // consume newline
            if (!scanner.nextLine().toLowerCase().startsWith("y")) {
                return;
            }
            // Convert to absolute values
            for (int i = 0; i < array.length; i++) {
                array[i] = Math.abs(array[i]);
            }
            VisualizationUtils.printArray(array, "Array with absolute values:");
        }
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        if (!hasNegative) scanner.nextLine(); // consume newline only if not already consumed
        this.visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Reset counters
        passes = 0;
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        radixSort(sortedArray);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(passes, endTime - startTime, array.length);
    }
    
    private int[] getArrayInput(Scanner scanner) {
        int size = InputUtils.getInt(scanner, "Enter array size:", 1, 100);
        int[] array = new int[size];
        
        System.out.println("Enter " + size + " integers:");
        System.out.println("Note: Radix sort works best with non-negative integers.");
        for (int i = 0; i < size; i++) {
            array[i] = InputUtils.getInt(scanner, "Element [" + i + "]:", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        return array;
    }
    
    private void radixSort(int[] array) {
        // Find the maximum number to know number of digits
        int max = getMax(array);
        
        if (visualize) {
            System.out.println("\n--- Radix Sort Visualization ---");
            System.out.println("Maximum value: " + max);
            System.out.println("Number of digits in max: " + String.valueOf(max).length());
            VisualizationUtils.printSeparator();
        }
        
        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            passes++;
            
            if (visualize) {
                System.out.println("Pass " + passes + " - Sorting by digit at position " + 
                                 getDigitPosition(exp));
                System.out.println("Current exponent: " + exp);
            }
            
            countingSort(array, exp);
            
            if (visualize) {
                VisualizationUtils.printArray(array, "Array after pass " + passes + ":");
                VisualizationUtils.printSeparator();
            }
        }
    }
    
    private int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    private String getDigitPosition(int exp) {
        if (exp == 1) return "ones";
        if (exp == 10) return "tens";
        if (exp == 100) return "hundreds";
        if (exp == 1000) return "thousands";
        return "10^" + (int)(Math.log10(exp));
    }
    
    private void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n]; // output array
        int[] count = new int[10]; // count array for digits 0-9
        
        // Initialize count array
        Arrays.fill(count, 0);
        
        // Store count of occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }
        
        if (visualize) {
            System.out.println("  Digit frequency count:");
            for (int i = 0; i < 10; i++) {
                if (count[i] > 0) {
                    System.out.println("    Digit " + i + ": " + count[i] + " occurrences");
                }
            }
        }
        
        // Change count[i] so that it contains actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        if (visualize) {
            System.out.println("  Cumulative positions:");
            for (int i = 0; i < 10; i++) {
                if (count[i] > 0) {
                    System.out.println("    Digit " + i + " and below: " + count[i] + " total elements");
                }
            }
        }
        
        // Build the output array by traversing from right to left
        // This maintains stability
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
            
            if (visualize) {
                System.out.println("  Placing " + array[i] + " (digit " + digit + 
                                 ") at position " + count[digit]);
                VisualizationUtils.pauseForVisualization();
            }
        }
        
        // Copy the output array to array[], so that array[] now contains sorted numbers
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
    
    private void displayResults(int passes, long elapsedTime, int arraySize) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Time Complexity: O(d * (n + k)) where d = digits, k = range");
        System.out.println("Space Complexity: O(n + k)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Passes made: " + passes + " (one per digit position)");
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Stable (maintains relative order of equal elements)");
        System.out.println("Algorithm type: Non-comparative, digit-based sorting");
        System.out.println("Best for: Integer data with limited range, fixed-length strings");
        System.out.println("Note: Each pass uses counting sort as a stable subroutine");
    }
}
