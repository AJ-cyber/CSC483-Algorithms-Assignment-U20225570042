package com.csc483.assignment1.search;

/**
 * Provides search algorithms for product arrays.
 */
public final class ProductSearch {

    private ProductSearch() {
    }

    /**
     * Performs sequential search by product ID.
     *
     * @param products array of products
     * @param targetId target ID
     * @return matching product or null
     */
    public static Product sequentialSearchById(Product[] products, int targetId) {
        if (products == null || products.length == 0) {
            return null;
        }

        for (Product product : products) {
            if (product != null && product.getProductId() == targetId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Performs binary search by product ID on a sorted array.
     *
     * @param products sorted array of products
     * @param targetId target ID
     * @return matching product or null
     */
    public static Product binarySearchById(Product[] products, int targetId) {
        if (products == null || products.length == 0) {
            return null;
        }

        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Product current = products[mid];

            if (current == null) {
                return null;
            }

            if (current.getProductId() == targetId) {
                return current;
            } else if (current.getProductId() < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    /**
     * Performs sequential search by product name.
     *
     * @param products array of products
     * @param targetName target name
     * @return matching product or null
     */
    public static Product searchByName(Product[] products, String targetName) {
        if (products == null || products.length == 0 || targetName == null || targetName.isBlank()) {
            return null;
        }

        for (Product product : products) {
            if (product != null && product.getProductName().equalsIgnoreCase(targetName.trim())) {
                return product;
            }
        }
        return null;
    }
}
