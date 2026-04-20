package com.inventory.exception;

public class DuplicateProductException extends InventoryException {
    public DuplicateProductException(String productId) {
        super("A product already exists with id: " + productId);
    }
}
