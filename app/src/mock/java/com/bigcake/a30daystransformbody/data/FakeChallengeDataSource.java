package com.bigcake.a30daystransformbody.data;

import com.bigcake.a30daystransformbody.R;
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
        int inProgress = 4;
        for (int i = 0; i < 30; i++) {
            challengeDayList.add(new ChallengeDay(i, (i + 1) + ""
                    , (i < inProgress - 1) ? R.drawable.fake_leg : R.drawable.ic_camera
                    , (i < inProgress) ? ChallengeDay.STATUS_DONE : (i == inProgress) ? ChallengeDay.STATUS_CURRENT : ChallengeDay.STATUS_IN_PROGRESS
                    , null));
        }
        callBack.onChallengeDaysLoaded(challengeDayList);
    }
}
