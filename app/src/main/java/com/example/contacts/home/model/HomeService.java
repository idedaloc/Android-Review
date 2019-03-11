package com.example.contacts.home.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {
    @GET("contacts/{userId}")
    Call<List<Contact>> getAllContactsByUserIdCall(@Path("userId") Long userId);
}
