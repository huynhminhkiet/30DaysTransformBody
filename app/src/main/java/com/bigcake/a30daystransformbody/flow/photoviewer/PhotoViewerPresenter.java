package com.bigcake.a30daystransformbody.flow.photoviewer;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.utils.FileUtils;

/**
 * Created by Big Cake on 5/3/2017
 */

public class PhotoViewerPresenter implements PhotoViewerContract.Presenter {

    private PhotoViewerContract.View mView;
    private ChallengeDay mChallengeDay;

    public PhotoViewerPresenter(PhotoViewerContract.View mView, ChallengeDay challengeDay) {
        this.mView = mView;
        mChallengeDay = challengeDay;
    }

    @Override
    public void start() {
        mView.onShowPhoto(FileUtils.loadImage(mChallengeDay.getImage()));
    }
}
