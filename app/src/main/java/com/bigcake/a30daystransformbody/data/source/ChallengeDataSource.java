package com.bigcake.a30daystransformbody.data.source;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

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

    interface LoadLastImageCallBack {
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

    void getChallengeDays(int challengeId, LoadChallengeDaysCallBack callBack);
    void generateChallengesDay(int challengeId, ChallengeDayCallBack callBack);
    void updateChallengeDay(ChallengeDay challengeDay, ChallengeDayCallBack callBack);
    void getLastImage(int challengeId, LoadLastImageCallBack callBack);
    void getChallengeDayThumbnail(int challengeDayId, @NonNull LoadChallengeDayThumbnailCallback callback);
    void getLastChallengeDayThumbnail(int challengeId, @NonNull LoadChallengeDayThumbnailCallback callback);
    void getLastChallengeDayHasImage(int challengeId, @NonNull GetLastChallengeDay callback);
    void updateImage(int challengeDayId, Bitmap newImage, UpdateChallengeDayImageCallback callBack);
}
