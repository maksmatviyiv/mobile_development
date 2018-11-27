package com.example.maksy.mobile_development.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.mobile_development.R;
import com.example.maksy.mobile_development.data.FavoriteDbHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {
    public final static String urlKey = "url";
    public final static String urlTitle = "title";
    boolean isImageFitToScreen;
    @BindView(R.id.name)
    TextView mDetailsTitle;
    @BindView(R.id.img)
    ImageView mDetailImage;
    @BindView(R.id.images_add_to_favourites)
    ImageView mFavoriteImage;
    @BindView(R.id.button_delete_from_favourites)
    Button mDeleteFavourite;
    FavoriteDbHelper mDatabaseHelper;
    String mTitle, mImagePath;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        assert getArguments() != null;
        mTitle = getArguments().getString(urlTitle);
        mImagePath = getArguments().getString(urlKey);
        mDatabaseHelper = new FavoriteDbHelper(getActivity());
        mDetailsTitle.setText(mTitle);
        Picasso.get().load(mImagePath).into(mDetailImage);
        fullScreen();
        addToFavourite();
        removeFromFavourite();
        return view;
    }

    private void fullScreen() {
        mDetailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    mDetailImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                            LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    mDetailImage.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    mDetailImage.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT));
                    mDetailImage.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
    }

    private void addToFavourite() {
        mFavoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.addToFavorites(mTitle);
                mFavoriteImage.setImageResource(R.drawable.ic_favorite_black_24dp);
                Toast.makeText(getContext(), "Add to favorites" + mTitle, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
    private void removeFromFavourite() {
        mDeleteFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteFromFavourite(mTitle);
                mFavoriteImage.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        });
    }
}
