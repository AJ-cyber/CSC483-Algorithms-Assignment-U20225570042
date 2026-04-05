package com.csc483.assignment1.search;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Generates product datasets for testing.
 */
public final class DatasetGenerator {

    private static final String[] NAMES = {
            "Laptop", "Phone", "Camera", "Tablet", "Speaker",
            "Headphones", "Monitor", "Keyboard", "Mouse", "Printer"
    };

    private static final String[] CATEGORIES = {
            "Electronics", "Accessories", "Audio", "Computers"
    };

    private static final int ID_MIN = 1;
    private static final int ID_MAX = 200_000;
    private static final long DEFAULT_SEED = 483L;

    private DatasetGenerator() {
    }

    /**
     * Generates an array of products with unique IDs.
     *
     * @param size array size
     * @return generated products
     */
    public static Product[] generateProducts(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative.");
        }
        if (size > ID_MAX) {
            throw new IllegalArgumentException("size is too large for unique IDs in the configured range.");
        }

        Random random = new Random(DEFAULT_SEED);
        Product[] products = new Product[size];
        Set<Integer> usedIds = new HashSet<>();

        for (int i = 0; i < size; i++) {
            int id;
            do {
                id = random.nextInt(ID_MAX - ID_MIN + 1) + ID_MIN;
            } while (!usedIds.add(id));

            String name = NAMES[random.nextInt(NAMES.length)] + "-" + (i % 50);
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            double price = 50.0 + random.nextDouble() * 1000.0;
            int stock = random.nextInt(500);

            products[i] = new Product(id, name, category, Math.round(price * 100.0) / 100.0, stock);
        }

        return products;
    }
}
