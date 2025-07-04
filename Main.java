class Main{
    public static void main(String[] args) {
        // Create 15 products with various properties
        Product[] products = new Product[15];
        // Products with expiry date and shipping (food items)
        products[0] = new Product("Fresh Milk", 50, 3.99, "2024-02-15", 1.0f);
        products[1] = new Product("Organic Yogurt", 30, 2.49, "2024-02-20", 0.5f);
        products[2] = new Product("Whole Grain Bread", 25, 1.99, "2024-02-18", 0.8f);
        products[3] = new Product("Fresh Eggs", 100, 4.99, "2024-03-01", 0.6f);
        products[4] = new Product("Organic Bananas", 75, 2.99, "2024-02-25", 2.0f);
        
        // Products with expiry date but not shippable (perishable items)
        products[5] = new Product("Fresh Tomatoes", 40, 1.49, "2024-02-22");
        products[6] = new Product("Lettuce", 20, 0.99, "2024-02-19");
        products[7] = new Product("Strawberries", 35, 3.49, "2024-02-23");
        
        // Products that are shippable but no expiry date (non-perishable)
        products[8] = new Product("Rice 5kg", 15, 12.99, 5.0f);
        products[9] = new Product("Pasta 1kg", 30, 2.99, 1.0f);
        products[10] = new Product("Olive Oil 500ml", 25, 8.99, 0.5f);
        products[11] = new Product("Canned Beans", 45, 1.99, 0.4f);
        products[12] = new Product("Honey 250g", 20, 4.99, 0.25f);
        
        // Products with no expiry date and not shippable (digital/services)
        products[13] = new Product("Gift Card", 100, 25.00);
        products[14] = new Product("Movie Ticket", 50, 15.00);
        
        // Display all products
        System.out.println("=== PRODUCT INVENTORY ===");
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product " + (i + 1) + ": " + products[i]);
        }
        
        // Create a customer
        Customer customer = new Customer("John Doe", 200);
        System.out.println("\n=== CUSTOMER CREATED ===");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Balance: $" + customer.getBalance());
        
        // Customer adds items to cart
        System.out.println("\n=== ADDING ITEMS TO CART ===");
        customer.addToCart(products[0], 2); // Fresh Milk
        customer.addToCart(products[2], 1); // Whole Grain Bread
        customer.addToCart(products[8], 1); // Rice 5kg
        customer.addToCart(products[13], 2); // Gift Card
        
        // View cart
        customer.viewCart();
        
        // Try to checkout
        System.out.println("\n=== ATTEMPTING CHECKOUT ===");
        boolean checkoutSuccess = customer.checkout();
        
        if (checkoutSuccess) {
            System.out.println("\n=== POST-CHECKOUT STATUS ===");
            System.out.println("Customer: " + customer.getName());
            System.out.println("Remaining balance: $" + customer.getBalance());
            System.out.println("Items in cart: " + (customer.hasItemsInCart() ? "Yes" : "No"));
        } else {
            // Add more funds and try again
            System.out.println("\n=== ADDING MORE FUNDS ===");
            customer.addFunds(100);
            customer.checkout();
        }
        
        // Show updated inventory
        System.out.println("\n=== UPDATED INVENTORY ===");
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product " + (i + 1) + ": " + products[i]);
        }
    }
}