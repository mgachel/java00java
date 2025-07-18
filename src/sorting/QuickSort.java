package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Quick Sort Algorithm Implementation
 * 
 * Time Complexity: O(n log n) average, O(n²) worst case
 * Space Complexity: O(log n) average due to recursion
 * 
 * Quick sort is a divide-and-conquer algorithm that picks a pivot
 * and partitions the array around it.
 */
public class QuickSort {
    
    private int comparisons = 0;
    private int swaps = 0;
    private boolean visualize = false;
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Quick Sort Algorithm ===");
        System.out.println("Divide-and-conquer algorithm using pivot-based partitioning.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        this.visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Choose pivot strategy
        System.out.println("Choose pivot strategy:");
        System.out.println("1. Last element (Lomuto partition)");
        System.out.println("2. First element");
        System.out.println("3. Random element");
        int pivotChoice = InputUtils.getInt(scanner, "Enter choice:", 1, 3);
        
        // Reset counters
        comparisons = 0;
        swaps = 0;
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        quickSort(sortedArray, 0, sortedArray.length - 1, pivotChoice, 0);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, swaps, endTime - startTime, array.length, pivotChoice);
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
    
    private void quickSort(int[] array, int low, int high, int pivotChoice, int depth) {
        if (low < high) {
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "QuickSort: [" + low + ", " + high + "]");
                printSubArray(array, low, high, indent + "  ");
            }
            
            // Choose and position pivot
            choosePivot(array, low, high, pivotChoice);
            
            // Partition the array and get pivot index
            int pivotIndex = partition(array, low, high, depth);
            
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "Pivot " + array[pivotIndex] + " placed at index " + pivotIndex);
                printSubArray(array, low, high, indent + "  ");
                VisualizationUtils.pauseForVisualization();
            }
            
            // Recursively sort elements before and after partition
            quickSort(array, low, pivotIndex - 1, pivotChoice, depth + 1);
            quickSort(array, pivotIndex + 1, high, pivotChoice, depth + 1);
        }
    }
    
    private void choosePivot(int[] array, int low, int high, int pivotChoice) {
        switch (pivotChoice) {
            case 2: // First element
                swap(array, low, high);
                break;
            case 3: // Random element
                int randomIndex = low + (int) (Math.random() * (high - low + 1));
                swap(array, randomIndex, high);
                break;
            // Case 1 (last element) needs no action as partition assumes last element is pivot
        }
    }
    
    private int partition(int[] array, int low, int high, int depth) {
        int pivot = array[high]; // Last element as pivot
        int i = low - 1; // Index of smaller element
        
        if (visualize) {
            String indent = "  ".repeat(depth);
            System.out.println(indent + "Partitioning with pivot: " + pivot);
        }
        
        for (int j = low; j < high; j++) {
            comparisons++;
            
            if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "  Comparing " + array[j] + " with pivot " + pivot);
            }
            
            if (array[j] <= pivot) {
                i++;
                if (i != j) {
                    swap(array, i, j);
                    if (visualize) {
                        String indent = "  ".repeat(depth);
                        System.out.println(indent + "    → " + array[j] + " <= " + pivot + 
                                         ", swapping positions " + i + " and " + j);
                        printSubArray(array, low, high, indent + "      ");
                    }
                } else if (visualize) {
                    String indent = "  ".repeat(depth);
                    System.out.println(indent + "    → " + array[j] + " <= " + pivot + 
                                     ", already in correct relative position");
                }
            } else if (visualize) {
                String indent = "  ".repeat(depth);
                System.out.println(indent + "    → " + array[j] + " > " + pivot + 
                                 ", stays in larger partition");
            }
            
            if (visualize) {
                VisualizationUtils.pauseForVisualization();
            }
        }
        
        // Place pivot in correct position
        swap(array, i + 1, high);
        
        return i + 1;
    }
    
    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            swaps++;
        }
    }
    
    private void printSubArray(int[] array, int left, int right, String indent) {
        System.out.print(indent + "[");
        for (int i = left; i <= right; i++) {
            System.out.print(array[i]);
            if (i < right) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    private void displayResults(int comparisons, int swaps, long elapsedTime, int arraySize, int pivotChoice) {
        String[] pivotStrategies = {"", "Last element (Lomuto)", "First element", "Random element"};
        
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Pivot strategy: " + pivotStrategies[pivotChoice]);
        System.out.println("Time Complexity: O(n log n) average, O(n²) worst case");
        System.out.println("Space Complexity: O(log n) average due to recursion");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Swaps made: " + swaps);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Not stable (may change relative order of equal elements)");
        System.out.println("Advantages: In-place sorting, good average-case performance");
        System.out.println("Note: Performance depends on pivot selection and input distribution");
    }
}
