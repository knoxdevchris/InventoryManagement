package com.inventory.repository;

import com.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryInventoryRepository implements InventoryRepository {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> load() {
        return new ArrayList<>(products);
    }

    @Override
    public void save(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
}
