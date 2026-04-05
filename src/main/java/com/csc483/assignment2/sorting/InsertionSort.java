package com.csc483.assignment2.sorting;

/**
 * Insertion sort implementation.
 */
public final class InsertionSort {

    private InsertionSort() {
    }

    public static SortMetrics sort(int[] arr) {
        SortMetrics metrics = new SortMetrics();
        if (arr == null) {
            return metrics;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            metrics.addAssignments(1);
            int j = i - 1;

            while (j >= 0) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.addAssignments(1);
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
            metrics.addAssignments(1);
        }

        return metrics;
    }
}
