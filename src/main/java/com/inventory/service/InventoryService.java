package com.inventory.service;

import com.inventory.exception.DuplicateProductException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Category;
import com.inventory.model.Product;
import com.inventory.repository.InventoryRepository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class InventoryService {
    private final InventoryRepository repository;
    private final Map<String, Product> products = new LinkedHashMap<>();

    public InventoryService(InventoryRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository");
        for (Product product : repository.load()) {
            products.put(product.getProductId(), product);
        }
    }

    public void add(Product product) {
        Objects.requireNonNull(product, "product");
        if (products.containsKey(product.getProductId())) {
            throw new DuplicateProductException(product.getProductId());
        }
        products.put(product.getProductId(), product);
    }

    public Product remove(String productId) {
        Product removed = products.remove(productId);
        if (removed == null) {
            throw new ProductNotFoundException(productId);
        }
        return removed;
    }

    public Optional<Product> find(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public void updateQuantity(String productId, int quantity) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        product.setQuantity(quantity);
    }

    public List<Product> listAll() {
        return products.values().stream()
                .sorted(Comparator.comparing(Product::getProductId))
                .toList();
    }

    public List<Product> listByCategory(Category category) {
        Objects.requireNonNull(category, "category");
        return products.values().stream()
                .filter(p -> p.getCategory() == category)
                .sorted(Comparator.comparing(Product::getProductId))
                .toList();
    }

    public int totalUnits() {
        return products.values().stream().mapToInt(Product::getQuantity).sum();
    }

    public double totalValue() {
        return products.values().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }

    public void persist() {
        repository.save(List.copyOf(products.values()));
    }
}
