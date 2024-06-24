package com.example.contactmanagerapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void OnFabClicked(View view){
        Intent i = new Intent(view.getContext(), AddNewActivity.class);
        context.startActivity(i);
    }
}
