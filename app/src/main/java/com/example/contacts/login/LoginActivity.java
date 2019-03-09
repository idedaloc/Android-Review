package com.example.contacts.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnSuccesfulLoginListener {

    private static String USER = "com.example.contacts.login.USER";
    private static String PASSWORD = "com.example.login.contacts.password";

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
    public void onSuccesfullLogin(String user, String password) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(USER,user);
        intent.putExtra(PASSWORD,password);
        startActivity(intent);
    }
}
