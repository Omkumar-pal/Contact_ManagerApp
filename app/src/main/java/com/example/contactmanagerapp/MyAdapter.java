package com.example.contactmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyCustomViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating New view Holders for items in recycleview
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item, parent, false);
        return new MyCustomViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        // Called by recyleview when it needs to update and display a view at a specific position in the list or grid.
        Contacts currenContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currenContact);
    }

    @Override
    public int getItemCount() {
        // Determines the total no of items in the dataset
        if(contacts!= null){
            return contacts.size();

        }else {
            return 0;
        }
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        // Inform the associated Recyleview that the underlying dataset has changed, and the recylerview should refreshed
        // those changes in the recyleview.
        notifyDataSetChanged();

    }

    public class MyCustomViewHolder extends RecyclerView.ViewHolder{
        ContactListItemBinding contactListItemBinding;

        public MyCustomViewHolder( ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
