package com.example.mtandao.controllers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mtandao.services.AccountsListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AccountsAPI {
    //
    private Context context;
    // create firebase auth
    private FirebaseAuth auth;
    // create firebase database
    private FirebaseDatabase database;
    // create firebase database reference
    private DatabaseReference databaseReference;
    // call interface
    private AccountsListener.RegistrationListener registrationListener;

    // create a constructor
    public  AccountsAPI(Context context) {
        // init context
        this.context = context;
        // init auth
        auth = FirebaseAuth.getInstance();
        //init database
        database = FirebaseDatabase.getInstance();
        //init database reference and set path
        databaseReference = database.getReference("Users");

    }
    // create a register function
    public void registerUser(final String email, final String password) {
        // firebase user creation with email and password
        auth.createUserWithEmailAndPassword(email, password)
                // create a completion success and failure listener
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // check is task is successful
                        if (task.isSuccessful()) {
                            // create an instance of firebase user
                            FirebaseUser user = auth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();

                            // create hashmap instance
                            HashMap<Object, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("email", email);

                            // create a child node of the db reference
                            databaseReference.child(uid).setValue(map); // create primary key row and value
                            registrationListener.onAccountCreated();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        registrationListener.onRegistrationFailure();

                    }
                });
    }

    // create setters and getters
    public AccountsListener.RegistrationListener getRegistrationListener() {
        return registrationListener;
    }

    public void setRegistrationListener(AccountsListener.RegistrationListener registrationListener) {
        this.registrationListener = registrationListener;
    }
}
