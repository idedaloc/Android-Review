package com.example.contacts.login.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/login/validation")
    Call<User> validateLoginCredentials(@Body User user);

}
