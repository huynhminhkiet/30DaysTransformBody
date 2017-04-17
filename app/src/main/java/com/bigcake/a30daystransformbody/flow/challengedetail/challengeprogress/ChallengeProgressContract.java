package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public interface ChallengeProgressContract {
    interface View extends BaseView<Presenter> {
        void displayChallengeDays(List<ChallengeDay> challengeDayList);
        void displayProgressBar(int progress);
        void openCamera();
    }

    interface Presenter extends BasePresenter {
        void challengeDayClick(ChallengeDay challengeDay);
    }
}
