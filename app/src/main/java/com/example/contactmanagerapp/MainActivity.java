package com.example.contactmanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Data Source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    // Adapter
    private MyAdapter myAdapter;

    // binding
    private MainActivityClickHandler handler;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing databinding objects

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handler);

        // Recylcer view
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        //Database
        contactDatabase = ContactDatabase.getDbInstance(this);

        //View Model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Inserting a contact just for testing
//        Contacts c1 = new Contacts("Om", "omkumarpal2003@gmail.com");
//        viewModel.addContacts(c1);

        //Loading the Data from the database
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();
                for (Contacts c : contacts) {

                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);

                }myAdapter.notifyDataSetChanged();
            }

        });
        //Adapter
        myAdapter = new MyAdapter(contactsArrayList);

        // Linking the recyclerView with the Adapter
        recyclerView.setAdapter(myAdapter);

        //Swipe Left to Delete Contact;
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //If you swipe the element in left it will delete;
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.removeContacts(c);

            }
        }).attachToRecyclerView(recyclerView);


    }
}