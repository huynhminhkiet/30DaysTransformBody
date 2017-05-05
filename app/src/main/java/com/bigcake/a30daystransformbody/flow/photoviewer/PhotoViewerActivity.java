package com.bigcake.a30daystransformbody.flow.photoviewer;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewerActivity extends BaseActivity implements PhotoViewerContract.View {
    private ImageView photoView;
    private PhotoViewAttacher mPhotoViewAttacher;

    private PhotoViewerContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_photo_viewer;
    }

    @Override
    protected void initViews() {
        photoView = (ImageView) findViewById(R.id.photo_view);
        mPhotoViewAttacher = new PhotoViewAttacher(photoView);
        mPhotoViewAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mPresenter = new PhotoViewerPresenter(this);
        mPresenter.start();
    }

    private void bindImage() {
//        int tag = getIntent().getIntExtra(Constants.FLOW_PHOTO_VIEWER, Constants.TAG_CHALLENGE_ALBUM);
//        if (tag == Constants.TAG_CHALLENGE_ALBUM) {
//            ChallengeDay challengeDay = (ChallengeDay) getIntent().getSerializableExtra(Constants.EXTRA_CHALLENGE_DAY);
//            Glide.with(this).load(challengeDay.getImageThumbnail()).asBitmap().into(new BitmapImageViewTarget(photoView) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    super.setResource(resource);
//                    mPhotoViewAttacher.update();
//                }
//            });
//        }
    }

    @Override
    public void setPresenter(PhotoViewerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShowPhoto() {
        bindImage();
    }
}
