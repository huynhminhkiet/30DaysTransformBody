package com.bigcake.a30daystransformbody.flow.photoviewer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewerActivity extends BaseActivity implements PhotoViewerContract.View {
    private ImageView photoView;
    private GifImageView gifImageView;
    private PhotoViewAttacher mPhotoViewAttacher;
    private ImageButton btnDeleteImage, btnShareImage;
    private PhotoViewerContract.Presenter mPresenter;
    private ChallengeDay challengeDay;
    private ChallengeImage challengeImage;
    private int tag;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_photo_viewer;
    }

    @Override
    protected void initViews() {
        bindViews();

        tag = getIntent().getIntExtra(Constants.FLOW_PHOTO_VIEWER, Constants.TAG_CHALLENGE_ALBUM);
        if (tag == Constants.TAG_CHALLENGE_ALBUM) {
            challengeDay = (ChallengeDay) getIntent().getSerializableExtra(Constants.EXTRA_CHALLENGE_DAY);
            mPresenter = new PhotoViewerPresenter(Injection.provideChallengeRepository(this), this, challengeDay.getImage(), Constants.JPG_DIR);
        } else if (tag == Constants.TAG_CHAGE_IMAGE) {
            challengeImage = (ChallengeImage) getIntent().getSerializableExtra(Constants.EXTRA_CHALLENGE_IMAGE);
            mPresenter = new PhotoViewerPresenter(Injection.provideChallengeRepository(this), this, challengeImage.getChallengeImage(), Constants.GIF_DIR);
        }

        mPresenter.start();

        btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.shareImage();
            }
        });

        btnDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tag == Constants.TAG_CHALLENGE_ALBUM) {
                    mPresenter.deleteChallengeDayImage(challengeDay);
                } else {
                    mPresenter.deleteChangeImage(challengeImage);
                }
            }
        });
    }

    @Override
    public void onImageDeleted() {
        Intent returnIntent = new Intent();
        if (tag == Constants.TAG_CHALLENGE_ALBUM)
            returnIntent.putExtra(Constants.EXTRA_CHALLENGE_DAY, challengeDay);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void bindViews() {
        photoView = (ImageView) findViewById(R.id.photo_view);
        mPhotoViewAttacher = new PhotoViewAttacher(photoView);
        mPhotoViewAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);
        gifImageView = (GifImageView) findViewById(R.id.gif_view);
        btnDeleteImage = (ImageButton) findViewById(R.id.btn_delete_image);
        btnShareImage = (ImageButton) findViewById(R.id.btn_share_image);
    }

    @Override
    public void setPresenter(PhotoViewerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShowGIFPhoto(GifDrawable image) {
        photoView.setVisibility(View.GONE);
        gifImageView.setVisibility(View.VISIBLE);
        gifImageView.setImageDrawable(image);
    }

    @Override
    public void onShowJPGPhoto(byte[] image) {
        photoView.setVisibility(View.VISIBLE);
        gifImageView.setVisibility(View.GONE);

        Glide.with(this).load(image).asBitmap().into(new BitmapImageViewTarget(photoView) {
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                mPhotoViewAttacher.update();
            }
        });
    }
}
