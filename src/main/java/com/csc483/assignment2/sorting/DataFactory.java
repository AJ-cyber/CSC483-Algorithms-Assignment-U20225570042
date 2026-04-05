package com.csc483.assignment2.sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Creates input datasets for sorting benchmarks.
 */
public final class DataFactory {

    private static final long DEFAULT_SEED = 483L;

    private DataFactory() {
    }

    public static int[] randomData(int size) {
        Random random = new Random(DEFAULT_SEED + size);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }

    public static int[] sortedData(int size) {
        int[] arr = randomData(size);
        Arrays.sort(arr);
        return arr;
    }

    public static int[] reverseSortedData(int size) {
        int[] arr = sortedData(size);
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    public static int[] nearlySortedData(int size) {
        int[] arr = sortedData(size);
        Random random = new Random(DEFAULT_SEED + size * 7L);
        int swaps = Math.max(1, size / 10);
        for (int i = 0; i < swaps; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    public static int[] manyDuplicatesData(int size) {
        Random random = new Random(DEFAULT_SEED + size * 13L);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }
}
