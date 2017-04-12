package com.bigcake.a30daystransformbody.data.source;

import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public interface ChallengeDataSource {
    interface LoadChallengeDaysCallBack {
        void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList);
    }

    void getChallengeDays(LoadChallengeDaysCallBack callBack);
}
