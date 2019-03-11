package com.example.contacts.login.presenter;

import com.example.contacts.dtos.UserDTO;
import com.example.contacts.login.LoginContract;
import com.example.contacts.login.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.ModelCallbackListener {

    private LoginContract.View mLoginView;
    private LoginContract.Model mLoginModel;

    public LoginPresenter(LoginContract.View loginLoginView) {
        this.mLoginView = loginLoginView;
        this.mLoginModel = new LoginModel();

        loginLoginView.setPresenter(this);
    }

    @Override
    public void validateCredentials(UserDTO user) {
        mLoginModel.getValidation(this, user);
    }

    @Override
    public void OnSuccess(UserDTO user) {
        if(user.isAuthorized())
            mLoginView.continueSuccessfullyLogin(user);
        else
            mLoginView.displayAuthenticationError();
    }

    @Override
    public void onFailure() {
        mLoginView.displayServerError();
    }


}

