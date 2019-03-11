package com.example.contacts.home;

import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.home.model.Contact;
import com.example.contacts.login.LoginContract;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter>{

        void displayServerError();

        void showContactList(List<Contact> contacts);

    }

    interface Presenter extends BasePresenter{

        void getContactList(Long userId);

    }

    interface Model{

        interface ModelCallbackListener{

            void OnSuccess(List<Contact> contacts);

            void onFailure();

        }

        void getValidation(HomeContract.Model.ModelCallbackListener listener, Long userId);
    }
}
