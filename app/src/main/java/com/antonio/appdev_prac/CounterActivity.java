package com.antonio.appdev_prac;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CounterActivity extends AppCompatActivity {

    private int counter = 1;
    private TextView counterTextView, nameTextView, courseTextView, yearTextView, whamTextView;
    private ImageView profilePicture;
    private Button decrementButton, incrementButton, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        counterTextView = findViewById(R.id.counterValue);
        nameTextView = findViewById(R.id.nameText);
        courseTextView = findViewById(R.id.courseText);
        yearTextView = findViewById(R.id.yearText);
        whamTextView = findViewById(R.id.whamText);
        profilePicture = findViewById(R.id.profileImage);

        decrementButton = findViewById(R.id.decrementButton);
        incrementButton = findViewById(R.id.incrementButton);

        String nameIntent = getIntent().getStringExtra("name");
        String courseIntent = getIntent().getStringExtra("course");
        String yearIntent = getIntent().getStringExtra("year");
        String whamIntent = getIntent().getStringExtra("wham");


        nameTextView.setText( !isEmpty(nameIntent) ? "Name: " + nameIntent : "No Name");
        courseTextView.setText( !isEmpty(courseIntent) ? "Course: " + courseIntent : "No Course");
        yearTextView.setText( !isEmpty(yearIntent) ? "Year :" + yearIntent : "No Year");
        whamTextView.setText( !isEmpty(whamIntent) ? "WHAM: " + whamIntent : "No WHAM");
        profilePicture.setImageResource(getIntent().getIntExtra("image", 0));

        btnBack = findViewById(R.id.btnBack);

        updateCounter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCounter = new Intent( CounterActivity.this, MainActivity.class);
                startActivity(intentCounter);
            }
        });

        decrementButton.setOnClickListener(v -> {
            if(counter > 0){
                counter--;
                updateCounter();
            }
        });

        incrementButton.setOnClickListener(v -> {
            counter++;
            updateCounter();
        });
    }

    private void updateCounter() {
        counterTextView.setText(String.valueOf(counter));
    }
}