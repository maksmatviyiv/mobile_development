package com.example.maksy.mobile_development.entity;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("title")
    private final String mTitle;
    @SerializedName("thumbnailUrl")
    private final String mThumbnailUrl;

    public Photo(String title, String thumbnailUrl) {
        mTitle = title;
        mThumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }
}
