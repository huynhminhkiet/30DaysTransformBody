package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengeDetailPresenter implements ChallengeDetailContract.Presenter {
    private ChallengeDetailContract.View mView;
    private Exercise mExercise;

    public ChallengeDetailPresenter(@NonNull ChallengeDetailContract.View view, @NonNull Exercise exercise) {
        this.mView = view;
        this.mExercise = exercise;
    }

    @Override
    public void start() {
        mView.displayChallenge(mExercise);
    }
}
