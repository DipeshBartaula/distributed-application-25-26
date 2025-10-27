package com.hsfulda.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private String size;
    private String color;

    public Product() {}
    public Product(int id, String name, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    public static List<Product> productList = new ArrayList<>();
    static {
        productList.addAll(List.of(
                new Product(1, "T-Shirt", 19.99, "M", "Blue"),
                new Product(2, "Jeans", 49.99, "32", "Black"),
                new Product(3, "Sneakers", 79.99, "42", "White"),
                new Product(4, "Jacket", 99.99, "L", "Red"),
                new Product(5, "Cap", 14.99, "Free Size", "Green")
        ));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public static List<Product> getProductList() {
        return productList;
    }

}
