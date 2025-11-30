package com.hsfulda.demo.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jdk.jfr.Name;

@Entity
@NamedQuery(
        name="Product.findByColor",
        query = "SELECT p FROM Product p WHERE LOWER(p.color) = LOWER(:color)"
)
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private String size;
    private String color;
    private String category;

    public Product() {}
    public Product( String name, double price, String size, String color, String category) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
