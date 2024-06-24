package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contactmanagerapp.databinding.ActivityAddNewBinding;

public class AddNewActivity extends AppCompatActivity {

    private ActivityAddNewBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        contacts = new Contacts();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        handler = new AddNewContactClickHandler(contacts, this, viewModel);
        binding.setContact(contacts);
        binding.setClickHandler(handler);
    }
}