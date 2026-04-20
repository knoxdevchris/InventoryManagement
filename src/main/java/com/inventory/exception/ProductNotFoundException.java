package com.inventory.exception;

public class ProductNotFoundException extends InventoryException {
    public ProductNotFoundException(String productId) {
        super("No product found with id: " + productId);
    }
}
