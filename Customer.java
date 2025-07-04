import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int balance;
    private Cart cart;
    private String name;

    public Customer(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public Customer(String name) {
        this(name, 0);
    }

    // Add item to cart with validation
    public void addToCart(Product product, int quantity) {
        // Validate input parameters
        if (product == null) {
            System.out.println("Error: Cannot add null product to cart.");
            return;
        }
        
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0.");
            return;
        }
        
        if (product.getPrice() <= 0) {
            System.out.println("Error: Product price must be greater than 0.");
            return;
        }
        
        cart.addToCart(product, quantity);
    }

    // View cart contents
    public void viewCart() {
        cart.viewCart();
    }

    // Remove item from cart
    public void removeFromCart(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Error: Product name cannot be null or empty.");
            return;
        }
        cart.removeFromCart(productName);
    }

    // Checkout process with shipping fees
    public boolean checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cannot checkout: Cart is empty.");
            return false;
        }

        // Calculate subtotal (sum of all items' prices)
        double subtotal = cart.getTotalPrice();
        
        // Collect shippable items and calculate shipping fees
        List<Shippable> shippableItems = collectShippableItems();
        ShippingService shippingService = new ShippingService();
        double shippingFees = shippingService.calculateShippingFee(shippableItems);
        
        // Calculate total paid amount (subtotal + shipping fees)
        double paidAmount = subtotal + shippingFees;
        
        if (balance >= paidAmount) {
            balance -= paidAmount;
            System.out.println("\n=== CHECKOUT SUCCESSFUL ===");
            System.out.println("Customer: " + name);
            System.out.println("Total items purchased: " + cart.getTotalItems());
            System.out.println("Order subtotal: $" + String.format("%.2f", subtotal));
            System.out.println("Shipping fees: $" + String.format("%.2f", shippingFees));
            System.out.println("Paid amount: $" + String.format("%.2f", paidAmount));
            System.out.println("Customer current balance after payment: $" + balance);
            System.out.println("Thank you for your purchase!");
            
            // Process shipment if there are shippable items
            if (!shippableItems.isEmpty()) {
                shippingService.processShipment(shippableItems);
            }
            
            // Clear the cart after successful checkout
            cart.clearCart();
            return true;
        } else {
            System.out.println("\n=== CHECKOUT FAILED ===");
            System.out.println("Insufficient balance!");
            System.out.println("Order subtotal: $" + String.format("%.2f", subtotal));
            System.out.println("Shipping fees: $" + String.format("%.2f", shippingFees));
            System.out.println("Total required: $" + String.format("%.2f", paidAmount));
            System.out.println("Available balance: $" + balance);
            System.out.println("Please add more funds or remove some items.");
            return false;
        }
    }

    // Add funds to balance with validation
    public void addFunds(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than 0.");
            return;
        }
        balance += amount;
        System.out.println("Added $" + amount + " to balance. New balance: $" + balance);
    }

    // Getter methods
    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean hasItemsInCart() {
        return !cart.isEmpty();
    }
    
    // Collect all shippable items from the cart
    private List<Shippable> collectShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        List<Product> cartItems = cart.getItems();
        
        for (Product item : cartItems) {
            if (item.isIsshippable()) {
                // Add the item to the shippable list
                shippableItems.add(item);
            }
        }
        
        return shippableItems;
    }
}
