package com.bigcake.a30daystransformbody.flow.photoviewer;

/**
 * Created by Big Cake on 5/3/2017
 */

public class PhotoViewerPresenter implements PhotoViewerContract.Presenter {

    private PhotoViewerContract.View mView;

    public PhotoViewerPresenter(PhotoViewerContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.onShowPhoto();
    }
}
