package com.example.mtandao.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtandao.R;
import com.example.mtandao.controllers.AccountsAPI;
import com.example.mtandao.services.AccountsListener;
import com.example.mtandao.services.Loader;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AccountsListener.LoginListener {
    // views
    private EditText emailEdit, passwordEdit;
    private TextView forgotPassword;
    private Button btnRegister, btnLogin;

    private AccountsAPI accountsAPI;
    private Loader loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init views
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        forgotPassword = findViewById(R.id.forgetPassword);

        // set up listener
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        accountsAPI = new AccountsAPI(this);
        loader = new Loader(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogin)) {
            if (validate()) {
                // init strings email and password
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                accountsAPI.loginUser(email, password);
                loader.showDialog();

            }

           } else if (view.equals(btnRegister)) {
                goToRegisterActivity();
        }

    }

    private boolean validate() {
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
            return true;
    }

    private void goToRegisterActivity() {
        // go to register activity
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }


    @Override
    public void onSuccessLogin() {
        loader.hideDialog();
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onLoginFailure() {
        loader.hideDialog();
        Toast.makeText(this, "Something went wrong. Kindly Try again", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailureResponse(Exception e) {
        loader.hideDialog();
        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

    }
}