package com.inventory.model;

public class Electronics extends Product {
    private static final long serialVersionUID = 1L;

    private final int warrantyMonths;

    public Electronics(String productId, String name, double price, int quantity) {
        this(productId, name, price, quantity, 12);
    }

    public Electronics(String productId, String name, double price, int quantity, int warrantyMonths) {
        super(productId, name, price, quantity);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("warrantyMonths must not be negative");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public Category getCategory() {
        return Category.ELECTRONICS;
    }
}
