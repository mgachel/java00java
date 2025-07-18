package core;

import java.util.Scanner;
import searching.*;
import sorting.*;
import utils.InputUtils;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        InputUtils.printWelcome();
        
        while (true) {
            InputUtils.printMainMenu();
            int choice = InputUtils.getInt(scanner, "🎯 Enter your choice:", 0, 3);
            
            switch (choice) {
                case 0:
                    InputUtils.printGoodbye();
                    return;
                case 1:
                    handleSearchingMenu();
                    break;
                case 2:
                    handleSortingMenu();
                    break;
                case 3:
                    handleAlgorithmInfo();
                    break;
                default:
                    InputUtils.printError("Invalid selection. Please choose a valid option.");
            }
        }
    }
    
    private static void handleSearchingMenu() {
        while (true) {
            InputUtils.printSearchingMenu();
            int choice = InputUtils.getInt(scanner, "🔍 Choose a searching algorithm:", 0, 5);
            
            if (choice == 0) {
                break; // Return to main menu
            }
            
            System.out.println("\n" + "=".repeat(50));
            switch (choice) {
                case 1:
                    System.out.println("🚀 Starting Linear Search...");
                    new LinearSearch().run(scanner);
                    break;
                case 2:
                    System.out.println("🚀 Starting Binary Search...");
                    new BinarySearch().run(scanner);
                    break;
                case 3:
                    System.out.println("🚀 Starting Jump Search...");
                    new JumpSearch().run(scanner);
                    break;
                case 4:
                    System.out.println("🚀 Starting Interpolation Search...");
                    new InterpolationSearch().run(scanner);
                    break;
                case 5:
                    System.out.println("🚀 Starting Exponential Search...");
                    new ExponentialSearch().run(scanner);
                    break;
                default:
                    InputUtils.printError("Invalid selection.");
            }
            
            if (choice >= 1 && choice <= 5) {
                System.out.println("\n" + "=".repeat(50));
                System.out.print("Press Enter to return to searching menu...");
                scanner.nextLine(); // Consume any leftover input
                scanner.nextLine(); // Wait for user input
            }
        }
    }
    
    private static void handleSortingMenu() {
        while (true) {
            InputUtils.printSortingMenu();
            int choice = InputUtils.getInt(scanner, "🗂️ Choose a sorting algorithm:", 0, 8);
            
            if (choice == 0) {
                break; // Return to main menu
            }
            
            System.out.println("\n" + "=".repeat(50));
            switch (choice) {
                case 1:
                    System.out.println("🚀 Starting Bubble Sort...");
                    new BubbleSort().run(scanner);
                    break;
                case 2:
                    System.out.println("🚀 Starting Selection Sort...");
                    new SelectionSort().run(scanner);
                    break;
                case 3:
                    System.out.println("🚀 Starting Insertion Sort...");
                    new InsertionSort().run(scanner);
                    break;
                case 4:
                    System.out.println("🚀 Starting Merge Sort...");
                    new MergeSort().run(scanner);
                    break;
                case 5:
                    System.out.println("🚀 Starting Quick Sort...");
                    new QuickSort().run(scanner);
                    break;
                case 6:
                    System.out.println("🚀 Starting Heap Sort...");
                    new HeapSort().run(scanner);
                    break;
                case 7:
                    System.out.println("🚀 Starting Radix Sort...");
                    new RadixSort().run(scanner);
                    break;
                case 8:
                    System.out.println("🚀 Starting Shell Sort...");
                    new ShellSort().run(scanner);
                    break;
                default:
                    InputUtils.printError("Invalid selection.");
            }
            
            if (choice >= 1 && choice <= 8) {
                System.out.println("\n" + "=".repeat(50));
                System.out.print("Press Enter to return to sorting menu...");
                scanner.nextLine(); // Consume any leftover input
                scanner.nextLine(); // Wait for user input
            }
        }
    }
    
    private static void handleAlgorithmInfo() {
        InputUtils.printAlgorithmInfo();
        scanner.nextLine(); // Consume any leftover input
        scanner.nextLine(); // Wait for user input
    }
}
