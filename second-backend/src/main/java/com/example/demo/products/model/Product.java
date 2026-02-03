package com.example.demo.products.model;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;

    private BigDecimal price;

    private String size;

    private String color;

    private String category;

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
    public Product(Long id, String name, BigDecimal price, String size, String color, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.category = category;
    }

    public Product(String name, BigDecimal price, String size, String color) {
        this(null, name, price, size, color, Category.STANDARD.name());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
