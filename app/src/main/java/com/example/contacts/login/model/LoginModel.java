package com.example.contacts.login.model;

import android.os.AsyncTask;

import com.example.contacts.dtos.UserDTO;
import com.example.contacts.login.LoginContract;
import com.example.contacts.utils.network.ApliClient;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    @Override
    public void getValidation(final ModelCallbackListener listener, UserDTO user) {

        User userModel = new User();

        userModel.setUserId(user.getUserId());
        userModel.setUserName(user.getUserName());
        userModel.setPassword(user.getPassword());

        final UserDTO verifiedUser = new UserDTO();

        LoginService loginService = ApliClient.getClient().create(LoginService.class);

        Call<User> call = loginService.postValidateLoginCredentials(userModel);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                verifiedUser.setUserId(response.body().getUserId());
                verifiedUser.setUserName(response.body().getUserName());
                verifiedUser.setAuthorized(response.body().isAuthorized());
                listener.OnSuccess(verifiedUser);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });

    }

}
