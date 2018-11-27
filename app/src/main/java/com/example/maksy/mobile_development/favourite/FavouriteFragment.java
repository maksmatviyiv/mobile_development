package com.example.maksy.mobile_development.favourite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.maksy.mobile_development.ApplicationEx;
import com.example.maksy.mobile_development.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment implements FavouriteContract.View {
    public static FavouriteFragment newInstance() {
        return new FavouriteFragment();
    }

    @BindView(R.id.listView)
    ListView mListView;
    public FavouriteContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new FavouritePresenter(this);
        mPresenter.setDataToListView();
        return view;
    }

    public void display(List<String> listData) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ApplicationEx.getContext(),
                                                               android.R.layout.simple_list_item_1,
                                                               listData);
        mListView.setAdapter(arrayAdapter);
    }
}