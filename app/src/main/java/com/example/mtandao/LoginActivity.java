package com.example.mtandao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // views
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init views
        btnRegister = findViewById(R.id.btnRegister);

        // set up listener
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnRegister)) {
            // go to register activity
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }

    }
}