package com.csc483.assignment1.search;

import java.util.Arrays;

/**
 * Runs basic search performance measurements for best, average and worst cases.
 */
public final class SearchBenchmark {

    private static final int SIZE = 100_000;
    private static final int RUNS = 5;

    private SearchBenchmark() {
    }

    public static void main(String[] args) {
        Product[] unsorted = DatasetGenerator.generateProducts(SIZE);
        Product[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(sorted, (a, b) -> Integer.compare(a.getProductId(), b.getProductId()));

        int sequentialBestId = unsorted[0].getProductId();
        int sequentialAverageId = unsorted[unsorted.length / 2].getProductId();
        int missingId = 999_999;

        int binaryBestId = sorted[sorted.length / 2].getProductId();
        int binaryAverageId = sorted[sorted.length / 3].getProductId();

        HybridSearchIndex index = new HybridSearchIndex(sorted);

        double sequentialBest = averageTimeMillis(() -> ProductSearch.sequentialSearchById(unsorted, sequentialBestId));
        double sequentialAverage = averageTimeMillis(() -> ProductSearch.sequentialSearchById(unsorted, sequentialAverageId));
        double sequentialWorst = averageTimeMillis(() -> ProductSearch.sequentialSearchById(unsorted, missingId));

        double binaryBest = averageTimeMillis(() -> ProductSearch.binarySearchById(sorted, binaryBestId));
        double binaryAverage = averageTimeMillis(() -> ProductSearch.binarySearchById(sorted, binaryAverageId));
        double binaryWorst = averageTimeMillis(() -> ProductSearch.binarySearchById(sorted, missingId));

        String name = sorted[sorted.length / 4].getProductName();
        double hybridNameSearch = averageTimeMillis(() -> index.searchByName(name));
        Product newProduct = new Product(200001, "NewDevice-1", "Electronics", 250.00, 10);
        double hybridInsert = averageTimeMillis(() -> index.addProduct(sorted, newProduct));

        System.out.println("================================================================");
        System.out.println("TECHMART SEARCH PERFORMANCE ANALYSIS (n = 100,000 products)");
        System.out.println("================================================================");
        System.out.printf("SEQUENTIAL SEARCH:%n");
        System.out.printf("Best Case (ID found at position 0): %.3f ms%n", sequentialBest);
        System.out.printf("Average Case (ID found around middle): %.3f ms%n", sequentialAverage);
        System.out.printf("Worst Case (ID not found): %.3f ms%n%n", sequentialWorst);

        System.out.printf("BINARY SEARCH:%n");
        System.out.printf("Best Case (ID at middle): %.3f ms%n", binaryBest);
        System.out.printf("Average Case (existing ID): %.3f ms%n", binaryAverage);
        System.out.printf("Worst Case (ID not found): %.3f ms%n%n", binaryWorst);

        double improvement = binaryAverage == 0.0 ? 0.0 : sequentialAverage / binaryAverage;
        System.out.printf("PERFORMANCE IMPROVEMENT: Binary search is ~%.2fx faster on average%n%n", improvement);

        System.out.printf("HYBRID NAME SEARCH:%n");
        System.out.printf("Average search time: %.3f ms%n", hybridNameSearch);
        System.out.printf("Average insert time: %.3f ms%n", hybridInsert);
        System.out.println("================================================================");
    }

    private static double averageTimeMillis(Runnable task) {
        long totalNanos = 0L;
        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            task.run();
            long end = System.nanoTime();
            totalNanos += (end - start);
        }
        return totalNanos / (RUNS * 1000000.0);
    }
}
