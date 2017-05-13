package com.bigcake.a30daystransformbody.flow.challengedetail.changealbum;

import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;

import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public class ChangeImagesPresenter implements ChangeImagesContract.Presenter {
    private ChangeImagesContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private List<ChallengeImage> mChallengeImages;
    private Exercise mExercise;

    public ChangeImagesPresenter(ChangeImagesContract.View view, ChallengeRepository challengeRepository, Exercise exercise) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
        mExercise = exercise;
    }

    @Override
    public void start() {
        if (mChallengeImages == null)
            mChallengeRepository.getAllChangeImages(mExercise.getId(), new ChallengeDataSource.GetChangeImagesCallback() {
                @Override
                public void onSuccess(List<ChallengeImage> challengeImageList) {
                    mChallengeImages = challengeImageList;
                    mView.showAllChangeImages(challengeImageList);
                }

                @Override
                public void onError() {

                }
            });
        else
            mView.showAllChangeImages(mChallengeImages);
    }
}
