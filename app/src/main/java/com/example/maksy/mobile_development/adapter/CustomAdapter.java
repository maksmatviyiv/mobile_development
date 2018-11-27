package com.example.maksy.mobile_development.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.fragments.DetailFragment;
import com.example.maksy.mobile_development.model.Photo;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private FragmentManager fragmentManager;
    private List<Photo> mDataList;
    public CustomAdapter(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }
    public void loadData(List<Photo> mDataList){
        this.mDataList = mDataList;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImage)
        ImageView coverImage;
        @BindView(R.id.title)
        TextView txtTitle;
        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Photo photo = mDataList.get(position);
                        Bundle data = new Bundle();
                        data.putString("title", photo.getTitle());
                        data.putString("url", photo.getThumbnailUrl());
                        DetailFragment listItemFragment = new DetailFragment();
                        listItemFragment.setArguments(data);
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_replace, listItemFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_photo, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        final Photo photo = mDataList.get(position);
        Picasso.get().load(photo.getThumbnailUrl()).into(holder.coverImage);
        holder.txtTitle.setText(photo.getTitle());

    }
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }
}