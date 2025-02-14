package com.antonio.appdev_prac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private TextView titleTextView;
    private String usernameIntent;
    private LinearLayout checkoutContainer;
    private ArrayList<CartItemClass> cartItems;
    private Button btnCheckout;
    private Spinner categorySpinner;
    private CartItemClass[] foodItems = {
            new CartItemClass("Masala Curry", 25.00, "A spicy and flavorful Indian curry made with a blend of aromatic spices.", 0, 0.00, R.drawable.food1, "Indian"),
            new CartItemClass("Hot Egg Masala", 30.00, "A rich and spicy Indian dish made with boiled eggs cooked in a tomato-based gravy.", 0, 0.00, R.drawable.food2, "Indian"),
            new CartItemClass("Pasta Alfredo", 20.00, "A creamy Italian pasta dish made with fettuccine tossed in a rich Alfredo sauce.", 0, 0.00, R.drawable.food3, "Italian"),
            new CartItemClass("Tandoori Chicken", 35.00, "A popular Indian dish made with chicken marinated in yogurt and spices, then grilled to perfection.", 0, 0.00, R.drawable.food4, "Indian"),
            new CartItemClass("Veggie Pizza", 28.00, "A delicious Italian pizza topped with a variety of fresh vegetables and melted cheese.", 0, 0.00, R.drawable.food5, "Italian"),
            new CartItemClass("BBQ Ribs", 40.00, "Tender and juicy American-style ribs coated in a smoky barbecue sauce.", 0, 0.00, R.drawable.food6, "American")
    };
    private String[] categories = {"All", "Indian", "Italian", "American"};
    private FoodItemAdapter foodItemAdapter;
    private GridLayout foodGridLayout;

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

        checkoutContainer = findViewById(R.id.checkout_container);
        categorySpinner = findViewById(R.id.category_spinner);

        btnCheckout = findViewById(R.id.checkout_button);

        foodGridLayout = findViewById(R.id.food_container);

        if (!cartItems.isEmpty()) {
            checkoutContainer.setVisibility(View.VISIBLE);
        } else {
            checkoutContainer.setVisibility(View.GONE);
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        foodItemAdapter = new FoodItemAdapter(this, cartItems);
        for (int i = 0; i < foodItemAdapter.getCount(); i++) {
            final int position = i;
            View item = foodItemAdapter.getView(position, null, foodGridLayout);
            item.setOnClickListener(view -> openFoodDetails((CartItemClass) foodItemAdapter.getItem(position)));
            foodGridLayout.addView(item);
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

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                filterFoodItemsByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
    private void openFoodDetails(CartItemClass foodItem) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("food_item", foodItem);
        intent.putExtra("food_description", "Description for " + foodItem.getFoodName());
        intent.putExtra("username", usernameIntent);
        intent.putExtra("cart", cartItems);
        startActivity(intent);
    }

    private void filterFoodItemsByCategory(String category) {
        foodGridLayout.removeAllViews();
        ArrayList<CartItemClass> filteredItems = new ArrayList<>();
        for (CartItemClass item : foodItems) {
            if (category.equals("All") || item.getCategory().equals(category)) {
                filteredItems.add(item);
            }
        }

        foodItemAdapter = new FoodItemAdapter(this, filteredItems);
        for (int i = 0; i < foodItemAdapter.getCount(); i++) {
            final int position = i;
            View item = foodItemAdapter.getView(position, null, foodGridLayout);
            item.setOnClickListener(view -> openFoodDetails((CartItemClass) foodItemAdapter.getItem(position)));
            foodGridLayout.addView(item);
        }
    }
}