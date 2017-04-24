package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;

/**
 * Created by Big Cake on 4/22/2017
 */

public class CameraPresenter implements CameraContract.Presenter {
    private CameraContract.View mView;
    private ChallengeRepository mChallengeRepository;

    public CameraPresenter(CameraContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveImage(final ChallengeDay challengeDay, byte [] bitmap) {
        challengeDay.setImage(bitmap);
        mChallengeRepository.updateChallengeDay(challengeDay, new ChallengeDataSource.ChallengeDayCallBack() {
            @Override
            public void onSuccess() {
                mView.onCaptureFinished(challengeDay);
            }

            @Override
            public void onError() {
                mView.onCaptureFail();
            }
        });
    }
}
