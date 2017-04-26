package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;

import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public class AlbumPresenter implements AlbumContract.Presenter {
    private AlbumContract.View mView;
    private ChallengeRepository mChallengeRepository;

    public AlbumPresenter(AlbumContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {
        mChallengeRepository.getChallengeDays(0, new ChallengeDataSource.LoadChallengeDaysCallBack() {
            @Override
            public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                mView.displayAllImages(challengeDayList);
            }

            @Override
            public void onError() {

            }
        });
    }
}
