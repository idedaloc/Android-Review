package com.example.contacts;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
