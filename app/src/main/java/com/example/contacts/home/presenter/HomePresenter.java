package com.example.contacts.home.presenter;

import com.example.contacts.dtos.ContactDTO;
import com.example.contacts.home.HomeContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.View homeView;

    private final List<ContactDTO> ITEMS = new ArrayList<>();

    private final Map<Integer, ContactDTO> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    private Long userId;

    public List<ContactDTO> getList(Long userId){
        buildList(userId);
        return ITEMS;
    }
    public void buildList(Long userId) {
        if(userId == 1) {

            for (int i = 1; i <= COUNT / 2; i++) {
                addItem(createDummyItem(i));
            }
        }else if(userId == 2){
            for (int i = COUNT / 2; i < COUNT; i++) {
                addItem(createDummyItem(i));
                System.out.println(i);
            }
        }
    }

    private void addItem(ContactDTO item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getContactId(), item);
    }

    private static ContactDTO createDummyItem(int position) {
        return new ContactDTO(position, "Item " + position, 1234l);
    }


    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;

        homeView.setPresenter(this);
    }

    @Override
    public void getContactList(Long userId) {
        homeView.showContactList(getList(userId));
    }

}
