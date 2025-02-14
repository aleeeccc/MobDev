package com.antonio.appdev_prac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    private List<CartItemClass> cartItems;
    private LayoutInflater inflater;

    public OrderListAdapter(Context context, List<CartItemClass> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.order_item_layout, parent, false);
        }

        ImageView itemImage = convertView.findViewById(R.id.item_image);
        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemPrice = convertView.findViewById(R.id.item_price);
        TextView itemQuantity = convertView.findViewById(R.id.item_quantity);
        TextView itemSubtotal = convertView.findViewById(R.id.item_subtotal);

        CartItemClass item = cartItems.get(position);

        itemImage.setImageResource(item.getFoodImage());
        itemName.setText(item.getFoodName());
        itemPrice.setText(String.format("$ %.2f", item.getFoodPrice()));
        itemQuantity.setText(String.format("Quantity: %d", item.getQuantity()));
        itemSubtotal.setText(String.format("$ %.2f", item.getTotalPrice()));

        return convertView;
    }
}