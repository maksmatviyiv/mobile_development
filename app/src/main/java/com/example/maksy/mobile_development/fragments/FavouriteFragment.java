package com.example.maksy.mobile_development.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.data.FavoriteDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment {
    public static FavouriteFragment newInstance()
    {
        return new FavouriteFragment();
    }
    FavoriteDbHelper mDatabaseHelper;
    @BindView(R.id.listView)
    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this, view);
        mDatabaseHelper = new FavoriteDbHelper(getActivity());
        favoriteList();
        return view;
    }

    private void favoriteList() {
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                listData);
        mListView.setAdapter(adapter);
    }
}
