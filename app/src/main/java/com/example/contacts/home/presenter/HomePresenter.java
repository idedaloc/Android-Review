package com.example.contacts.home.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePresenter {


    private final List<HomePresenter.DummyItem> ITEMS = new ArrayList<HomePresenter.DummyItem>();

    private final Map<String, HomePresenter.DummyItem> ITEM_MAP = new HashMap<String, HomePresenter.DummyItem>();

    private static final int COUNT = 25;

    private Long userId;

    public List<HomePresenter.DummyItem> getList(){
        return ITEMS;
    }
    public HomePresenter(Long userId) {
        if(userId == 1) {

            for (int i = 1; i <= COUNT / 2; i++) {
                addItem(createDummyItem(i));
            }
        }else if(userId == 2){
            for (int i = 1; COUNT / 2 <= COUNT; i++) {
                addItem(createDummyItem(i));
            }
        }
    }

    private void addItem(HomePresenter.DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static HomePresenter.DummyItem createDummyItem(int position) {
        return new HomePresenter.DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
