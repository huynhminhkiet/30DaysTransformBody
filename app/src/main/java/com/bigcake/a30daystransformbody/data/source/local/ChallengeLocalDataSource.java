package com.bigcake.a30daystransformbody.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.utils.FileUtils;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Big Cake on 4/23/2017
 */

public class ChallengeLocalDataSource implements ChallengeDataSource {
    private static ChallengeLocalDataSource mInstance;
    private DatabaseHelper mDatabaseHelper;

    public static synchronized ChallengeLocalDataSource getInstance(Context context) {
        if (mInstance == null)
            mInstance = new ChallengeLocalDataSource(context);
        return mInstance;
    }

    private ChallengeLocalDataSource(Context context) {
        mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public void getChallengeDays(int challengeid, LoadChallengeDaysCallBack callBack) {
        List<ChallengeDay> challengeDayList = new ArrayList<>();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.ChallengeDay._ID,
                TableContent.ChallengeDay.COLUMN_CHALLENGE_ID,
                TableContent.ChallengeDay.COLUMN_DATE,
                TableContent.ChallengeDay.COLUMN_IMAGE,
                TableContent.ChallengeDay.COLUMN_STATUS,
                TableContent.ChallengeDay.COLUMN_LEVEL_ID
        };

        Cursor c = db.query(TableContent.ChallengeDay.TABLE_NAME,
                projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay._ID));
                int challengeId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_CHALLENGE_ID));
                int challengeDate = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_DATE));
                String image = c.getString(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
                int status = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_STATUS));
                int levelId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_LEVEL_ID));

                ChallengeDay challengeDay = new ChallengeDay(id, challengeId, challengeDate, status, null, image);
                challengeDayList.add(challengeDay);
            }
        } else {
            callBack.onError();
        }

        if (c != null) {
            c.close();
        }

        db.close();
        callBack.onChallengeDaysLoaded(challengeDayList);
    }

    @Override
    public void generateChallengesDay(int challengeId, ChallengeDayCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        for (int i = 0; i < 30; i++) {
            ContentValues values = new ContentValues();
            values.put(TableContent.ChallengeDay.COLUMN_CHALLENGE_ID, challengeId);
            values.put(TableContent.ChallengeDay.COLUMN_STATUS,
                    i == 20 ? ChallengeDay.STATUS_CURRENT : i < 21 ? ChallengeDay.STATUS_DONE : ChallengeDay.STATUS_IN_PROGRESS);
            values.put(TableContent.ChallengeDay.COLUMN_DATE, i + 1);

            if (db.insert(TableContent.ChallengeDay.TABLE_NAME, null, values) == -1) {
                callBack.onError();
                return;
            }
        }

        db.close();
        callBack.onSuccess();
    }

    @Override
    public void getLastImage(int challengeId, LoadLastImageCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_IMAGE
        };

        String selection = TableContent.ChallengeDay.COLUMN_CHALLENGE_ID + " = ?";
        String orderBy = TableContent.ChallengeDay.COLUMN_DATE + " DESC";
        String[] selectionArgs = { String.valueOf(challengeId) };

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        byte[] image = null;

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
                if (image != null) {
                    callBack.onSuccess(image);
                    return;
                }
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();
        callBack.onSuccess(image);
    }

    @Override
    public void getChallengeDayThumbnail(int challengeDayId, @NonNull LoadChallengeDayThumbnailCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_THUMBNAIL
        };

        String selection = TableContent.ChallengeDay._ID + " = ?";
        String[] selectionArgs = { String.valueOf(challengeDayId) };

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                byte[] image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_THUMBNAIL));
                if (image != null) {
                    callback.onChallengeDayThumbnailLoaded(image);
                    return;
                }
            }
        } else {
            callback.onDataNotAvailable();
        }

        if (c != null) {
            c.close();
        }

        db.close();
    }

    @Override
    public void getLastChallengeDayThumbnail(int challengeId, @NonNull LoadChallengeDayThumbnailCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_THUMBNAIL
        };

        String selection = TableContent.ChallengeDay.COLUMN_CHALLENGE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(challengeId) };
        String orderBy = TableContent.ChallengeDay._ID + " DESC";

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                byte[] image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_THUMBNAIL));
                if (image != null) {
                    callback.onChallengeDayThumbnailLoaded(image);
                    return;
                }
            }
        } else {
            callback.onDataNotAvailable();
        }

        if (c != null) {
            c.close();
        }

        db.close();
    }

    @Override
    public void getLastChallengeDayHasImage(int challengeId, @NonNull GetLastChallengeDay callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay._ID,
                TableContent.ChallengeDay.COLUMN_CHALLENGE_ID,
                TableContent.ChallengeDay.COLUMN_DATE,
                TableContent.ChallengeDay.COLUMN_IMAGE,
                TableContent.ChallengeDay.COLUMN_STATUS,
                TableContent.ChallengeDay.COLUMN_LEVEL_ID
        };

        String selection = TableContent.ChallengeDay.COLUMN_CHALLENGE_ID + " = ? AND " +
                TableContent.ChallengeDay.COLUMN_IMAGE + " is not null";
        String[] selectionArgs = { String.valueOf(challengeId) };
        String orderBy = TableContent.ChallengeDay._ID + " DESC";

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay._ID));
                int challengeDate = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_DATE));
                String image = c.getString(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
                int status = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_STATUS));
                int levelId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_LEVEL_ID));

                ChallengeDay challengeDay = new ChallengeDay(id, challengeId, challengeDate, status, null, image);
                callback.onSuccess(challengeDay);
                return;
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();
        callback.onError();
    }

    @Override
    public void updateImage(int challengeDayId, Bitmap newImage, UpdateChallengeDayImageCallback callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Bitmap thumbnail = FileUtils.scaleBitmap(newImage, 200);
        values.put(TableContent.ChallengeDay.COLUMN_THUMBNAIL, Utils.convertBitmapToByteArray(thumbnail));
        String imageName = FileUtils.saveImage(newImage, "chllenge-day-" + challengeDayId + ".jpg");
        values.put(TableContent.ChallengeDay.COLUMN_IMAGE, imageName);

        if (db.update(TableContent.ChallengeDay.TABLE_NAME, values,
                TableContent.ChallengeDay._ID + "=" + challengeDayId, null) == -1) {
            callBack.onError();
            return;
        }

        db.close();
        callBack.onUpdated(imageName);
    }

    @Override
    public void updateChallengeDay(ChallengeDay challengeDay, ChallengeDayCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.ChallengeDay.COLUMN_CHALLENGE_ID, challengeDay.getChallengeId());
        values.put(TableContent.ChallengeDay.COLUMN_STATUS, challengeDay.getStatus());
        values.put(TableContent.ChallengeDay.COLUMN_DATE, challengeDay.getDay());
        values.put(TableContent.ChallengeDay.COLUMN_CHALLENGE_ID, challengeDay.getLevel() != null ? 1 : 0);

        if (db.update(TableContent.ChallengeDay.TABLE_NAME, values,
                TableContent.ChallengeDay._ID + "=" + challengeDay.getId(), null) == -1) {
            callBack.onError();
            return;
        }


        db.close();
        callBack.onSuccess();
    }

}
