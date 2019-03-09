package com.example.contacts.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.dtos.UserDTO;
import com.example.contacts.home.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements ContactFragment.OnItemSelectedListener {

    private UserDTO userDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(findViewById(R.id.home_fragment_container) != null) {
            if(savedInstanceState != null)
                return;
        }

        userDTO = (UserDTO) getIntent().getExtras().getParcelable("t");

        ContactFragment contactFragment = ContactFragment.newInstance(userDTO.getUserId());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, contactFragment)
                .commit();
    }

    @Override
    public void onItemSelected(HomePresenter.DummyItem item) {

    }
}
