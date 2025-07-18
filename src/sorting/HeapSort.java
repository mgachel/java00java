package sorting;

import utils.InputUtils;
import utils.VisualizationUtils;
import java.util.Scanner;

/**
 * Heap Sort Algorithm Implementation
 * 
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(1)
 * 
 * Heap sort uses a binary heap data structure to sort elements.
 * It first builds a max heap, then repeatedly extracts the maximum.
 */
public class HeapSort {
    
    private int comparisons = 0;
    private int swaps = 0;
    private boolean visualize = false;
    
    public void run(Scanner scanner) {
        System.out.println("\n=== Heap Sort Algorithm ===");
        System.out.println("Uses binary heap structure - builds max heap, then repeatedly extracts maximum.");
        
        // Get array input
        int[] array = getArrayInput(scanner);
        if (array == null) return;
        
        VisualizationUtils.printArray(array, "Original array:");
        
        // Ask for visualization
        System.out.print("Enable step-by-step visualization? (y/n): ");
        scanner.nextLine(); // consume newline
        this.visualize = scanner.nextLine().toLowerCase().startsWith("y");
        
        // Reset counters
        comparisons = 0;
        swaps = 0;
        
        // Perform sort
        long startTime = System.nanoTime();
        int[] sortedArray = array.clone();
        heapSort(sortedArray);
        long endTime = System.nanoTime();
        
        // Display results
        VisualizationUtils.printArray(sortedArray, "Sorted array:");
        displayResults(comparisons, swaps, endTime - startTime, array.length);
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
    
    private void heapSort(int[] array) {
        int n = array.length;
        
        if (visualize) {
            System.out.println("\n--- Heap Sort Visualization ---");
            System.out.println("Phase 1: Building Max Heap");
            VisualizationUtils.printSeparator();
        }
        
        // Build max heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            if (visualize) {
                System.out.println("Heapifying subtree rooted at index " + i + " (value: " + array[i] + ")");
            }
            heapify(array, n, i);
        }
        
        if (visualize) {
            VisualizationUtils.printArray(array, "Max heap built:");
            System.out.println("\nPhase 2: Extracting elements from heap");
            VisualizationUtils.printSeparator();
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            if (visualize) {
                System.out.println("Extracting maximum element " + array[0] + " to position " + i);
            }
            
            // Move current root to end
            swap(array, 0, i);
            
            if (visualize) {
                VisualizationUtils.printArray(array, "After moving max to sorted portion:");
                System.out.println("Heap size reduced to " + i + ". Re-heapifying root...");
            }
            
            // Call max heapify on the reduced heap
            heapify(array, i, 0);
            
            if (visualize) {
                VisualizationUtils.printArray(array, "After re-heapifying:");
                VisualizationUtils.printSeparator();
            }
        }
    }
    
    private void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex; // Initialize largest as root
        int leftChild = 2 * rootIndex + 1; // Left child
        int rightChild = 2 * rootIndex + 2; // Right child
        
        if (visualize) {
            System.out.println("  Heapifying node " + rootIndex + " (value: " + array[rootIndex] + ")");
            System.out.println("    Left child: " + (leftChild < heapSize ? 
                             "index " + leftChild + " (value: " + array[leftChild] + ")" : "none"));
            System.out.println("    Right child: " + (rightChild < heapSize ? 
                             "index " + rightChild + " (value: " + array[rightChild] + ")" : "none"));
        }
        
        // If left child is larger than root
        if (leftChild < heapSize) {
            comparisons++;
            if (array[leftChild] > array[largest]) {
                largest = leftChild;
                if (visualize) {
                    System.out.println("    → Left child " + array[leftChild] + " is larger than " + array[rootIndex]);
                }
            }
        }
        
        // If right child is larger than largest so far
        if (rightChild < heapSize) {
            comparisons++;
            if (array[rightChild] > array[largest]) {
                largest = rightChild;
                if (visualize) {
                    System.out.println("    → Right child " + array[rightChild] + " is larger than current largest");
                }
            }
        }
        
        // If largest is not root
        if (largest != rootIndex) {
            if (visualize) {
                System.out.println("    → Swapping " + array[rootIndex] + " (index " + rootIndex + 
                                 ") with " + array[largest] + " (index " + largest + ")");
            }
            
            swap(array, rootIndex, largest);
            
            if (visualize) {
                VisualizationUtils.printArray(array, "    Array after swap:");
                System.out.println("    → Recursively heapifying affected subtree at index " + largest);
            }
            
            // Recursively heapify the affected sub-tree
            heapify(array, heapSize, largest);
        } else if (visualize) {
            System.out.println("    → Node " + rootIndex + " already satisfies heap property");
        }
        
        if (visualize) {
            VisualizationUtils.pauseForVisualization();
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
    }
    
    private void displayResults(int comparisons, int swaps, long elapsedTime, int arraySize) {
        System.out.println("\n--- Sorting Results ---");
        System.out.println("✓ Array successfully sorted!");
        System.out.println("Time Complexity: O(n log n) in all cases");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Array size: " + arraySize);
        System.out.println("Comparisons made: " + comparisons);
        System.out.println("Swaps made: " + swaps);
        System.out.println("Execution time: " + elapsedTime + " nanoseconds");
        System.out.println("Stability: Not stable (may change relative order of equal elements)");
        System.out.println("Advantages: In-place sorting, consistent O(n log n) performance");
        System.out.println("Heap properties: Complete binary tree, parent ≥ children (max heap)");
        System.out.println("Applications: Priority queues, finding k largest/smallest elements");
    }
}
