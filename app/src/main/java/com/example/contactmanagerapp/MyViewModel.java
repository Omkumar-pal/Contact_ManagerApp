package com.example.contactmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    // androidViewModel is used in place of viewmodel as it contains application context
    // instance of repository
    private Repository myRepository;
    // LiveData instance
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myRepository.getallContacts();
        return allContacts;
    }
    public void addContacts(Contacts contacts){
        myRepository.addContact(contacts);
    }
    public void removeContacts(Contacts contacts){
        myRepository.removeContact(contacts);
    }
}
