package com.example.maksy.mobile_development.list;



import com.example.maksy.mobile_development.entity.Photo;

import java.util.List;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View mView;
    private ListContract.Model mModel;
    private List<Photo> mDataList;

    public ListPresenter(ListContract.View mView) {
        this.mView = mView;
        this.mModel = new ListModel();
    }

    @Override
    public void getData() {
        mDataList = mModel.callRetrofit();
        mView.display(mDataList);
    }
}


