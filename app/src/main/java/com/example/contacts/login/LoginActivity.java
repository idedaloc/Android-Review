package com.example.contacts.login;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.R;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.login_fragment_container) != null) {
            if(savedInstanceState != null)
                return;
        }

        LoginFragment loginFragment = LoginFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                                    .add(R.id.login_fragment_container, loginFragment)
                                    .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
