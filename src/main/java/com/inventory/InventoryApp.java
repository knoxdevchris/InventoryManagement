package com.inventory;

import com.inventory.cli.ConsoleApp;
import com.inventory.cli.ConsoleIO;
import com.inventory.repository.FileInventoryRepository;
import com.inventory.repository.InventoryRepository;
import com.inventory.service.InventoryService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public final class InventoryApp {
    private static final String DEFAULT_FILE = "inventory.dat";

    private InventoryApp() {}

    public static void main(String[] args) {
        Path file = Paths.get(args.length > 0 ? args[0] : DEFAULT_FILE);
        InventoryRepository repository = new FileInventoryRepository(file);
        InventoryService service = new InventoryService(repository);

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleIO io = new ConsoleIO(scanner, System.out);
            new ConsoleApp(service, io).run();
        }
    }
}
