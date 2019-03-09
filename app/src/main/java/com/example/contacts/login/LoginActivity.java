package com.example.contacts.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.dtos.UserDTO;
import com.example.contacts.home.HomeActivity;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnSuccessfullyLoginListener {

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
    public void onSuccessfullyLogin(UserDTO userDTO) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("t", userDTO);
        startActivity(intent);
        finish();
    }
}
