package com.example.mtandao.services;

import android.app.ProgressDialog;
import android.content.Context;

public class Loader {
    private Context context;
    private ProgressDialog dialog;

    public Loader(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    public void showDialog() {
        dialog.setMessage("Please wait...");
        dialog.show();

    }

    public void hideDialog() {
        dialog.dismiss();

    }
}
