public class Product implements Shippable {
    private String name ;
    private int quantity ;
    private double price;
    private boolean hasExpireDate;
    private String expireDate; // (yyyy-MM-dd)
    private boolean isshippable ;
    private double weight ;
    
    Product(String name, int quantity, double price, String expireDate, float weight){
        // Validate constructor parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        
        this.hasExpireDate = this.isshippable = true ;
        this.name = name.trim();
        this.quantity = quantity ;
        this.price = price;
        this.expireDate = expireDate ;
        this.weight = weight ;
    }

    Product(String name, int quantity, double price, String expireDate) {
        // Validate constructor parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.hasExpireDate = true ;
        this.isshippable = false;
        this.name = name.trim();
        this.quantity = quantity ;
        this.price = price;
        this.expireDate = expireDate ;
    }

    Product(String name, int quantity, double price, float weight) {
        // Validate constructor parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        
        this.isshippable = true;
        this.hasExpireDate = false ;
        this.name = name.trim();
        this.quantity = quantity ;
        this.price = price;
        this.weight = weight;
    }

    Product(String name, int quantity, double price) {
        // Validate constructor parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.hasExpireDate = this.isshippable = false;
        this.name = name.trim();
        this.quantity = quantity ;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHasExpireDate() {
        return hasExpireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public boolean isIsshippable() {
        return isshippable;
    }

    public double getWeight() {
        return weight;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setHasExpireDate(boolean hasExpireDate) {
        this.hasExpireDate = hasExpireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setIsshippable(boolean isshippable) {
        this.isshippable = isshippable;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public void addToStock(int quantityToAdd) {
        if (quantityToAdd < 0) {
            throw new IllegalArgumentException("Cannot add negative quantity to stock");
        }
        this.quantity += quantityToAdd;
    }

    public boolean isExpired() {
        if (!hasExpireDate || expireDate == null || expireDate.isEmpty()) {
            return false; // Products without expiration dates are never expired
        }
        
        try {
            // Parse the expire date
            java.time.LocalDate expireLocalDate = java.time.LocalDate.parse(expireDate);
            java.time.LocalDate currentDate = java.time.LocalDate.now();
            return currentDate.isAfter(expireLocalDate);
        } catch (Exception e) {
            // If date parsing fails, assume not expired
            System.out.println("Warning: Could not parse expire date: " + expireDate);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", hasExpireDate=" + hasExpireDate +
                ", expireDate='" + expireDate + '\'' +
                ", isshippable=" + isshippable +
                ", weight=" + weight +
                '}';
    }
}
