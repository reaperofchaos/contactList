package com.example.mycontactlist.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.mycontactlist.dao.ContactDao;
import com.example.mycontactlist.database.ContactRoomDatabase;
import com.example.mycontactlist.domain.Contact;
import java.util.List;

public class ContactRepository {

    private final ContactDao contactDao;
    private final LiveData<List<Contact>> contacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();
        contacts = contactDao.selectContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.insert(contact));
    }

    public void deleteAll(){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.deleteAll());
    }

    public void deleteById(int id){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> contactDao.deleteById(id));
    }

}
