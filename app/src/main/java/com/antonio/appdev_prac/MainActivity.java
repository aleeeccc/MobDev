package com.antonio.appdev_prac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private TextView nameTextView, courseTextView, yearTextView, whamTextView;
    private ImageView profileImageView;
    private Button btnSave, btnNext, btnBrowse;
    private EditText nameEditText, courseEditText, yearEditText, whamEditText;
    private int fileLocation;
    private String editName, editCourse, editYear, editWHAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = findViewById(R.id.profileName);
        courseTextView = findViewById(R.id.profileCourse);
        yearTextView = findViewById(R.id.profileYear);
        whamTextView = findViewById(R.id.profileWHAM);

        profileImageView = findViewById(R.id.profilePic);

        btnSave = findViewById(R.id.save_profile_button);
        btnNext = findViewById(R.id.nextButton);
        btnBrowse = findViewById(R.id.browseButton);

        nameEditText = findViewById(R.id.edName);
        courseEditText = findViewById(R.id.edCourse);
        yearEditText = findViewById(R.id.edYear);
        whamEditText = findViewById(R.id.edWHAM);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName = nameEditText.getText().toString();
                editCourse = courseEditText.getText().toString();
                editYear = yearEditText.getText().toString();
                editWHAM = whamEditText.getText().toString();

                nameTextView.setText(editName);
                courseTextView.setText(editCourse);
                yearTextView.setText(editYear);
                whamTextView.setText(editWHAM);

                fileLocation = R.drawable.john_doe;
                profileImageView.setImageResource(fileLocation);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCounter = new Intent( MainActivity.this, CounterActivity.class);
                intentCounter.putExtra("name", editName);
                intentCounter.putExtra("course", editCourse);
                intentCounter.putExtra("year", editYear);
                intentCounter.putExtra("wham", editWHAM);
                intentCounter.putExtra("image", fileLocation);
                startActivity(intentCounter);
            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com"));
                startActivity(i);
            }
        });

    }
}