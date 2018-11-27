package com.example.maksy.mobile_development.list;

import android.support.annotation.NonNull;


import com.example.maksy.mobile_development.entity.Photo;
import com.example.maksy.mobile_development.network.Api;
import com.example.maksy.mobile_development.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel implements ListContract.Model {
    private List<Photo> mDataList;

    @Override
    public List<Photo> callRetrofit() {
        Api api = ApiClient.getRetrofitInstance().create(Api.class);
        Call<List<Photo>> call = api.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull
                    Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    mDataList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                mDataList = null;
            }
        });
        return mDataList;
    }

}
