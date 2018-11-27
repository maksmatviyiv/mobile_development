package com.example.maksy.mobile_development.network;

import com.example.maksy.mobile_development.entity.Photo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/photos")
    Call<List<Photo>> getPhotos();
}
