package com.example.maksy.mobile_development.favourite;

import android.database.Cursor;


import com.example.maksy.mobile_development.ApplicationEx;
import com.example.maksy.mobile_development.data.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavouriteModel implements FavouriteContract.Model {
    FavoriteDbHelper mDatabaseHelper;


    public List<String> getFavouriteList() {

        mDatabaseHelper = new FavoriteDbHelper(ApplicationEx.getContext());
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        return listData;
    }
}



