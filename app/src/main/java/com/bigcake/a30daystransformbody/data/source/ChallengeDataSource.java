package com.bigcake.a30daystransformbody.data.source;

import com.bigcake.a30daystransformbody.data.Challenge;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public interface ChallengeDataSource {
    interface LoadChallengeDaysCallBack {
        void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList);
        void onError();
    }

    interface ChallengeDayCallBack {
        void onSuccess();
        void onError();
    }

    void getChallengeDays(int challengeId, LoadChallengeDaysCallBack callBack);
    void generateChallengesDay(int challengeId, ChallengeDayCallBack callBack);
    void updateChallengeDay(ChallengeDay challengeDay, ChallengeDayCallBack callBack);


}
