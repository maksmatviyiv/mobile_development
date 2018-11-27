package com.example.maksy.mobile_development.detail;

public interface DetailContract {

    interface Presenter {
        void makeFavorite();

        void removeFavorite();
    }

    interface Model {
        boolean addToFavorites(String title);

        void deleteFromFavourite(String title);
    }
}
