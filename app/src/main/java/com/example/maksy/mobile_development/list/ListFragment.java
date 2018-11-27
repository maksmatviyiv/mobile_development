package com.example.maksy.mobile_development.list;


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


import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.entity.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListContract.View {
    public static Fragment newInstance() {
        return new ListFragment();
    }

    @BindView(R.id.recycler_view)

    RecyclerView mRecyclerView;
    @BindView(R.id.no_data)
    TextView mNoDataText;
    CustomAdapter mAdapter;
    @BindView(R.id.pull_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ListContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_list, viewGroup, false);

        mPresenter = new ListPresenter(this);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initRecyclerView();
            mNoDataText.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.getData();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        mPresenter.getData();
        return view;

    }

    private void initRecyclerView() {
        mAdapter = new CustomAdapter(getFragmentManager());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mNoDataText.setVisibility(View.VISIBLE);
    }

    public void display(List<Photo> mDataList) {
        mAdapter.setData(mDataList);
        mAdapter.notifyDataSetChanged();
        mNoDataText.setVisibility(View.INVISIBLE);
    }

    public void noData() {
        mNoDataText.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.INVISIBLE);

    }
}
