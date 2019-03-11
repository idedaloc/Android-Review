package com.example.contacts.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.R;
import com.example.contacts.home.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment implements HomeContract.View {

    private static final String USER = "user";

    private Long userId;
    private RecyclerView recyclerView;
    private OnItemSelectedListener mListener;

    private HomeContract.Presenter mPresenter;

    List<Contact> mContactsList = new ArrayList<>();

    public ContactFragment() {
    }

    public static ContactFragment newInstance(Long userId) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putLong(USER, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.userId = getArguments().getLong(USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //DummyContent.setSelection()
        }

        mPresenter.getContactList(this.userId);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            mListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void displayServerError() {

    }

    @Override
    public void showContactList(List<Contact> contacts) {
        this.mContactsList = contacts;
        recyclerView.setAdapter(new contactRecyclerViewAdapter(this.mContactsList, mListener));
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Contact Contact);
    }
}
