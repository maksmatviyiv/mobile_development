package com.example.maksy.mobile_development.detail;


import com.example.maksy.mobile_development.ApplicationEx;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.Model mModel;
    private String mTitle;

    public DetailPresenter() {
        this.mModel = new DetailModel();
        this.mTitle = ApplicationEx.getTitle();
    }

    @Override
    public void makeFavorite() {
        mTitle = ApplicationEx.getTitle();
        mModel.addToFavorites(mTitle);
    }

    @Override
    public void removeFavorite() {
        mTitle = ApplicationEx.getTitle();
        mModel.deleteFromFavourite(mTitle);
    }
}

