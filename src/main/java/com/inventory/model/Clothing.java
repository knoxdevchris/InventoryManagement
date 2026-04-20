package com.inventory.model;

import java.util.Objects;

public class Clothing extends Product {
    private static final long serialVersionUID = 1L;

    private final String size;

    public Clothing(String productId, String name, double price, int quantity, String size) {
        super(productId, name, price, quantity);
        this.size = Objects.requireNonNull(size, "size");
    }

    public String getSize() {
        return size;
    }

    @Override
    public Category getCategory() {
        return Category.CLOTHING;
    }
}
