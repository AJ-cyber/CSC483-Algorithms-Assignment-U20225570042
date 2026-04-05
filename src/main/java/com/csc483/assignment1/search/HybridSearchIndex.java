package com.csc483.assignment1.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hybrid search helper that maintains a name index and supports sorted insertion by ID.
 */
public class HybridSearchIndex {

    private final Map<String, List<Product>> nameIndex = new HashMap<>();

    /**
     * Builds the name index from a product array.
     *
     * @param products products to index
     */
    public HybridSearchIndex(Product[] products) {
        if (products != null) {
            for (Product product : products) {
                if (product != null) {
                    addToNameIndex(product);
                }
            }
        }
    }

    /**
     * Returns all products that match a name.
     *
     * @param name product name
     * @return list of matching products
     */
    public List<Product> searchByName(String name) {
        if (name == null || name.isBlank()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(nameIndex.getOrDefault(name.trim(), new ArrayList<>()));
    }

    /**
     * Adds a product to a sorted array while maintaining sorted order by product ID.
     * A new array is returned because Java arrays have fixed size.
     *
     * @param products sorted array of products
     * @param newProduct product to add
     * @return new sorted array containing the inserted product
     */
    public Product[] addProduct(Product[] products, Product newProduct) {
        if (newProduct == null) {
            throw new IllegalArgumentException("newProduct must not be null.");
        }

        Product[] source = products == null ? new Product[0] : products;
        Product[] result = new Product[source.length + 1];

        int low = 0;
        int high = source.length - 1;
        int insertIndex = source.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (source[mid].getProductId() < newProduct.getProductId()) {
                low = mid + 1;
            } else {
                insertIndex = mid;
                high = mid - 1;
            }
        }

        System.arraycopy(source, 0, result, 0, insertIndex);
        result[insertIndex] = newProduct;
        System.arraycopy(source, insertIndex, result, insertIndex + 1, source.length - insertIndex);

        addToNameIndex(newProduct);
        return result;
    }

    private void addToNameIndex(Product product) {
        nameIndex.computeIfAbsent(product.getProductName(), key -> new ArrayList<>()).add(product);
    }
}
