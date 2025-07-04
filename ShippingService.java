import java.util.List;

public class ShippingService {
    private static final double BASE_SHIPPING_RATE = 5.0; // Base shipping cost
    private static final double PER_KG_RATE = 2.0; // Additional cost per kg
    
    public double calculateShippingFee(List<Shippable> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = 0.0;
        for (Shippable item : shippableItems) {
            totalWeight += item.getWeight();
        }
        
        // Calculate shipping fee: base rate + weight-based rate
        double shippingFee = BASE_SHIPPING_RATE + (totalWeight * PER_KG_RATE);
        
        // Round to 2 decimal places
        return Math.round(shippingFee * 100.0) / 100.0;
    }
    
    public void processShipment(List<Shippable> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        
        System.out.println("\n=== SHIPPING SERVICE ===");
        System.out.println("Processing shipment for " + shippableItems.size() + " item(s):");
        
        double totalWeight = 0.0;
        for (int i = 0; i < shippableItems.size(); i++) {
            Shippable item = shippableItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - Weight: " + item.getWeight() + " kg");
            totalWeight += item.getWeight();
        }
        
        double shippingFee = calculateShippingFee(shippableItems);
        
        System.out.println("Total shipment weight: " + String.format("%.2f", totalWeight) + " kg");
        System.out.println("Shipping fee: $" + String.format("%.2f", shippingFee));
        System.out.println("Shipment processed successfully!");
    }
} 