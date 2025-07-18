package utils;

public class VisualizationUtils {
    public static void printArray(double[] arr, String label) {
        System.out.print(label + " [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void printArray(int[] arr, String label) {
        System.out.print(label + " [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void printSeparator() {
        System.out.println("------------------------------------");
    }

    public static void pauseForVisualization() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
