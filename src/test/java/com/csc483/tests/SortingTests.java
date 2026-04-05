package com.csc483.tests;

import com.csc483.assignment2.sorting.InsertionSort;
import com.csc483.assignment2.sorting.MergeSort;
import com.csc483.assignment2.sorting.QuickSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Tests sorting algorithm public methods.
 */
class SortingTests {

    @Test
    void insertionSortSortsCorrectly() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    void mergeSortSortsCorrectly() {
        int[] arr = {4, 3, 2, 1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr);
    }

    @Test
    void quickSortSortsCorrectly() {
        int[] arr = {7, 7, 3, 9, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 7, 7, 9}, arr);
    }

    @Test
    void sortingHandlesEmptyArray() {
        int[] arr = {};
        InsertionSort.sort(arr);
        MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
}
