package com.example.mycontactlist.views;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.room.Room;
import com.example.mycontactlist.dao.ContactDao;
import com.example.mycontactlist.database.ContactRoomDatabase;
import com.example.mycontactlist.domain.Contact;
import java.util.ArrayList;


public class ContactListAdapter extends ListAdapter<Contact, ContactViewHolder> {

    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContactViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact current = getItem(position);
        holder.bindName(current.getName());
        holder.bindAddresss(current.getAddress());
        holder.bindCity(current.getCity() + ", " + current.getState() + " " + current.getZipCode());
        holder.deleteBtn.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
                //remove from DB
                ContactRoomDatabase db =  Room.databaseBuilder(holder.itemView.getContext(),
                        ContactRoomDatabase.class, "contact_database").allowMainThreadQueries().build();
                ContactDao dao = db.contactDao();
                dao.deleteById(current.getId());

                // To update the Recycler view:
                // Get the list from the adapter, remove the contact and submit list to the adapter again
                ArrayList<Contact> contactList = new ArrayList<>(getCurrentList());
                contactList.remove(holder.getAdapterPosition());
                submitList(contactList);
            }
        }));
    }


    public static class ContactDiff extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getContact().equals(newItem.getContact());
        }
    }
}
