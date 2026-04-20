package com.inventory.cli;

import com.inventory.exception.InventoryException;
import com.inventory.model.Category;
import com.inventory.model.Clothing;
import com.inventory.model.Electronics;
import com.inventory.model.Food;
import com.inventory.model.Product;
import com.inventory.service.InventoryService;

import java.util.List;

public class ConsoleApp {
    private final InventoryService service;
    private final ConsoleIO io;

    public ConsoleApp(InventoryService service, ConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    public void run() {
        io.print("=== Inventory Management System ===");
        while (true) {
            printMenu();
            int choice = io.readIntInRange("Select an option: ", 1, 8);
            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> removeProduct();
                    case 3 -> updateQuantity();
                    case 4 -> listAll();
                    case 5 -> listByCategory();
                    case 6 -> showSummary();
                    case 7 -> {
                        service.persist();
                        io.print("Inventory saved.");
                    }
                    case 8 -> {
                        service.persist();
                        io.print("Inventory saved. Goodbye!");
                        return;
                    }
                }
            } catch (InventoryException | IllegalArgumentException e) {
                io.print("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        io.print("");
        io.print("1) Add product");
        io.print("2) Remove product");
        io.print("3) Update quantity");
        io.print("4) List all products");
        io.print("5) List by category");
        io.print("6) Summary");
        io.print("7) Save");
        io.print("8) Save and exit");
    }

    private void addProduct() {
        String id = io.readNonEmpty("Product ID: ");
        String name = io.readNonEmpty("Name: ");
        double price = io.readDouble("Price: ");
        int quantity = io.readInt("Quantity: ");

        io.print("Category:");
        io.print("  1) Electronics");
        io.print("  2) Food");
        io.print("  3) Clothing");
        int type = io.readIntInRange("Choose: ", 1, 3);

        Product product = switch (type) {
            case 1 -> {
                int warranty = io.readInt("Warranty (months): ");
                yield new Electronics(id, name, price, quantity, warranty);
            }
            case 2 -> new Food(id, name, price, quantity,
                    io.readDate("Expiration date (YYYY-MM-DD): "));
            case 3 -> new Clothing(id, name, price, quantity,
                    io.readNonEmpty("Size: "));
            default -> throw new IllegalStateException("unreachable");
        };
        service.add(product);
        io.print("Added: " + product);
    }

    private void removeProduct() {
        String id = io.readNonEmpty("Product ID to remove: ");
        Product removed = service.remove(id);
        io.print("Removed: " + removed);
    }

    private void updateQuantity() {
        String id = io.readNonEmpty("Product ID: ");
        int qty = io.readInt("New quantity: ");
        service.updateQuantity(id, qty);
        io.print("Quantity updated.");
    }

    private void listAll() {
        render(service.listAll());
    }

    private void listByCategory() {
        io.print("Category:");
        Category[] values = Category.values();
        for (int i = 0; i < values.length; i++) {
            io.printf("  %d) %s%n", i + 1, values[i].displayName());
        }
        int choice = io.readIntInRange("Choose: ", 1, values.length);
        render(service.listByCategory(values[choice - 1]));
    }

    private void showSummary() {
        io.printf("Total products : %d%n", service.listAll().size());
        io.printf("Total units    : %d%n", service.totalUnits());
        io.printf("Total value    : $%.2f%n", service.totalValue());
    }

    private void render(List<Product> products) {
        if (products.isEmpty()) {
            io.print("(no products)");
            return;
        }
        products.forEach(p -> io.print(" - " + p));
    }
}
