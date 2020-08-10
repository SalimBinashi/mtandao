package com.example.mtandao.services;

public interface AccountsListener {

    // create an interface
    interface RegistrationListener {
        // create listeners
        void onAccountCreated();
        void onRegistrationFailure(Exception e);
    }
     interface LoginListener {
        // listeners
         void onSuccessLogin();
         void onLoginFailure();
         void onFailureResponse(Exception e);
     }
}
