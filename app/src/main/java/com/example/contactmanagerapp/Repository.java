package com.example.contactmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    // The Availabel Data Source
    // - ROOM dataBase
    private final ContactDAO contactDAO;
    ExecutorService executorService;
    Handler handler;

    public Repository(Application application) {

        ContactDatabase contactDatabase = ContactDatabase.getDbInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        // Used for background Database Operation
        executorService = Executors.newSingleThreadExecutor();
        // Used for updating the UI
        handler = new android.os.Handler(Looper.getMainLooper());
    }
    // Methods in DAO is executed in Repository
    public void addContact(Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.Insert(contacts);
            }
        });

    }

    public void removeContact(Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.Delete(contacts);
            }
        });

    }
    public LiveData<List<Contacts>> getallContacts(){
         return contactDAO.getallContacts();

    }
}
