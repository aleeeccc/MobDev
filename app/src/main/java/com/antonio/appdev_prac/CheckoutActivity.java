package com.antonio.appdev_prac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<CartItemClass> cartItems;
    private ListView orderListView;
    private TextView totalPriceTextView, orderStatusTextView;
    private Button btnBack, btnConfirmCheckout;
    private double totalAmount = 0.0;
    private boolean orderPlaced = false;
    private OrderListAdapter orderListAdapter;

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

        orderListAdapter = new OrderListAdapter(this, cartItems);
        orderListView.setAdapter(orderListAdapter);

        updateOrderSummary();

        btnBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(CheckoutActivity.this, HomePageActivity.class);
            intentBack.putExtra("username", getIntent().getStringExtra("username"));

            if (!orderPlaced) {
                intentBack.putExtra("cart", cartItems);
            } else {
                intentBack.putExtra("cart", new ArrayList<CartItemClass>());
            }

            startActivity(intentBack);
        });

        btnConfirmCheckout.setOnClickListener(v -> {
            orderPlaced = true;
            cartItems.clear();
            orderListAdapter.notifyDataSetChanged();
            totalPriceTextView.setText("Total: $0.00");
            Toast.makeText(this, "✅ Items have been ordered!", Toast.LENGTH_SHORT).show();
            btnConfirmCheckout.setEnabled(false);
        });
    }

    private void updateOrderSummary() {
        totalAmount = 0.0;
        for (CartItemClass item : cartItems) {
            totalAmount += item.getTotalPrice();
        }
        totalPriceTextView.setText(String.format("Total: $ %.2f", totalAmount));
        orderListAdapter.notifyDataSetChanged();
    }
}