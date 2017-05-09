package com.bigcake.a30daystransformbody.flow.photoviewer;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
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
    private ChallengeRepository mChallengeRepository;

    public PhotoViewerPresenter(ChallengeRepository challengeRepository, PhotoViewerContract.View view, String image, int directory) {
        mChallengeRepository = challengeRepository;
        mView = view;
        mImage = image;
        mDirectory = directory;
    }

    @Override
    public void shareImage() {

    }

    @Override
    public void deleteChangeImage(ChallengeImage challengeImage) {

    }

    @Override
    public void deleteChallengeDayImage(ChallengeDay challengeDay) {
        mChallengeRepository.deleteChallengeDayImage(challengeDay, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {
                mView.onImageDeleted();
            }

            @Override
            public void onError() {

            }
        });
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
