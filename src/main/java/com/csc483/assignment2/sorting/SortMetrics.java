package com.csc483.assignment2.sorting;

/**
 * Stores operation counts for sorting algorithms.
 */
public class SortMetrics {

    private long comparisons;
    private long assignments;

    public void incrementComparisons() {
        comparisons++;
    }

    public void addAssignments(long count) {
        assignments += count;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAssignments() {
        return assignments;
    }
}
