package com.inventory.service;

import com.inventory.exception.DuplicateProductException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Category;
import com.inventory.model.Clothing;
import com.inventory.model.Electronics;
import com.inventory.model.Food;
import com.inventory.model.Product;
import com.inventory.repository.InMemoryInventoryRepository;
import com.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryServiceTest {
    private InventoryRepository repository;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryInventoryRepository();
        service = new InventoryService(repository);
    }

    @Test
    void addFindAndRemove() {
        Product phone = new Electronics("E1", "Phone", 499.0, 2);
        service.add(phone);
        assertEquals(phone, service.find("E1").orElseThrow());
        assertEquals(phone, service.remove("E1"));
        assertTrue(service.find("E1").isEmpty());
    }

    @Test
    void addingDuplicateIdThrows() {
        service.add(new Electronics("E1", "Phone", 1.0, 1));
        assertThrows(DuplicateProductException.class,
                () -> service.add(new Clothing("E1", "Shirt", 2.0, 1, "L")));
    }

    @Test
    void removingMissingIdThrows() {
        assertThrows(ProductNotFoundException.class, () -> service.remove("missing"));
    }

    @Test
    void updateQuantityUpdatesExistingProduct() {
        service.add(new Electronics("E1", "Phone", 1.0, 1));
        service.updateQuantity("E1", 5);
        assertEquals(5, service.find("E1").orElseThrow().getQuantity());
    }

    @Test
    void listByCategoryReturnsOnlyMatches() {
        service.add(new Electronics("E1", "Phone", 1.0, 1));
        service.add(new Food("F1", "Milk", 2.0, 3, LocalDate.of(2030, 1, 1)));
        service.add(new Clothing("C1", "Shirt", 3.0, 2, "M"));

        List<Product> food = service.listByCategory(Category.FOOD);
        assertEquals(1, food.size());
        assertEquals("F1", food.get(0).getProductId());
    }

    @Test
    void totalsAggregateCorrectly() {
        service.add(new Electronics("E1", "Phone", 100.0, 2));
        service.add(new Clothing("C1", "Shirt", 20.0, 5, "M"));
        assertEquals(7, service.totalUnits());
        assertEquals(300.0, service.totalValue(), 0.001);
    }

    @Test
    void persistRoundtripsThroughRepository() {
        service.add(new Electronics("E1", "Phone", 100.0, 2));
        service.persist();

        InventoryService reloaded = new InventoryService(repository);
        assertEquals(1, reloaded.listAll().size());
        assertEquals("E1", reloaded.listAll().get(0).getProductId());
    }
}
