package com.bigcake.a30daystransformbody.data.source.repository;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

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
    public void getChallengeDays(int challengeId, final LoadChallengeDaysCallBack callBack) {
        mChallengeDataSource.getChallengeDays(challengeId, new LoadChallengeDaysCallBack() {
            @Override
            public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                callBack.onChallengeDaysLoaded(challengeDayList);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void generateChallengesDay(int challengeId, final ChallengeDayCallBack callBack) {
        mChallengeDataSource.generateChallengesDay(challengeId, new ChallengeDayCallBack() {
            @Override
            public void onSuccess() {
                callBack.onSuccess();
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    @Override
    public void updateChallengeDay(ChallengeDay challengeDay, final ChallengeDayCallBack callBack) {
        mChallengeDataSource.updateChallengeDay(challengeDay, new ChallengeDayCallBack() {
            @Override
            public void onSuccess() {
                callBack.onSuccess();
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    @Override
    public void getLastImage(int challengeId, final LoadLastImageCallBack callBack) {
        mChallengeDataSource.getLastImage(challengeId, new LoadLastImageCallBack() {
            @Override
            public void onSuccess(byte[] image) {
                callBack.onSuccess(image);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void getChallengeDayThumbnail(int challengeDayId, @NonNull final LoadChallengeDayThumbnailCallback callback) {
        mChallengeDataSource.getChallengeDayThumbnail(challengeDayId, new LoadChallengeDayThumbnailCallback() {
            @Override
            public void onChallengeDayThumbnailLoaded(byte[] thumbnail) {
                callback.onChallengeDayThumbnailLoaded(thumbnail);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateImage(int challengeDayId, Bitmap newImage, final UpdateChallengeDayImageCallback callBack) {
        mChallengeDataSource.updateImage(challengeDayId, newImage, new UpdateChallengeDayImageCallback() {
            @Override
            public void onUpdated(String image) {
                callBack.onUpdated(image);
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }
}
