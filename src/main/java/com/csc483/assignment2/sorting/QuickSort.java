package com.csc483.assignment2.sorting;

/**
 * Quick sort implementation using last element as pivot.
 */
public final class QuickSort {

    private QuickSort() {
    }

    public static SortMetrics sort(int[] arr) {
        SortMetrics metrics = new SortMetrics();
        if (arr == null || arr.length <= 1) {
            return metrics;
        }
        quickSort(arr, 0, arr.length - 1, metrics);
        return metrics;
    }

    private static void quickSort(int[] arr, int low, int high, SortMetrics metrics) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, metrics);
            quickSort(arr, low, pivotIndex - 1, metrics);
            quickSort(arr, pivotIndex + 1, high, metrics);
        }
    }

    private static int partition(int[] arr, int low, int high, SortMetrics metrics) {
        int pivot = arr[high];
        metrics.addAssignments(1);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, metrics);
            }
        }

        swap(arr, i + 1, high, metrics);
        return i + 1;
    }

    private static void swap(int[] arr, int first, int second, SortMetrics metrics) {
        if (first == second) {
            return;
        }
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
        metrics.addAssignments(3);
    }
}
