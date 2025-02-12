package com.antonio.appdev_prac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<CartItemClass> cartItems;
    private ListView orderListView;
    private TextView totalPriceTextView, orderStatusTextView;
    private Button btnBack, btnConfirmCheckout;
    private double totalAmount = 0.0;
    private boolean orderPlaced = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        orderListView = findViewById(R.id.order_list);
        totalPriceTextView = findViewById(R.id.total_price);
        orderStatusTextView = findViewById(R.id.order_status);
        btnBack = findViewById(R.id.btn_back_checkout);
        btnConfirmCheckout = findViewById(R.id.confirm_checkout);

        cartItems = (ArrayList<CartItemClass>) getIntent().getSerializableExtra("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        updateOrderSummary();
        
        btnBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(CheckoutActivity.this, HomePageActivity.class);
            intentBack.putExtra("username", getIntent().getStringExtra("username"));

            if (!orderPlaced) {
                intentBack.putExtra("cart", cartItems); // Keep cart if not checked out
            } else {
                intentBack.putExtra("cart", new ArrayList<CartItemClass>()); // Empty cart if checked out
            }

            startActivity(intentBack);
        });

        btnConfirmCheckout.setOnClickListener(v -> {
            orderPlaced = true;
            cartItems.clear();
            orderListView.setAdapter(null); // Clear list
            totalPriceTextView.setText("Total: $0.00");
            orderStatusTextView.setText("✅ Items have been ordered!");
            btnConfirmCheckout.setEnabled(false);
        });
    }

    private void updateOrderSummary() {
        totalAmount = 0.0;
        for (CartItemClass item : cartItems) {
            totalAmount += item.getTotalPrice();
        }
        totalPriceTextView.setText(String.format("Total: $ %.2f", totalAmount));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getCartItemNames());
        orderListView.setAdapter(adapter);
    }

    private ArrayList<String> getCartItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (CartItemClass item : cartItems) {
            itemNames.add(item.getFoodName() + " x" + item.getQuantity() + " - $" + item.getTotalPrice());
        }
        return itemNames;
    }
}
