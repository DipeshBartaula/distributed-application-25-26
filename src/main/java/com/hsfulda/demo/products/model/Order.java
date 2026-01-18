package com.hsfulda.demo.products.model;

public class Order {

    private Double totalPrice;
    private Long userId;

    public Order(Double totalPrice, Long userId) {
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
