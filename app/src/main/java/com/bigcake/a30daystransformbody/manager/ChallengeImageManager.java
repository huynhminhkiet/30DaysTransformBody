package com.bigcake.a30daystransformbody.manager;

import android.os.AsyncTask;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bigcake.a30daystransformbody.utils.FileUtils;

/**
 * Created by Big Cake on 4/17/2017
 */

public class ChallengeImageManager {
    private static ChallengeImageManager mInstance;
    private ChallengeRepository mChallengeRepository;

    private ChallengeImageManager(ChallengeRepository challengeRepository) {
        mChallengeRepository = challengeRepository;
    }

    public static synchronized ChallengeImageManager getInstance(ChallengeRepository challengeRepository) {
        if (mInstance == null)
            mInstance = new ChallengeImageManager(challengeRepository);
        return mInstance;
    }

    public void displayThumbnail(final int challengeDayId, final DisplayImageCallback callback) {
        final byte[][] image = {null};
        new AsyncTask<Void, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(Void... voids) {
                mChallengeRepository.getChallengeDayThumbnail(challengeDayId, new ChallengeDataSource.LoadChallengeDayThumbnailCallback() {
                    @Override
                    public void onChallengeDayThumbnailLoaded(byte[] thumbnail) {
                        image[0] = thumbnail;
                    }

                    @Override
                    public void onDataNotAvailable() {
                    }
                });
                return image[0];
            }

            @Override
            protected void onPostExecute(byte[] image) {
                super.onPostExecute(image);
                if (image != null)
                    callback.onImageLoaded(image);
                else
                    callback.onDataNotAvailable();
            }
        }.execute();
    }

    public void displayLastThumbnail(final int challengeId, final int limitDay, final DisplayImageCallback callback) {
        final byte[][] image = {null};
        new AsyncTask<Void, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(Void... voids) {
                mChallengeRepository.getLastChallengeDayThumbnail(challengeId, limitDay, new ChallengeDataSource.LoadChallengeDayThumbnailCallback() {
                    @Override
                    public void onChallengeDayThumbnailLoaded(byte[] thumbnail) {
                        image[0] = thumbnail;
                    }

                    @Override
                    public void onDataNotAvailable() {
                    }
                });
                return image[0];
            }

            @Override
            protected void onPostExecute(byte[] image) {
                super.onPostExecute(image);
                if (image != null)
                    callback.onImageLoaded(image);
                else
                    callback.onDataNotAvailable();
            }
        }.execute();
    }

    public void displayLastChallengeImage(final int challengeId, int limitDay, final DisplayImageCallback callback) {
        mChallengeRepository.getLastChallengeDayHasImage(challengeId, limitDay, new ChallengeDataSource.GetLastChallengeDay() {
            @Override
            public void onSuccess(ChallengeDay challengeDay) {
                callback.onImageLoaded(FileUtils.loadImage(challengeDay.getImage(), Constants.JPG_DIR));
            }

            @Override
            public void onError() {
                callback.onDataNotAvailable();
            }
        });
    }

    public void displayChangeThumbnail(final int ChallengeImageId, final DisplayImageCallback callback) {
        final byte[][] image = {null};
        new AsyncTask<Void, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(Void... voids) {
                mChallengeRepository.getChangeThumbnail(ChallengeImageId, new ChallengeDataSource.LoadThumbnailCallBack() {
                    @Override
                    public void onSuccess(byte[] thumbnail) {
                        image[0] = thumbnail;
                    }

                    @Override
                    public void onError() {
                    }
                });
                return image[0];
            }

            @Override
            protected void onPostExecute(byte[] image) {
                super.onPostExecute(image);
                if (image != null)
                    callback.onImageLoaded(image);
                else
                    callback.onDataNotAvailable();
            }
        }.execute();
    }

    public interface DisplayImageCallback {
        void onImageLoaded(byte[] thumbnail);

        void onDataNotAvailable();
    }

}
