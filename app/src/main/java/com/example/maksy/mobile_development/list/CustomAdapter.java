package com.example.maksy.mobile_development.list;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.maksy.mobile_development.ApplicationEx;
import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.detail.DetailFragment;
import com.example.maksy.mobile_development.entity.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private FragmentManager fragmentManager;

    private List<Photo> mDataList;

    public CustomAdapter(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }

    public void setData(List<Photo> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_list_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        final Photo photo = mDataList.get(position);
        Picasso.get().load(photo.getThumbnailUrl()).into(holder.mCoverImage);
        holder.mTxtTitle.setText(photo.getTitle());
    }

    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImage)
        ImageView mCoverImage;
        @BindView(R.id.title)
        TextView mTxtTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Photo photo = mDataList.get(position);
                        ApplicationEx.setTitle(photo.getTitle());
                        ApplicationEx.setThumbnailUrl(photo.getThumbnailUrl());
                        DetailFragment listItemFragment = new DetailFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_replace, listItemFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
            ButterKnife.bind(this, itemView);
        }
    }
}
