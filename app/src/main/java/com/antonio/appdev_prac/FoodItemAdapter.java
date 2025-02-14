package com.antonio.appdev_prac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class FoodItemAdapter extends BaseAdapter {
    private Context context;
    private List<CartItemClass> foodItems;

    public FoodItemAdapter(Context context, List<CartItemClass> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_item_layout, parent, false);
        }

        ImageView foodImage = convertView.findViewById(R.id.food_image);
        TextView foodName = convertView.findViewById(R.id.food_name);
        TextView foodPrice = convertView.findViewById(R.id.food_price);

        CartItemClass foodItem = foodItems.get(position);

        foodImage.setImageResource(foodItem.getFoodImage());
        foodName.setText(foodItem.getFoodName());
        foodPrice.setText(String.format("$%.2f", foodItem.getFoodPrice()));

        return convertView;
    }
}