package com.example.mtandao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    // views
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init views
        btnLogin = findViewById(R.id.btnLogin);

        // set up button listener
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogin)) {
            // go to login activity
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }
}