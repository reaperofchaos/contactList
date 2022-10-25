package com.example.mycontactlist.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import com.example.mycontactlist.domain.Contact;
import com.example.mycontactlist.repository.ContactRepository;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;
    private final LiveData<List<Contact>> contacts;

    public ContactViewModel (Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        contacts = contactRepository.getContacts();
    }

    public LiveData<List<Contact>> getAllContacts() { return contacts; }

    public void insert(Contact contact) { contactRepository.insert(contact); }
    public void deleteAll() { contactRepository.deleteAll(); }
    public void deleteById(int id) { contactRepository.deleteById(id); }

}
