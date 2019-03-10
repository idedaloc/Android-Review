package com.example.contacts.home;

import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.dtos.ContactDTO;
import com.example.contacts.login.LoginContract;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter>{
        void displayServerError();
        void showContactList(List<ContactDTO> contacts);
    }
    interface Presenter extends BasePresenter{
        void getContactList(Long userId);
    }
}
