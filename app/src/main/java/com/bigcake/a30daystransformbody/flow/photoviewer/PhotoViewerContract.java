package com.bigcake.a30daystransformbody.flow.photoviewer;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Big Cake on 5/3/2017
 */

public interface PhotoViewerContract {
    interface Presenter extends BasePresenter {
        void shareImage();
        void deleteImage();
    }

    interface View extends BaseView<Presenter> {
        void onShowJPGPhoto(byte [] image);
        void onShowGIFPhoto(GifDrawable image);
    }
}
