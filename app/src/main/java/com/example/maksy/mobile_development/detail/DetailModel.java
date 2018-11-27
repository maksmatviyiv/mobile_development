package com.example.maksy.mobile_development.detail;


import com.example.maksy.mobile_development.ApplicationEx;
import com.example.maksy.mobile_development.data.FavoriteDbHelper;

public class DetailModel implements DetailContract.Model {
    FavoriteDbHelper mDatabaseHelper;

    public DetailModel() {
        this.mDatabaseHelper = new FavoriteDbHelper(ApplicationEx.getContext());
    }

    public boolean addToFavorites(String title) {
        mDatabaseHelper.addToFavorites(ApplicationEx.getTitle());
        return true;
    }

    public void deleteFromFavourite(String title) {
        mDatabaseHelper.deleteFromFavourite(ApplicationEx.getTitle());
    }

}
