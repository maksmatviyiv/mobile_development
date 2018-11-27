package com.example.maksy.mobile_development.list;



import com.example.maksy.mobile_development.entity.Photo;

import java.util.List;

public interface ListContract {
    interface View {
        void display(List<Photo> dataList);
    }

    interface Presenter {
        void getData();
    }

    interface Model {
        List<Photo> callRetrofit();
    }
}

