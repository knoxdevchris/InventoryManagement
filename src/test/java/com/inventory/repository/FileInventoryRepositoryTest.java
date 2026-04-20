package com.inventory.repository;

import com.inventory.model.Electronics;
import com.inventory.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileInventoryRepositoryTest {

    @Test
    void loadingMissingFileReturnsEmptyList(@TempDir Path tmp) {
        InventoryRepository repo = new FileInventoryRepository(tmp.resolve("missing.dat"));
        assertTrue(repo.load().isEmpty());
    }

    @Test
    void saveThenLoadRoundtripsProducts(@TempDir Path tmp) {
        Path file = tmp.resolve("inventory.dat");
        InventoryRepository repo = new FileInventoryRepository(file);

        repo.save(List.of(new Electronics("E1", "Phone", 499.0, 2, 24)));

        List<Product> loaded = repo.load();
        assertEquals(1, loaded.size());
        assertEquals("E1", loaded.get(0).getProductId());
    }

    @Test
    void saveCreatesMissingParentDirectories(@TempDir Path tmp) {
        Path file = tmp.resolve("nested/dir/inventory.dat");
        InventoryRepository repo = new FileInventoryRepository(file);
        repo.save(List.of(new Electronics("E1", "Phone", 1.0, 1)));
        assertEquals(1, repo.load().size());
    }
}
