package com.inventory.repository;

import com.inventory.exception.InventoryException;
import com.inventory.model.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileInventoryRepository implements InventoryRepository {
    private final Path file;

    public FileInventoryRepository(Path file) {
        this.file = file;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> load() {
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {
            return (List<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InventoryException("Failed to load inventory from " + file, e);
        }
    }

    @Override
    public void save(List<Product> products) {
        try {
            Path parent = file.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {
                out.writeObject(new ArrayList<>(products));
            }
        } catch (IOException e) {
            throw new InventoryException("Failed to save inventory to " + file, e);
        }
    }
}
