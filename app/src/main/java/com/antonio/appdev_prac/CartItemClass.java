package com.antonio.appdev_prac;

import java.io.Serializable;

public class CartItemClass implements Serializable {
    private String foodName;
    private double foodPrice;
    private String foodDescription;
    private int quantity;
    private double totalPrice;
    private int foodImage;
    private String category;

    public CartItemClass(String foodName, double foodPrice, String foodDescription, int quantity, double totalPrice, int foodImage, String category) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescription = foodDescription;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.foodImage = foodImage;
        this.category = category;
    }

    public String getFoodName() { return foodName; }
    public double getFoodPrice() { return foodPrice; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public int getFoodImage() { return foodImage; }
    public String getFoodDescription() { return foodDescription; }
    public String getCategory() { return category; }

    public void setQuantity(int counter) { this.quantity = counter; }
    public void setTotalPrice(double v) { this.totalPrice = v; }
}
