package com.example.maksy.mobile_development;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        initRecyclerView();
        makeCall();
    }
    public void makeCall() {
        Api api = ApiClient.getRetrofitInstance().create(Api.class);
        Call<List<Photo>> call = api.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {

                if(response.isSuccessful()) {
                    mProgressDialog.dismiss();
                    mDataList = response.body();
                    display();
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.error,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, R.string.on_failure,
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
        mAdapter = new CustomAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
