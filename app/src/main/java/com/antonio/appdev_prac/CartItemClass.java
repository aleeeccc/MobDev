package com.antonio.appdev_prac;

import java.io.Serializable;

public class CartItemClass implements Serializable {
    private String foodName;
    private double foodPrice;
    private int quantity;
    private double totalPrice;
    private int foodImage;

    public CartItemClass(String foodName, double foodPrice, int quantity, double totalPrice, int foodImage) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.foodImage = foodImage;
    }

    public String getFoodName() { return foodName; }
    public double getFoodPrice() { return foodPrice; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public int getFoodImage() { return foodImage; }

    public void setQuantity(int counter) { this.quantity = counter; }
    public void setTotalPrice(double v) { this.totalPrice = v; }
}
