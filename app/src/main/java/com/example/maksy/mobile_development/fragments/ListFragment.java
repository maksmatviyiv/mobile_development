package com.example.maksy.mobile_development.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.adapter.CustomAdapter;
import com.example.maksy.mobile_development.model.Photo;
import com.example.maksy.mobile_development.network.Api;
import com.example.maksy.mobile_development.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    public static Fragment newInstance()
    {
        return new ListFragment();
    }
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.no_data)
    TextView mNoDataText;
    CustomAdapter mAdapter;
    @BindView(R.id.pull_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressDialog mProgressDialog;

    private List<Photo> mDataList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_list, viewGroup, false);
        ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        initRecyclerView();
        makeCall();
        return view;

    }

    public void makeCall() {
        Api api = ApiClient.getRetrofitInstance().create(Api.class);
        Call<List<Photo>> call = api.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull
                    Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    mProgressDialog.dismiss();
                    mDataList = response.body();
                    display();
                } else {
                    Toast.makeText(getActivity(), R.string.error,
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), R.string.on_failure,
                        Toast.LENGTH_LONG).show();
                noData();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeCall();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CustomAdapter(getFragmentManager());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mNoDataText.setVisibility(View.INVISIBLE);
    }

    private void display() {
        mAdapter.loadData(mDataList);
        mAdapter.notifyDataSetChanged();
        mNoDataText.setVisibility(View.INVISIBLE);
    }

    private void noData() {
        mNoDataText.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.INVISIBLE);

    }
}

