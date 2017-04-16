package com.bigcake.a30daystransformbody.flow.challengedetail;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;

/**
 * Created by kiethuynh on 10/04/2017
 */

public interface ChallengeDetailContract {
    interface View extends BaseView<Presenter> {
        void displayChallenge(Challenge challenge);
    }

    interface Presenter extends BasePresenter{
    }
}
