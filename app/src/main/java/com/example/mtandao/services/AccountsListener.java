package com.example.mtandao.services;

public interface AccountsListener {

    // create an interface
    interface RegistrationListener {
        // create listeners
        void onAccountCreated();
        void onRegistrationFailure();
    }
}
