import java.io.IOException;
import java.util.Scanner;

public class InventoryApp {
    private static Inventory inventory = new Inventory();
    private static final String FILE_NAME = "inventory.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadInventory();

        int option;
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. List Products");
            System.out.println("4. Save Inventory");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    inventory.listProducts();
                    break;
                case 4:
                    saveInventory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (option != 5);

        saveInventory();
    }

    private static void addProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter product type (1. Electronics, 2. Food, 3. Clothes): ");
        int type = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Product product;
        switch (type) {
            case 1:
                product = new Electronics(id, name, price, quantity);
                break;
            case 2:
                // product = new Food(id, name, price, quantity);  // Uncomment once Food is implemented
                return;
            case 3:
                // product = new Clothes(id, name, price, quantity);  // Uncomment once Clothes is implemented
                return;
            default:
                System.out.println("Invalid type!");
                return;
        }

        inventory.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void removeProduct() {
        System.out.print("Enter product ID to remove: ");
        String id = scanner.nextLine();
        inventory.removeProduct(id);
        System.out.println("Product removed successfully!");
    }

    private static void saveInventory() {
        try {
            inventory.saveInventory(FILE_NAME);
            System.out.println("Inventory saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    private static void loadInventory() {
        try {
            inventory.loadInventory(FILE_NAME);
            System.out.println("Inventory loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved inventory found.");
        }
    }
}
