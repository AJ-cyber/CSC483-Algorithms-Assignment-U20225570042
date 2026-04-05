package com.csc483.assignment2.sorting;

import java.util.Arrays;

/**
 * Benchmarks insertion sort, merge sort and quick sort on different datasets.
 */
public final class SortBenchmark {

    private static final int[] SIZES = {100, 1000, 10000, 100000};
    private static final int RUNS = 5;

    private SortBenchmark() {
    }

    public static void main(String[] args) {
        runDataset("RANDOM DATA", DataType.RANDOM);
        runDataset("ALREADY SORTED DATA", DataType.SORTED);
        runDataset("REVERSE SORTED DATA", DataType.REVERSE);
        runDataset("NEARLY SORTED DATA", DataType.NEARLY_SORTED);
        runDataset("MANY DUPLICATES DATA", DataType.DUPLICATES);
    }

    private static void runDataset(String title, DataType dataType) {
        System.out.println("================================================================");
        System.out.println("SORTING ALGORITHMS COMPARISON - " + title);
        System.out.println("================================================================");
        System.out.printf("%-10s %-12s %-12s %-15s %-15s%n",
                "Input Size", "Algorithm", "Time (ms)", "Comparisons", "Assignments");

        for (int size : SIZES) {
            int[] baseData = buildData(dataType, size);
            printRow(size, "Insertion", benchmark(baseData, Algorithm.INSERTION));
            printRow(size, "Merge", benchmark(baseData, Algorithm.MERGE));
            printRow(size, "Quick", benchmark(baseData, Algorithm.QUICK));
        }
        System.out.println();
    }

    private static BenchmarkResult benchmark(int[] baseData, Algorithm algorithm) {
        double[] times = new double[RUNS];
        long comparisons = 0L;
        long assignments = 0L;

        for (int i = 0; i < RUNS; i++) {
            int[] copy = Arrays.copyOf(baseData, baseData.length);
            long start = System.nanoTime();
            SortMetrics metrics;
            switch (algorithm) {
                case INSERTION:
                    metrics = InsertionSort.sort(copy);
                    break;
                case MERGE:
                    metrics = MergeSort.sort(copy);
                    break;
                default:
                    metrics = QuickSort.sort(copy);
                    break;
            }
            long end = System.nanoTime();

            times[i] = (end - start) / 1000000.0;
            comparisons += metrics.getComparisons();
            assignments += metrics.getAssignments();
        }

        double mean = mean(times);
        double stdDev = standardDeviation(times, mean);
        return new BenchmarkResult(mean, stdDev, comparisons / RUNS, assignments / RUNS);
    }

    private static void printRow(int size, String algorithm, BenchmarkResult result) {
        System.out.printf("%-10d %-12s %-12.3f %-15d %-15d%n",
                size, algorithm, result.meanMillis, result.averageComparisons, result.averageAssignments);
    }

    private static int[] buildData(DataType dataType, int size) {
        switch (dataType) {
            case RANDOM:
                return DataFactory.randomData(size);
            case SORTED:
                return DataFactory.sortedData(size);
            case REVERSE:
                return DataFactory.reverseSortedData(size);
            case NEARLY_SORTED:
                return DataFactory.nearlySortedData(size);
            default:
                return DataFactory.manyDuplicatesData(size);
        }
    }

    private static double mean(double[] values) {
        double total = 0.0;
        for (double value : values) {
            total += value;
        }
        return total / values.length;
    }

    private static double standardDeviation(double[] values, double mean) {
        double total = 0.0;
        for (double value : values) {
            double difference = value - mean;
            total += difference * difference;
        }
        return Math.sqrt(total / values.length);
    }

    private enum DataType {
        RANDOM, SORTED, REVERSE, NEARLY_SORTED, DUPLICATES
    }

    private enum Algorithm {
        INSERTION, MERGE, QUICK
    }

    private static final class BenchmarkResult {
        private final double meanMillis;
        private final double standardDeviationMillis;
        private final long averageComparisons;
        private final long averageAssignments;

        private BenchmarkResult(double meanMillis, double standardDeviationMillis,
                                long averageComparisons, long averageAssignments) {
            this.meanMillis = meanMillis;
            this.standardDeviationMillis = standardDeviationMillis;
            this.averageComparisons = averageComparisons;
            this.averageAssignments = averageAssignments;
        }
    }
}
