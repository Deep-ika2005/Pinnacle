package task1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Product {
    private String productId;
    private String name;
    private double price;
    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + name + ", Price=" + price + "]";
    }
}
class CartItem {
    private Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = $" + getTotalPrice();
    }
}
class ShoppingCart {
    private List<CartItem> items;
    public ShoppingCart() {
        items = new ArrayList<>();
    }
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(product.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }
    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
    }
    public void updateItemQuantity(String productId, int newQuantity) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
    }
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (CartItem item : items) {
                System.out.println(item);
            }
            System.out.println("Total: $" + getTotal());
        }
    }
    public List<CartItem> getItems() {
        return items;
    }
}
class Checkout {

    public static void processCheckout(ShoppingCart cart) {
        double total = cart.getTotal();
        System.out.println("Your total amount is: $" + total);
        System.out.println("Processing payment...");
        System.out.println("Payment successful! Your order has been placed.");
    }
}
public class eCommerce {
 

    public static void main(String[] args) {
        Product product1 = new Product("P001", "Laptop", 70000.00);
        Product product2 = new Product("P002", "Smartphone", 50000.00);
        Product product3 = new Product("P003", "Headphones", 500.00);
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to the E-Commerce Website!");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Products:");
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;

                case 2:
                    System.out.print("Enter product ID to add to cart: ");
                    String addProductId = scanner.next();
                    System.out.print("Enter quantity: ");
                    int addQuantity = scanner.nextInt();

                    if (addProductId.equals("P001")) {
                        cart.addItem(product1, addQuantity);
                    } else if (addProductId.equals("P002")) {
                        cart.addItem(product2, addQuantity);
                    } else if (addProductId.equals("P003")) {
                        cart.addItem(product3, addQuantity);
                    } else {
                        System.out.println("Invalid product ID.");
                    }
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    System.out.print("Enter product ID to update quantity: ");
                    String updateProductId = scanner.next();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    cart.updateItemQuantity(updateProductId, newQuantity);
                    break;

                case 5:
                    System.out.print("Enter product ID to remove from cart: ");
                    String removeProductId = scanner.next();
                    cart.removeItem(removeProductId);
                    break;

                case 6:
                    Checkout.processCheckout(cart);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
