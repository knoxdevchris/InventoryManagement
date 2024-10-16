import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productId) {
        products.removeIf(p -> p.getProductId().equals(productId));
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void listProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void saveInventory(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
        }
    }

    public void loadInventory(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (List<Product>) ois.readObject();
        }
    }
}
