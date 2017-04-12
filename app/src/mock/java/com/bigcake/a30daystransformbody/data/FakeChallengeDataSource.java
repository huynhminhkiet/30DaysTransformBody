package com.bigcake.a30daystransformbody.data;

import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class FakeChallengeDataSource implements ChallengeDataSource{
    private static FakeChallengeDataSource mInstance = null;
    public static synchronized FakeChallengeDataSource getInstance() {
        if (mInstance == null)
            mInstance = new FakeChallengeDataSource();
        return mInstance;
    }

    @Override
    public void getChallengeDays(LoadChallengeDaysCallBack callBack) {
        List<ChallengeDay> challengeDayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            challengeDayList.add(new ChallengeDay(i, (i + 1) + "", i, 1, null));
        }
        callBack.onChallengeDaysLoaded(challengeDayList);
    }
}
