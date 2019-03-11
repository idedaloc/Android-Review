package com.example.contacts.home.model;

import com.example.contacts.login.LoginContract;
import com.example.contacts.utils.network.ApliClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements com.example.contacts.home.HomeContract.Model {

    @Override
    public void getValidation(final ModelCallbackListener listener, Long userId) {

        HomeService homeService = ApliClient.getClient().create(HomeService.class);

        Call<List<Contact>> contactsCall = homeService.getAllContactsByUserIdCall(userId);

        contactsCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                listener.OnSuccess(contacts);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
