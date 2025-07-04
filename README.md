# Fawry-Rise-Journey

## Overview
Fawry-Rise-Journey is a Java-based e-commerce inventory management system that demonstrates object-oriented programming concepts including inheritance, interfaces, and polymorphism. The system manages products with different characteristics (shippable/non-shippable, perishable/non-perishable) and provides shopping cart functionality with checkout capabilities.

## Features

### Product Management
- **Flexible Product Types**: Supports four different product categories:
  - Products with expiry dates and shipping (e.g., fresh food items)
  - Products with expiry dates but not shippable (e.g., perishable items)
  - Products that are shippable but have no expiry date (e.g., non-perishable goods)
  - Products with no expiry date and not shippable (e.g., digital services, gift cards)

### Shopping Cart System
- Add products to cart with quantity tracking
- Real-time inventory management
- Automatic stock updates during checkout
- Balance validation for customer purchases

### Customer Management
- Customer account creation with balance tracking
- Add funds to customer account
- View cart contents and total cost
- Secure checkout process

### Shipping Integration
- Implements `Shippable` interface for products that can be shipped
- Weight tracking for shipping calculations
- Integration with `ShippingService` for delivery management

## Project Structure

```
Fawry-Rise-Journey/
├── Main.java              # Main application entry point with demo
├── Product.java           # Product class with multiple constructors
├── Customer.java          # Customer management and cart operations
├── Cart.java             # Shopping cart implementation
├── Shippable.java        # Interface for shippable products
├── ShippingService.java  # Shipping service implementation
└── README.md             # This file
```

## How to Run

1. **Compile the Java files:**
   ```bash
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java Main
   ```

## Demo Features

The main application demonstrates:
- Creating 15 different products with various characteristics
- Customer account creation and management
- Adding items to shopping cart
- Checkout process with balance validation
- Inventory updates after successful purchases
- Handling insufficient funds scenarios

## Technical Highlights

- **Object-Oriented Design**: Proper use of classes, interfaces, and inheritance
- **Data Validation**: Comprehensive input validation for all product attributes
- **Date Handling**: Expiry date management using Java's LocalDate
- **Exception Handling**: Robust error handling for invalid inputs
- **Polymorphism**: Interface implementation for shippable products

## Product Categories Examples

1. **Fresh Food Items**: Milk, yogurt, bread, eggs, bananas (expiry + shipping)
2. **Perishable Items**: Tomatoes, lettuce, strawberries (expiry only)
3. **Non-Perishable Goods**: Rice, pasta, olive oil, canned beans (shipping only)
4. **Digital Services**: Gift cards, movie tickets (neither expiry nor shipping)

This project serves as an excellent example of Java programming best practices and demonstrates a complete e-commerce system implementation. 

