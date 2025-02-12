package com.antonio.appdev_prac;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText nameEditText, passwordEditText;
    private TextView warningTextView;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameEditText = findViewById(R.id.loginName);
        passwordEditText = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginButton);
        warningTextView = findViewById(R.id.warning);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginActivity.this, HomePageActivity.class);
                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(password.equals("admin") && !name.isEmpty()) {
                    loginIntent.putExtra("username", name);
                    startActivity(loginIntent);
                } else {
                    warningTextView.setText("Invalid credentials! Please try again.");
                }
            }
        });

    }
}