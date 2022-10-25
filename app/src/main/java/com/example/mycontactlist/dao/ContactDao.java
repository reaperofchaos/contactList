package com.example.mycontactlist.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.mycontactlist.domain.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    /**
     * Adds a contact
     * @param contact
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    /**
     * Deletes everything in  the contact table
     */
    @Query("DELETE FROM contact_table")
    void deleteAll();

    /**
     * Deletes one contact record in  the contact table
     * @param id, contact id in db
     */
    @Query("DELETE FROM contact_table WHERE id=:id")
    void deleteById(int id);

    /**
     * Selects all contacts in the contact table
     * @return a list of contacts
     */
    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> selectContacts();
}
