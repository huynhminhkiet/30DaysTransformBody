package com.bigcake.a30daystransformbody.manager;

import android.util.Log;

import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;

/**
 * Created by Big Cake on 4/17/2017
 */

public class ChallengeImageManager {
    private static ChallengeImageManager mInstance;
    private ChallengeRepository mChallengeRepository;

    private ChallengeImageManager(ChallengeRepository challengeRepository) {
        mChallengeRepository = challengeRepository;
    }

    public static synchronized ChallengeImageManager getConstance(ChallengeRepository challengeRepository) {
        if (mInstance == null)
            mInstance = new ChallengeImageManager(challengeRepository);
        return mInstance;
    }

    public void displayThumbnail(final int challengeId, final DisplayThumbnailCallback callback) {
        mChallengeRepository.getChallengeDayThumbnail(challengeId, new ChallengeDataSource.LoadChallengeDayThumbnailCallback() {
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

    public interface DisplayThumbnailCallback {
        void onChallengeDayThumbnailLoaded(byte[] thumbnail);

        void onDataNotAvailable();
    }

}
