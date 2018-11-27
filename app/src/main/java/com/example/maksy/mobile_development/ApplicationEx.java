package com.example.maksy.mobile_development;

import android.app.Application;
import android.content.Context;

public class ApplicationEx extends Application {
    public static Context mContext;

    public static String mTitle;
    public static String mThumbnailUrl;

    public static Context getContext() {
        return mContext;
    }

    public static String getTitle() {
        return mTitle;
    }

    public static void setTitle(String mTitle) {
        ApplicationEx.mTitle = mTitle;
    }

    public static String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public static void setThumbnailUrl(String mThumbnailUrl) {
        ApplicationEx.mThumbnailUrl = mThumbnailUrl;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

}
