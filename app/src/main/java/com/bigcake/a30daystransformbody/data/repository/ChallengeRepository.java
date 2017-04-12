package com.bigcake.a30daystransformbody.data.repository;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeRepository implements ChallengeDataSource {
    private static ChallengeRepository mInstance = null;
    private ChallengeDataSource mChallengeDataSource;

    private ChallengeRepository(ChallengeDataSource challengeDataSource) {
        mChallengeDataSource = challengeDataSource;
    }

    public static synchronized  ChallengeRepository getInstance(ChallengeDataSource challengeDataSource) {
        if (mInstance == null)
            mInstance = new ChallengeRepository(challengeDataSource);
        return mInstance;
    }

    @Override
    public void getChallengeDays(final LoadChallengeDaysCallBack callBack) {
        mChallengeDataSource.getChallengeDays(new LoadChallengeDaysCallBack() {
            @Override
            public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                callBack.onChallengeDaysLoaded(challengeDayList);
            }
        });
    }
}
