package com.example.contacts.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.home.dummy.DummyContent;
import com.example.contacts.login.LoginFragment;

public class HomeActivity extends AppCompatActivity implements ContactFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(findViewById(R.id.home_fragment_container) != null) {
            if(savedInstanceState != null)
                return;
        }

        ContactFragment contactFragment = ContactFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, contactFragment)
                .commit();
    }

    @Override
    public void onItemSelected(DummyContent.DummyItem item) {

    }
}
