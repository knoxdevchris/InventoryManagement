package com.inventory.repository;

import com.inventory.model.Product;

import java.util.List;

public interface InventoryRepository {
    List<Product> load();

    void save(List<Product> products);
}
