package utils;

import java.util.Scanner;

public class InputUtils {
    public static void printWelcome() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          Welcome to                    ║");
        System.out.println("║       🚀 Algorithm Master! 🚀          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n🎯 Your gateway to exploring world-class algorithms interactively!");
        System.out.println("📚 Learn, visualize, and master searching & sorting algorithms.\n");
    }

    public static void printMainMenu() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           🏠 MAIN MENU                  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  What would you like to explore today?  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  1. 🔍 Searching Algorithms             │");
        System.out.println("│  2. 🗂️  Sorting Algorithms               │");
        System.out.println("│  3. 📊 Algorithm Comparison & Info      │");
        System.out.println("│  0. 👋 Exit Application                 │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    public static void printSearchingMenu() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│        🔍 SEARCHING ALGORITHMS          │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  Choose a searching algorithm:          │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  1. Linear Search        O(n)          │");
        System.out.println("│  2. Binary Search        O(log n)      │");
        System.out.println("│  3. Jump Search          O(√n)         │");
        System.out.println("│  4. Interpolation Search O(log log n)  │");
        System.out.println("│  5. Exponential Search   O(log n)      │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  0. ⬅️  Back to Main Menu               │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    public static void printSortingMenu() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│         🗂️  SORTING ALGORITHMS          │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  Choose a sorting algorithm:            │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  📖 Basic Algorithms:                   │");
        System.out.println("│    1. Bubble Sort        O(n²)         │");
        System.out.println("│    2. Selection Sort     O(n²)         │");
        System.out.println("│    3. Insertion Sort     O(n²)         │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  🚀 Advanced Algorithms:                │");
        System.out.println("│    4. Merge Sort         O(n log n)    │");
        System.out.println("│    5. Quick Sort         O(n log n)    │");
        System.out.println("│    6. Heap Sort          O(n log n)    │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  ⚡ Specialized Algorithms:              │");
        System.out.println("│    7. Radix Sort         O(d×n)        │");
        System.out.println("│    8. Shell Sort         O(n^1.25)     │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  0. ⬅️  Back to Main Menu               │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    public static void printAlgorithmInfo() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│       📊 ALGORITHM INFORMATION          │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│                                         │");
        System.out.println("│  🔍 SEARCHING ALGORITHMS:               │");
        System.out.println("│  • Linear Search    - O(n)             │");
        System.out.println("│  • Binary Search    - O(log n)         │");
        System.out.println("│  • Jump Search      - O(√n)            │");
        System.out.println("│  • Interpolation    - O(log log n)     │");
        System.out.println("│  • Exponential      - O(log n)         │");
        System.out.println("│                                         │");
        System.out.println("│  🗂️  SORTING ALGORITHMS:                │");
        System.out.println("│  📖 Basic: Bubble, Selection, Insertion │");
        System.out.println("│  🚀 Advanced: Merge, Quick, Heap        │");
        System.out.println("│  ⚡ Special: Radix, Shell               │");
        System.out.println("│                                         │");
        System.out.println("│  ✨ All algorithms include:             │");
        System.out.println("│    • Step-by-step visualization         │");
        System.out.println("│    • Performance metrics                │");
        System.out.println("│    • Educational explanations           │");
        System.out.println("│                                         │");
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("\nPress Enter to continue...");
    }

    public static int getInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    printError("Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                printError("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    public static void printError(String msg) {
        System.out.println("❌ [Error] " + msg);
    }

    public static void printGoodbye() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         👋 Thank you for using         ║");
        System.out.println("║        🚀 Algorithm Master! 🚀         ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("🎓 Keep exploring and learning algorithms!");
        System.out.println("💡 Remember: Every expert was once a beginner.\n");
    }
}
