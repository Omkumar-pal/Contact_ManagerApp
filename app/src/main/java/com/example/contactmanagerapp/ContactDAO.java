package com.example.contactmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ContactDAO {

    @Insert
    void Insert(Contacts contacts);

    @Delete
    void Delete(Contacts contacts);

    @Query("SELECT * FROM conatact_name")
    LiveData<List<Contacts>> getallContacts();
}
