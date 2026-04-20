package com.inventory.model;

public enum Category {
    ELECTRONICS("Electronics"),
    FOOD("Food"),
    CLOTHING("Clothing");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
