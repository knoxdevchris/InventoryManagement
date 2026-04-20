package com.inventory.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    @Test
    void electronicsReportsCategory() {
        Electronics e = new Electronics("E1", "Phone", 499.99, 3);
        assertEquals(Category.ELECTRONICS, e.getCategory());
        assertEquals(12, e.getWarrantyMonths());
    }

    @Test
    void foodDetectsExpiration() {
        Food milk = new Food("F1", "Milk", 2.50, 10, LocalDate.of(2025, 1, 1));
        assertTrue(milk.isExpired(LocalDate.of(2025, 6, 1)));
        assertFalse(milk.isExpired(LocalDate.of(2024, 6, 1)));
    }

    @Test
    void clothingStoresSize() {
        Clothing shirt = new Clothing("C1", "Shirt", 19.99, 5, "M");
        assertEquals("M", shirt.getSize());
        assertEquals(Category.CLOTHING, shirt.getCategory());
    }

    @Test
    void rejectsInvalidConstructorArgs() {
        assertThrows(IllegalArgumentException.class,
                () -> new Electronics("", "Phone", 1.0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> new Electronics("E1", " ", 1.0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> new Electronics("E1", "Phone", -1.0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> new Electronics("E1", "Phone", 1.0, -1));
    }

    @Test
    void productsWithSameIdAreEqual() {
        Product a = new Electronics("E1", "Phone", 1.0, 1);
        Product b = new Clothing("E1", "Shirt", 2.0, 2, "L");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void quantitySetterValidates() {
        Product p = new Electronics("E1", "Phone", 1.0, 1);
        p.setQuantity(10);
        assertEquals(10, p.getQuantity());
        assertThrows(IllegalArgumentException.class, () -> p.setQuantity(-1));
    }
}
