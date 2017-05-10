package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
import com.bigcake.a30daystransformbody.utils.FileUtils;

/**
 * Created by Big Cake on 4/22/2017
 */

public class CameraPresenter implements CameraContract.Presenter {
    private CameraContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private ChallengeDay mChallengeDay;
    private byte[] lastImage;

    public CameraPresenter(CameraContract.View view, ChallengeRepository challengeRepository, ChallengeDay challengeDay) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
        this.mChallengeDay = challengeDay;
    }

    @Override
    public void start() {
        getLastImage(mChallengeDay);
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
        ChallengeImageManager.getInstance(mChallengeRepository).displayLastChallengeImage(mChallengeDay.getChallengeId(), mChallengeDay.getDay(),
                new ChallengeImageManager.DisplayImageCallback() {
                    @Override
                    public void onImageLoaded(byte[] thumbnail) {
                        mView.displayLastImagePreview(thumbnail);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
    }

    private void getLastImage(ChallengeDay challengeDay) {
        ChallengeImageManager.getInstance(mChallengeRepository).displayLastThumbnail(challengeDay.getChallengeId(), challengeDay.getDay(),
                new ChallengeImageManager.DisplayImageCallback() {
                    @Override
                    public void onImageLoaded(byte[] thumbnail) {
                        lastImage = thumbnail;
                        mView.displayShowLastImageButton(thumbnail);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
    }
}
