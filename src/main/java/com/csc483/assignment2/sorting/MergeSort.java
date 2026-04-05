package com.csc483.assignment2.sorting;

/**
 * Merge sort implementation.
 */
public final class MergeSort {

    private MergeSort() {
    }

    public static SortMetrics sort(int[] arr) {
        SortMetrics metrics = new SortMetrics();
        if (arr == null || arr.length <= 1) {
            return metrics;
        }
        mergeSort(arr, 0, arr.length - 1, metrics);
        return metrics;
    }

    private static void mergeSort(int[] arr, int left, int right, SortMetrics metrics) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid, metrics);
            mergeSort(arr, mid + 1, right, metrics);
            merge(arr, left, mid, right, metrics);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, SortMetrics metrics) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
            metrics.addAssignments(1);
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
            metrics.addAssignments(1);
        }

        while (j <= right) {
            temp[k++] = arr[j++];
            metrics.addAssignments(1);
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
            metrics.addAssignments(1);
        }
    }
}
