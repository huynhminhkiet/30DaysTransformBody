package com.bigcake.a30daystransformbody.data.source;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.Weight;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public interface ChallengeDataSource {
    interface LoadChallengeDaysCallBack {
        void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList);
        void onError();
    }

    interface ChallengeCallBack {
        void onSuccess();
        void onError();
    }

    interface LoadThumbnailCallBack {
        void onSuccess(byte [] image);
        void onError();
    }

    interface LoadChallengeDayThumbnailCallback {
        void onChallengeDayThumbnailLoaded(byte[] thumbnail);
        void onDataNotAvailable();
    }

    interface UpdateChallengeDayImageCallback {
        void onUpdated(String image);
        void onError();
    }

    interface GetLastChallengeDay {
        void onSuccess(ChallengeDay challengeDay);
        void onError();
    }

    interface GetChangeImagesCallback {
        void onSuccess(List<ChallengeImage> challengeImageList);
        void onError();
    }

    interface InsertChangeImageCallBack {
        void onSuccess(ChallengeImage challengeImage);
        void onError();
    }

    interface GetAllWeightCallback {
        void onSuccess(List<Weight> weightList);
        void onError();
    }

    interface GetLastWeightCallback {
        void onSuccess(Weight weight);
        void onError();
    }

    void getChallengeDays(int exerciseId, LoadChallengeDaysCallBack callBack);
    void generateChallengesDay(int exerciseId, ChallengeCallBack callBack);
    void updateChallengeDay(ChallengeDay challengeDay, ChallengeCallBack callBack);
    void getLastImage(int exerciseId, LoadThumbnailCallBack callBack);
    void getChallengeDayThumbnail(int challengeDayId, @NonNull LoadChallengeDayThumbnailCallback callback);
    void getLastChallengeDayThumbnail(int exerciseId, int limitDay, @NonNull LoadChallengeDayThumbnailCallback callback);
    void getLastChallengeDayHasImage(int exerciseId, int limitDay, @NonNull GetLastChallengeDay callback);
    void updateImage(int challengeDayId, Bitmap newImage, UpdateChallengeDayImageCallback callBack);
    void deleteChallengeDayImage(ChallengeDay challengeDay, ChallengeCallBack callBack);
    void deleteAllChallengeDayByExercise(int exerciseId, ChallengeCallBack callBack);
    void resetChallengeDayByExercise(int exerciseId, ChallengeCallBack callBack);

    void insertChallengeGif(int exerciseId, byte[] gif, byte[] thumbnail, InsertChangeImageCallBack callBack);
    void getAllChangeImages(int exerciseId, GetChangeImagesCallback callback);
    void getChangeThumbnail(int changeImageId, @NonNull LoadThumbnailCallBack callback);
    void deleteChangeImage(ChallengeImage challengeImage, @NonNull ChallengeCallBack callback);

    void insertWeight(Weight weight, ChallengeCallBack callBack);
    void updateWeight(Weight weight, ChallengeCallBack callBack);
    void getAllWeight(GetAllWeightCallback callback);
    void getLastWeight(GetLastWeightCallback callback);
}
