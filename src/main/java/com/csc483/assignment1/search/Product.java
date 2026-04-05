package com.csc483.assignment1.search;

/**
 * Represents a product in the TechMart store.
 */
public class Product {

    private final int productId;
    private final String productName;
    private final String category;
    private final double price;
    private final int stockQuantity;

    /**
     * Creates a product object.
     *
     * @param productId unique product ID
     * @param productName product name
     * @param category product category
     * @param price product price
     * @param stockQuantity available stock
     */
    public Product(int productId, String productName, String category, double price, int stockQuantity) {
        if (productId <= 0) {
            throw new IllegalArgumentException("productId must be positive.");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("productName must not be blank.");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("category must not be blank.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price must not be negative.");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("stockQuantity must not be negative.");
        }
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{id=" + productId
                + ", name='" + productName + '\''
                + ", category='" + category + '\''
                + ", price=" + price
                + ", stock=" + stockQuantity + '}';
    }
}
