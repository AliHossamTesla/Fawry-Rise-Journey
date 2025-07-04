import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;
    private double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addToCart(Product product, int quantity) {
        // Enhanced validation for corner cases
        if (product == null) {
            System.out.println("Error: Cannot add null product to cart.");
            return;
        }
        
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0. Requested: " + quantity);
            return;
        }
        
        if (product.getPrice() <= 0) {
            System.out.println("Error: Product price must be greater than 0. Product: " + product.getName());
            return;
        }
        
        if (product.getQuantity() <= 0) {
            System.out.println("Error: Product is out of stock. Product: " + product.getName());
            return;
        }
        
        if (quantity > product.getQuantity()) {
            System.out.println("Error: Insufficient stock. Available: " + product.getQuantity() + 
                              ", Requested: " + quantity + ", Product: " + product.getName());
            return;
        }
        
        // Check if product is expired
        if (product.isExpired()) {
            System.out.println("Error: Product is expired. Cannot add to cart. Product: " + product.getName());
            return;
        }
        
        // Create a copy of the product for the cart
        Product cartItem = new Product(product.getName(), quantity, product.getPrice());
        if (product.isHasExpireDate()) {
            cartItem.setExpireDate(product.getExpireDate());
        }
        if (product.isIsshippable()) {
            cartItem.setWeight(product.getWeight());
        }
        
        items.add(cartItem);
        
        // Reduce the product's stock (thread-safe operation)
        synchronized (product) {
            product.setQuantity(product.getQuantity() - quantity);
        }
        
        // Update total price
        totalPrice += quantity * cartItem.getPrice();
        
        System.out.println(quantity + " " + product.getName() + "(s) added to cart.");
    }

    public void removeFromCart(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Error: Product name cannot be null or empty.");
            return;
        }
        
        boolean removed = false;
        double removedPrice = 0.0;
        
        for (int i = items.size() - 1; i >= 0; i--) {
            Product item = items.get(i);
            if (item.getName().equals(productName)) {
                removedPrice += item.getQuantity() * item.getPrice();
                items.remove(i);
                removed = true;
            }
        }
        
        if (removed) {
            totalPrice -= removedPrice;
            System.out.println(productName + " removed from cart.");
        } else {
            System.out.println(productName + " not found in cart.");
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        
        System.out.println("\n=== YOUR CART ===");
        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + 
                              " - Quantity: " + item.getQuantity() + 
                              " - Price: $" + String.format("%.2f", item.getPrice()) +
                              " - Subtotal: $" + String.format("%.2f", item.getQuantity() * item.getPrice()));
        }
        System.out.println("Total items: " + getTotalItems());
        System.out.println("Total price: $" + String.format("%.2f", totalPrice));
    }

    public int getTotalItems() {
        return items.stream().mapToInt(Product::getQuantity).sum();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0.0;
        System.out.println("Cart cleared.");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // Additional utility methods for better cart management
    public int getItemCount() {
        return items.size();
    }
    
    public boolean containsProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return false;
        }
        return items.stream().anyMatch(item -> item.getName().equals(productName));
    }
}
