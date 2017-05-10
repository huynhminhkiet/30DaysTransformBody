package com.bigcake.a30daystransformbody.flow.main;

import com.bigcake.a30daystransformbody.data.Weight;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.util.Date;

/**
 * Created by Big Cake on 5/11/2017
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private ChallengeRepository mChallengeRepository;

    public MainPresenter(MainContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {
        mView.resetWeightTracker();
        mChallengeRepository.getLastWeight(new ChallengeDataSource.GetLastWeightCallback() {
            @Override
            public void onSuccess(Weight weight) {
                int result = Utils.getZeroTimeDate(weight.getDate()).compareTo(Utils.getZeroTimeDate(new Date()));
                if (result == 0)
                    mView.trackWeight(weight);
            }

            @Override
            public void onError() {

            }
        });
    }
}
