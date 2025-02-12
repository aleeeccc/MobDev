package com.antonio.appdev_prac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private TextView titleTextView;
    private String usernameIntent;
    private LinearLayout food1Layout, food2Layout, food3Layout, food4Layout, food5Layout, food6Layout, checkoutContainer;
    private ArrayList<CartItemClass> cartItems;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        titleTextView = findViewById(R.id.welcomeHeader);
        usernameIntent = getIntent().getStringExtra("username");

        cartItems = (ArrayList<CartItemClass>) getIntent().getSerializableExtra("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        titleTextView.setText("Hello, " + (usernameIntent.isEmpty() ? "User" : usernameIntent));

        food1Layout = findViewById(R.id.food1);
        food2Layout = findViewById(R.id.food2);
        food3Layout = findViewById(R.id.food3);
        food4Layout = findViewById(R.id.food4);
        food5Layout = findViewById(R.id.food5);
        food6Layout = findViewById(R.id.food6);
        checkoutContainer = findViewById(R.id.checkout_container);

        btnCheckout = findViewById(R.id.checkout_button);

        food1Layout.setOnClickListener(view -> openFoodDetails("Masala Curry", 25.00, R.drawable.food1, "A delicious Indian curry with fresh spices."));
        food2Layout.setOnClickListener(view -> openFoodDetails("Hot Egg Masala", 30.00, R.drawable.food2, "A spicy egg dish with rich gravy."));
        food3Layout.setOnClickListener(view -> openFoodDetails("Pasta Alfredo", 20.00, R.drawable.food3, "A creamy and cheesy pasta dish made with rich Alfredo sauce."));
        food4Layout.setOnClickListener(view -> openFoodDetails("Tandoori Chicken", 35.00, R.drawable.food4, "A flavorful, smoky, and spicy grilled chicken marinated in yogurt and spices."));
        food5Layout.setOnClickListener(view -> openFoodDetails("Veggie Pizza", 28.00, R.drawable.food5, "A crispy crust topped with fresh vegetables, melted cheese, and rich tomato sauce."));
        food6Layout.setOnClickListener(view -> openFoodDetails("BBQ Ribs", 40.00, R.drawable.food6, "Tender, smoky ribs glazed with a rich and tangy barbecue sauce."));

        if (!cartItems.isEmpty()) {
            checkoutContainer.setVisibility(View.VISIBLE);
        } else {
            checkoutContainer.setVisibility(View.GONE);
        }

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCheckout = new Intent(HomePageActivity.this, CheckoutActivity.class);
                intentCheckout.putExtra("username", getIntent().getStringExtra("username"));
                intentCheckout.putExtra("cart", cartItems);
                startActivity(intentCheckout);
            }
        });
    }
    private void openFoodDetails(String name, double price, int imageRes, String description) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("food_name", name);
        intent.putExtra("food_price", price);
        intent.putExtra("food_image", imageRes);
        intent.putExtra("food_description", description);
        intent.putExtra("username", usernameIntent);
        intent.putExtra("cart", cartItems);
        startActivity(intent);
    }
}