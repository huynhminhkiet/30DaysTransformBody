package com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public interface ChallengePlanContract {
    interface View extends BaseView<Presenter> {
        void displayChallengeDays(List<ChallengeDay> challengeDayList);
    }

    interface Presenter extends BasePresenter {

    }
}
