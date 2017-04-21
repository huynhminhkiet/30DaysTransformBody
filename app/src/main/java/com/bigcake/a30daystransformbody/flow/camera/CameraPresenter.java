package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;

/**
 * Created by Big Cake on 4/22/2017
 */

public class CameraPresenter implements CameraContract.Presenter {
    private CameraContract.View mView;

    public CameraPresenter(CameraContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveImage(Bitmap bitmap) {

    }
}
