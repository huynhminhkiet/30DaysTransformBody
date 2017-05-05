package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeDayPresenter implements ChallengeProgressContract.Presenter {
    private ChallengeProgressContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private List<ChallengeDay> mChallengeDayList;

    public ChallengeDayPresenter(@NonNull ChallengeProgressContract.View view, @NonNull ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {
        if (mChallengeDayList == null)
            mChallengeRepository.getChallengeDays(0, new ChallengeDataSource.LoadChallengeDaysCallBack() {
                @Override
                public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                    mChallengeDayList = challengeDayList;
                    mView.displayChallengeDays(mChallengeDayList);
                    mView.displayProgressBar(13);
                }

                @Override
                public void onError() {

                }
            });

        else {
            mView.displayChallengeDays(mChallengeDayList);
            mView.displayProgressBar(13);
        }


    }

    @Override
    public void challengeDayClick(ChallengeDay challengeDay) {
        if (challengeDay.getStatus() == ChallengeDay.STATUS_DONE) {
            mView.openCamera(challengeDay);
        }
    }
}
