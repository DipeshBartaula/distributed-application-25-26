package com.hsfulda.demo.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jdk.jfr.Name;
import java.math.BigDecimal;

/**
 * Entity representing a product in the e-commerce catalog.
 * 
 * This entity models the core attributes of a product that can be displayed in
 * the catalog
 * and purchased through the application. Each product has a unique identifier
 * and contains
 * pricing, categorization, and sizing information.
 */
@Entity
@NamedQuery(name = "Product.findByColor", query = "SELECT p FROM Product p WHERE LOWER(p.color) = LOWER(:color)")
public class Product {
    /**
     * Unique identifier for the product. Auto-generated upon persistence.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name or title of the product (e.g., "Running Shoes", "Cotton T-Shirt").
     */
    private String name;

    /**
     * The price of the product in Euros. Represents the cost to the customer.
     * Uses BigDecimal for exact monetary calculations without floating-point
     * rounding errors.
     */
    private BigDecimal price;

    /**
     * The size dimension of the product (e.g., "M", "L", "42", "Large").
     * Used for clothing, footwear, and similarly sized items.
     */
    private String size;

    /**
     * The color of the product (e.g., "Red", "Blue", "Black").
     * Used for filtering and product differentiation.
     */
    private String color;

    /**
     * The product category or type (e.g., "Clothing", "Footwear", "Accessories").
     * Organizes products into logical groupings for browsing and navigation.
     */
    private String category;

    /**
     * Default constructor for JPA entity creation.
     */
    public Product() {
        this.category = Category.STANDARD.name();
    }

    /**
     * Constructor to create a new Product with all required attributes.
     * 
     * @param name     the product name
     * @param price    the product price in Euros as a BigDecimal
     * @param size     the product size
     * @param color    the product color
     * @param category the product category
     */
    public Product(String name, BigDecimal price, String size, String color, String category) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.category = category;
    }

    public Product(String name, BigDecimal price, String size, String color) {
        this(name, price, size, color, Category.STANDARD.name());
    }

    /**
     * Returns the unique product identifier.
     * 
     * @return the product ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product identifier.
     * 
     * @param id the product ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * 
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price in Euros.
     * 
     * @return the price as a BigDecimal
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price in Euros.
     * 
     * @param price the price to set as a BigDecimal
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the product size.
     * 
     * @return the product size
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the product size.
     * 
     * @param size the product size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Returns the product color.
     * 
     * @return the product color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the product color.
     * 
     * @param color the product color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the product category.
     * 
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     * 
     * @param category the product category
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
