package com.example.contactmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    Contacts contacts;
    Context context;
    MyViewModel viewModel;

    public AddNewContactClickHandler(Contacts contacts, Context context, MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.viewModel = myViewModel;
    }
    public  void OnSubmitBtnCLicked(View view){
        if(contacts.getName()==null || contacts.getEmail()==null){
            Toast.makeText(context,"Field's Can't be Empty", Toast.LENGTH_SHORT).show();
        }else {
        Intent i = new Intent(context,MainActivity.class);
//        i.putExtra("Name", contacts.getName());
//        i.putExtra("Email", contacts.getEmail());
        Contacts c = new Contacts(contacts.getName(), contacts.getEmail());
        viewModel.addContacts(c);
        context.startActivity(i);
        }
    }
}
