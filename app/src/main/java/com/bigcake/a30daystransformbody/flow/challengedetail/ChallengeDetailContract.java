package com.bigcake.a30daystransformbody.flow.challengedetail;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.Exercise;

/**
 * Created by kiethuynh on 10/04/2017
 */

public interface ChallengeDetailContract {
    interface View extends BaseView<Presenter> {
        void displayChallenge(Exercise exercise);
    }

    interface Presenter extends BasePresenter{
    }
}
