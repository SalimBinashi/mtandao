package com.example.mtandao.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mtandao.R;
import com.example.mtandao.controllers.AccountsAPI;
import com.example.mtandao.services.AccountsListener;
import com.example.mtandao.services.Loader;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AccountsListener, AccountsListener.RegistrationListener {
    // views
    private EditText emailEdit, passwordEdit;
    private Button btnLogin, btnRegister;
    private AccountsAPI accountsAPI;
    private Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init views
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // init accountsapi and loader class
        accountsAPI = new AccountsAPI(this);
        accountsAPI.setRegistrationListener(this);
        loader = new Loader(this);

        // set up button listener
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogin)) {
           goToLoginActivity();
        } else if (view.equals(btnRegister)) {
            if (validated()) {
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                accountsAPI.registerUser(email, password);
                loader.showDialog();
            }

        }
    }

    private boolean validated() {
        if (TextUtils.isEmpty(emailEdit.getText().toString())) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEdit.getText().toString()).matches()) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return  true;
    }

    private void goToLoginActivity() {
        // go to login activity
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    public void onAccountCreated() {
        loader.hideDialog();
        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }
    public void onRegistrationFailure() {
        loader.hideDialog();
        Toast.makeText(this, "Account creation failure", Toast.LENGTH_SHORT).show();
    }
}