package com.inventory.model;

import java.time.LocalDate;
import java.util.Objects;

public class Food extends Product {
    private static final long serialVersionUID = 1L;

    private final LocalDate expirationDate;

    public Food(String productId, String name, double price, int quantity, LocalDate expirationDate) {
        super(productId, name, price, quantity);
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate");
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired(LocalDate today) {
        return expirationDate.isBefore(today);
    }

    @Override
    public Category getCategory() {
        return Category.FOOD;
    }
}
