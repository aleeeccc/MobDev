package com.antonio.appdev_prac;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] courses;
    private final String[] topics;
    private final Integer[] imageID;

    public MyListAdapter(Activity context, String[] courses, String[] topics, Integer[] imageID) {
        super(context, R.layout.mylistlayout, courses);
        this.context = context;
        this.courses = courses;
        this.topics = topics;
        this.imageID = imageID;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylistlayout, null, true);

        ImageView img = (ImageView) rowView.findViewById(R.id.imageView);
        TextView textHeader = (TextView) rowView.findViewById(R.id.headertext);
        TextView textSubtext = (TextView) rowView.findViewById(R.id.subtext);

        textHeader.setText(courses[position%6]);
        textSubtext.setText(topics[position%6]);
        img.setImageResource(imageID[position%6]);

        return rowView;
    }
}
