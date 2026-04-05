package com.csc483.tests;

import com.csc483.assignment1.search.DatasetGenerator;
import com.csc483.assignment1.search.HybridSearchIndex;
import com.csc483.assignment1.search.Product;
import com.csc483.assignment1.search.ProductSearch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests search-related public methods.
 */
class SearchTests {

    @Test
    void sequentialSearchReturnsMatchingProduct() {
        Product[] products = {
                new Product(10, "Phone", "Electronics", 100.0, 5),
                new Product(20, "Laptop", "Electronics", 800.0, 2)
        };

        Product result = ProductSearch.sequentialSearchById(products, 20);

        assertNotNull(result);
        assertEquals(20, result.getProductId());
    }

    @Test
    void binarySearchReturnsMatchingProductFromSortedArray() {
        Product[] products = DatasetGenerator.generateProducts(20);
        Arrays.sort(products, (a, b) -> Integer.compare(a.getProductId(), b.getProductId()));

        int target = products[5].getProductId();
        Product result = ProductSearch.binarySearchById(products, target);

        assertNotNull(result);
        assertEquals(target, result.getProductId());
    }

    @Test
    void searchByNameReturnsMatchingProduct() {
        Product[] products = {
                new Product(1, "Keyboard", "Accessories", 40.0, 10),
                new Product(2, "Mouse", "Accessories", 25.0, 8)
        };

        Product result = ProductSearch.searchByName(products, "keyboard");

        assertNotNull(result);
        assertEquals("Keyboard", result.getProductName());
    }

    @Test
    void searchMethodsReturnNullForEmptyArray() {
        Product[] products = new Product[0];

        assertNull(ProductSearch.sequentialSearchById(products, 1));
        assertNull(ProductSearch.binarySearchById(products, 1));
        assertNull(ProductSearch.searchByName(products, "Phone"));
    }

    @Test
    void hybridAddProductMaintainsSortedOrder() {
        Product[] products = {
                new Product(10, "Phone", "Electronics", 100.0, 5),
                new Product(30, "Laptop", "Electronics", 800.0, 2)
        };

        HybridSearchIndex index = new HybridSearchIndex(products);
        Product[] updated = index.addProduct(products, new Product(20, "Tablet", "Electronics", 250.0, 4));

        assertEquals(3, updated.length);
        assertEquals(10, updated[0].getProductId());
        assertEquals(20, updated[1].getProductId());
        assertEquals(30, updated[2].getProductId());
    }

    @Test
    void hybridNameIndexReturnsMatches() {
        Product[] products = {
                new Product(10, "Phone", "Electronics", 100.0, 5),
                new Product(20, "Phone", "Electronics", 150.0, 3)
        };

        HybridSearchIndex index = new HybridSearchIndex(products);

        assertEquals(2, index.searchByName("Phone").size());
    }
}
