package com.bigcake.a30daystransformbody.flow.photoviewer;

import com.bigcake.a30daystransformbody.utils.Constants;
import com.bigcake.a30daystransformbody.utils.FileUtils;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.io.File;
import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Big Cake on 5/3/2017
 */

public class PhotoViewerPresenter implements PhotoViewerContract.Presenter {

    private PhotoViewerContract.View mView;
    private int mDirectory;
    private String mImage;

    public PhotoViewerPresenter(PhotoViewerContract.View mView, String image, int directory) {
        this.mView = mView;
        mImage = image;
        mDirectory = directory;
    }

    @Override
    public void shareImage() {

    }

    @Override
    public void deleteImage() {

    }

    @Override
    public void start() {
        if (mDirectory == Constants.JPG_DIR)
            mView.onShowJPGPhoto(FileUtils.loadImage(mImage, mDirectory));
        else {
            File gifFile = new File(FileUtils.getImageGifDir(), mImage);
            try {
                GifDrawable gifFromFile = new GifDrawable(gifFile);
                mView.onShowGIFPhoto(gifFromFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
