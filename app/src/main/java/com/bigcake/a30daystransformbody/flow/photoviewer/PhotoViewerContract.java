package com.bigcake.a30daystransformbody.flow.photoviewer;

import android.graphics.Bitmap;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;

import java.io.File;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Big Cake on 5/3/2017
 */

public interface PhotoViewerContract {
    interface Presenter extends BasePresenter {
        void shareImage();
        void deleteChangeImage(ChallengeImage challengeImage);
        void deleteChallengeDayImage(ChallengeDay challengeDay);
    }

    interface View extends BaseView<Presenter> {
        void onShowJPGPhoto(byte [] image);
        void onShowGIFPhoto(GifDrawable image);
        void onImageDeleted();
        void shareImage(File file);
    }
}
