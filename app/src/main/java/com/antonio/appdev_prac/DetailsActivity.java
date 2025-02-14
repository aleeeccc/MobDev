package com.antonio.appdev_prac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private CartItemClass foodItem;
    private ImageView foodImage;
    private int foodImg, counter = 1;
    private double foodPrice, priceTotal;
    private String foodName, foodDescription, foodCategory;
    private ArrayList<CartItemClass> cartItems;
    TextView foodNameTextView, foodPriceTextView, foodDescriptionTextView, counterTextView, totalTextView;
    Button btnAdd, btnMinus, btnBack, btnAddToCart, btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cartItems = (ArrayList<CartItemClass>) getIntent().getSerializableExtra("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        foodImage = findViewById(R.id.food_image);
        foodNameTextView = findViewById(R.id.food_name);
        foodPriceTextView = findViewById(R.id.food_price);
        foodDescriptionTextView = findViewById(R.id.food_description);
        totalTextView = findViewById(R.id.total_amount);

        foodItem = (CartItemClass) getIntent().getSerializableExtra("food_item");

        foodImg = foodItem.getFoodImage();
        foodName = foodItem.getFoodName();
        foodPrice = foodItem.getFoodPrice();
        foodDescription = foodItem.getFoodDescription();
        foodCategory = foodItem.getCategory();

        foodImage.setImageResource(foodImg);
        foodNameTextView.setText(foodName);
        foodPriceTextView.setText(String.format("$ %.2f", foodPrice));
        foodDescriptionTextView.setText(foodDescription);

        btnAdd = findViewById(R.id.incrementButtonCart);
        btnMinus = findViewById(R.id.decrementButtonCart);
        btnBack = findViewById(R.id.btnBackCart);
        btnAddToCart = findViewById(R.id.add_to_cart);
        btnCheckout = findViewById(R.id.checkout);

        counterTextView = findViewById(R.id.counterValueCart);

        for (CartItemClass item : cartItems) {
            if (item.getFoodName().equals(foodName)) {
                counter = item.getQuantity();
                break;
            }
        }

        updateCounter();
        updateTotal();
        updateAddToCartButton();

        btnAddToCart.setOnClickListener(v -> {
            if (counter > 0) {
                addItemToCart(cartItems);
                Toast.makeText(DetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            } else {
                removeItemFromCart(cartItems);
                Toast.makeText(DetailsActivity.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
            }
            navigateToHome();
        });

        btnCheckout.setOnClickListener(v -> {
            if (counter > 0) {
                addItemToCart(cartItems);
                Intent intentCheckout = new Intent(DetailsActivity.this, CheckoutActivity.class);
                intentCheckout.putExtra("username", getIntent().getStringExtra("username"));
                intentCheckout.putExtra("cart", cartItems);
                startActivity(intentCheckout);
            }
        });

        btnBack.setOnClickListener(v -> navigateToHome());

        btnMinus.setOnClickListener(v -> {
            if (counter > 0) {
                counter--;
                updateCounter();
                updateTotal();
                updateAddToCartButton();
            }
        });

        btnAdd.setOnClickListener(v -> {
            counter++;
            updateCounter();
            updateTotal();
            updateAddToCartButton();
        });
    }

    private void addItemToCart(ArrayList<CartItemClass> cartItems) {
        boolean itemExists = false;

        for (CartItemClass item : cartItems) {
            if (item.getFoodName().equals(foodName)) {
                item.setQuantity(counter);
                item.setTotalPrice(foodPrice * counter);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            CartItemClass newItem = new CartItemClass(foodName, foodPrice, foodDescription, counter, foodPrice * counter, foodImg, foodCategory);
            cartItems.add(newItem);
        }


    }

    private void removeItemFromCart(ArrayList<CartItemClass> cartItems) {
        cartItems.removeIf(item -> item.getFoodName().equals(foodName));
    }

    private void updateTotal() {
        priceTotal = foodPrice * counter;
        totalTextView.setText(String.format("$ %.2f", priceTotal));
    }

    private void updateCounter() {
        counterTextView.setText(String.valueOf(counter));
    }

    private void updateAddToCartButton() {
        if (counter == 0) {
            btnAddToCart.setText("Remove Item");
            btnAddToCart.setBackgroundColor(getResources().getColor(R.color.colorRemove));
        } else {
            btnAddToCart.setText("Add to Cart");
            btnAddToCart.setBackgroundColor(getResources().getColor(R.color.colorAdd));
        }
    }

    private void navigateToHome() {
        Intent intentBack = new Intent(DetailsActivity.this, HomePageActivity.class);
        intentBack.putExtra("username", getIntent().getStringExtra("username"));
        intentBack.putExtra("cart", cartItems);
        startActivity(intentBack);
    }
}
