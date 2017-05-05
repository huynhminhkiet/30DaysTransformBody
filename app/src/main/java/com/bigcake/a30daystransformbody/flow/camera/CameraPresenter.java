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
    private byte[] lastImage;

    public CameraPresenter(CameraContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {
        getLastImage();
    }

    @Override
    public void saveImage(final ChallengeDay challengeDay, Bitmap bitmap) {
        mChallengeRepository.updateImage(challengeDay.getId(), bitmap, new ChallengeDataSource.UpdateChallengeDayImageCallback() {
            @Override
            public void onUpdated(String image) {
                challengeDay.setImage(image);
                mView.onCaptureFinished(challengeDay);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onButtonLastImageClick() {
        mView.displayLastImagePreview(lastImage);
    }

    private void getLastImage() {
        mChallengeRepository.getLastImage(0, new ChallengeDataSource.LoadLastImageCallBack() {
            @Override
            public void onSuccess(byte[] image) {
                lastImage = image;
                mView.displayShowLastImageButton(image);
            }

            @Override
            public void onError() {

            }
        });
    }
}
