package com.example.contacts.home;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.home.ContactFragment.OnItemSelectedListener;
import com.example.contacts.home.model.Contact;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mContacts;
    private final OnItemSelectedListener mListener;

    public ContactRecyclerViewAdapter(List<Contact> contacts, OnItemSelectedListener listener) {
        mContacts = contacts;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContact = mContacts.get(position);
        holder.mContactIdView.setText(String.valueOf(mContacts.get(position).getContactId()));
        holder.mContactNameView.setText(mContacts.get(position).getContactName());
        holder.mContactPhoneView.setText(String.valueOf(mContacts.get(position).getPhone()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemSelected(holder.mContact);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CardView cardView;
        public final TextView mContactIdView;
        public final TextView mContactNameView;
        public final TextView mContactPhoneView;

        public Contact mContact;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            mContactIdView = (TextView) view.findViewById(R.id.textView_contactId);
            mContactNameView = (TextView) view.findViewById(R.id.textView_contactName);
            mContactPhoneView = (TextView) view.findViewById(R.id.textView_phone);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContactIdView.getText() + "'";
        }

    }

    public void updateUponDataChanged(List<Contact> contacts){
        mContacts.clear();
        mContacts.addAll(contacts);
        notifyDataSetChanged();
    }


}
