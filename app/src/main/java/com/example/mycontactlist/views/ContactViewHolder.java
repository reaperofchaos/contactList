package com.example.mycontactlist.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mycontactlist.R;


class ContactViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView address;
    private final TextView city;
    public final Button deleteBtn;

    private ContactViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txtName);
        address = itemView.findViewById(R.id.txtAddress);
        city = itemView.findViewById(R.id.txtAddress2);
        deleteBtn = itemView.findViewById(R.id.btnDelete);
    }

    public void bindName(String text) {
        name.setText(text);
    }

    public void bindAddresss(String text) {
        address.setText(text);
    }

    public void bindCity(String text) {
        city.setText(text);
    }



    static ContactViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(view);
    }

}
