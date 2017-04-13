package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.support.annotation.NonNull;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengeDetailPresenter implements ChallengeDetailContract.Presenter {
    private ChallengeDetailContract.View mView;
    private Challenge mChallenge;

    public ChallengeDetailPresenter(@NonNull ChallengeDetailContract.View view, @NonNull Challenge challenge) {
        this.mView = view;
        this.mChallenge = challenge;
    }

    @Override
    public void start() {
        mView.displayChallenge(mChallenge);
    }
}
