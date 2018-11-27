package com.example.maksy.mobile_development.detail;

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


import com.example.maksy.mobile_development.ApplicationEx;
import com.example.maksy.mobile_development.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    boolean isImageFitToScreen;
    private DetailContract.Presenter mPresenter;

    @BindView(R.id.name)
    TextView mDetailsTitle;
    @BindView(R.id.img)
    ImageView mDetailImage;
    @BindView(R.id.images_add_to_favourites)
    ImageView mFavoriteImage;
    @BindView(R.id.button_delete_from_favourites)
    Button mDeleteFavourite;

    String mImagePath;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        mImagePath = ApplicationEx.getThumbnailUrl();
        mPresenter = new DetailPresenter();
        mDetailsTitle.setText(ApplicationEx.getTitle());
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
                mPresenter.makeFavorite();
                mFavoriteImage.setImageResource(R.drawable.ic_favorite_black_24dp);
            }
        });
    }

    private void removeFromFavourite() {
        mDeleteFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.removeFavorite();
                mFavoriteImage.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        });
    }
}
