package com.bigcake.a30daystransformbody.data.source.repository;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
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
    public void getChallengeDays(int exerciseId, final LoadChallengeDaysCallBack callBack) {
        mChallengeDataSource.getChallengeDays(exerciseId, new LoadChallengeDaysCallBack() {
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
    public void generateChallengesDay(int exerciseId, final ChallengeCallBack callBack) {
        mChallengeDataSource.generateChallengesDay(exerciseId, new ChallengeCallBack() {
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
    public void updateChallengeDay(ChallengeDay challengeDay, final ChallengeCallBack callBack) {
        mChallengeDataSource.updateChallengeDay(challengeDay, new ChallengeCallBack() {
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
    public void getLastImage(int exerciseId, final LoadThumbnailCallBack callBack) {
        mChallengeDataSource.getLastImage(exerciseId, new LoadThumbnailCallBack() {
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
    public void insertChallengeGif(int exerciseId, byte[] gif, byte[] thumbnail, final ChallengeCallBack callBack) {
        mChallengeDataSource.insertChallengeGif(exerciseId, gif, thumbnail, new ChallengeCallBack() {
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
    public void getLastChallengeDayHasImage(int exerciseId, @NonNull final GetLastChallengeDay callback) {
        mChallengeDataSource.getLastChallengeDayHasImage(exerciseId, new GetLastChallengeDay() {
            @Override
            public void onSuccess(ChallengeDay challengeDay) {
                callback.onSuccess(challengeDay);
            }

            @Override
            public void onError() {
                callback.onError();
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
    public void getLastChallengeDayThumbnail(int exerciseId, @NonNull final LoadChallengeDayThumbnailCallback callback) {
        mChallengeDataSource.getLastChallengeDayThumbnail(exerciseId, new LoadChallengeDayThumbnailCallback() {
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
    public void getAllChangeImages(int exerciseId, final GetChangeImagesCallback callback) {
        mChallengeDataSource.getAllChangeImages(exerciseId, new GetChangeImagesCallback() {
            @Override
            public void onSuccess(List<ChallengeImage> challengeImageList) {
                callback.onSuccess(challengeImageList);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getChangeThumbnail(int changeImageId, @NonNull final LoadThumbnailCallBack callback) {
        mChallengeDataSource.getChangeThumbnail(changeImageId, new LoadThumbnailCallBack() {
            @Override
            public void onSuccess(byte[] image) {
                callback.onSuccess(image);
            }

            @Override
            public void onError() {
                callback.onError();
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
