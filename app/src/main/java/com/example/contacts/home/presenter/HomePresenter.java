package com.example.contacts.home.presenter;

import com.example.contacts.home.HomeContract;
import com.example.contacts.home.model.Contact;
import com.example.contacts.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomePresenter implements HomeContract.Presenter, HomeContract.Model.ModelCallbackListener {

    private HomeContract.Model mHomeModel;

    HomeContract.View homeView;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        this.mHomeModel = new HomeModel();


        homeView.setPresenter(this);
    }

    @Override
    public void getContactList(Long userId) {
        mHomeModel.getValidation(this,userId);
    }

    @Override
    public void OnSuccess(List<Contact> contacts) {
        homeView.showContactList(contacts);
    }

    @Override
    public void onFailure() {
        homeView.displayServerError();
    }



}
