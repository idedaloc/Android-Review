package com.example.contacts.login;

import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.dtos.UserDTO;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void displayAuthenticationError();

        void continueSuccessfullyLogin(UserDTO user);

    }

    interface Presenter extends BasePresenter {

        void validateCredentials(UserDTO user);

    }
}
