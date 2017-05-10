package com.bigcake.a30daystransformbody.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
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
    public void getChallengeDays(int exerciseId, LoadChallengeDaysCallBack callBack) {
        List<ChallengeDay> challengeDayList = new ArrayList<>();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.ChallengeDay._ID,
                TableContent.ChallengeDay.COLUMN_EXERCISE_ID,
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
                int challengeId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_EXERCISE_ID));
                int challengeDate = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_DATE));
                String image = c.getString(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
                int status = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_STATUS));
                int levelId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_LEVEL_ID));

                ChallengeDay challengeDay = new ChallengeDay(id, challengeId, challengeDate, status, null, image);
                challengeDayList.add(challengeDay);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (challengeDayList.isEmpty())
            callBack.onError();
        else
            callBack.onChallengeDaysLoaded(challengeDayList);
    }

    @Override
    public void generateChallengesDay(int exerciseId, ChallengeCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        for (int i = 0; i < 30; i++) {
            ContentValues values = new ContentValues();
            values.put(TableContent.ChallengeDay.COLUMN_EXERCISE_ID, exerciseId);
            values.put(TableContent.ChallengeDay.COLUMN_STATUS,
                    i == 28 ? ChallengeDay.STATUS_CURRENT : i < 29 ? ChallengeDay.STATUS_DONE : ChallengeDay.STATUS_IN_PROGRESS);
            values.put(TableContent.ChallengeDay.COLUMN_DATE, i + 1);

            if (db.insert(TableContent.ChallengeDay.TABLE_NAME, null, values) == -1) {
                db.close();
                callBack.onError();
                return;
            }
        }

        db.close();
        callBack.onSuccess();
    }

    @Override
    public void getLastImage(int exerciseId, LoadThumbnailCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_IMAGE
        };

        String selection = TableContent.ChallengeDay.COLUMN_EXERCISE_ID + " = ?";
        String orderBy = TableContent.ChallengeDay.COLUMN_DATE + " DESC";
        String[] selectionArgs = {String.valueOf(exerciseId)};

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        byte[] image = null;

        if (c != null && c.getCount() > 0) {
            if (c.moveToNext()) {
                image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();
        if (image == null)
            callBack.onError();
        else
            callBack.onSuccess(image);
    }

    @Override
    public void getChallengeDayThumbnail(int challengeDayId, @NonNull LoadChallengeDayThumbnailCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_THUMBNAIL
        };

        String selection = TableContent.ChallengeDay._ID + " = ?";
        String[] selectionArgs = {String.valueOf(challengeDayId)};

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        byte[] image = null;
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_THUMBNAIL));
            }
        }

        if (c != null) {
            c.close();
        }
        db.close();
        if (image != null)
            callback.onChallengeDayThumbnailLoaded(image);
        else
            callback.onDataNotAvailable();

    }

    @Override
    public void getLastChallengeDayThumbnail(int exerciseId, @NonNull LoadChallengeDayThumbnailCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay.COLUMN_THUMBNAIL
        };

        String selection = TableContent.ChallengeDay.COLUMN_EXERCISE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(exerciseId)};
        String orderBy = TableContent.ChallengeDay._ID + " DESC";

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);
        byte[] image = null;
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_THUMBNAIL));

            }
        }

        if (c != null) {
            c.close();
        }

        db.close();
        if (image != null)
            callback.onChallengeDayThumbnailLoaded(image);
        else
            callback.onDataNotAvailable();
    }

    @Override
    public void getLastChallengeDayHasImage(int exerciseId, @NonNull GetLastChallengeDay callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeDay._ID,
                TableContent.ChallengeDay.COLUMN_EXERCISE_ID,
                TableContent.ChallengeDay.COLUMN_DATE,
                TableContent.ChallengeDay.COLUMN_IMAGE,
                TableContent.ChallengeDay.COLUMN_STATUS,
                TableContent.ChallengeDay.COLUMN_LEVEL_ID
        };

        String selection = TableContent.ChallengeDay.COLUMN_EXERCISE_ID + " = ? AND " +
                TableContent.ChallengeDay.COLUMN_IMAGE + " is not null";
        String[] selectionArgs = {String.valueOf(exerciseId)};
        String orderBy = TableContent.ChallengeDay._ID + " DESC";

        Cursor c = db.query(
                TableContent.ChallengeDay.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        ChallengeDay challengeDay = null;
        if (c != null && c.getCount() > 0) {
            if (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay._ID));
                int challengeDate = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_DATE));
                String image = c.getString(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_IMAGE));
                int status = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_STATUS));
                int levelId = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeDay.COLUMN_LEVEL_ID));

                challengeDay = new ChallengeDay(id, exerciseId, challengeDate, status, null, image);
            }
        }

        if (c != null) {
            c.close();
        }
        db.close();
        if (challengeDay != null)
            callback.onSuccess(challengeDay);
        else
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
            db.close();
            callBack.onError();
            return;
        }

        db.close();
        callBack.onUpdated(imageName);
    }

    @Override
    public void updateChallengeDay(ChallengeDay challengeDay, ChallengeCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.ChallengeDay.COLUMN_EXERCISE_ID, challengeDay.getChallengeId());
        values.put(TableContent.ChallengeDay.COLUMN_STATUS, challengeDay.getStatus());
        values.put(TableContent.ChallengeDay.COLUMN_DATE, challengeDay.getDay());
        values.put(TableContent.ChallengeDay.COLUMN_EXERCISE_ID, challengeDay.getLevel() != null ? 1 : 0);

        if (db.update(TableContent.ChallengeDay.TABLE_NAME, values,
                TableContent.ChallengeDay._ID + "=" + challengeDay.getId(), null) == -1) {
            db.close();
            callBack.onError();
            return;
        }

        db.close();
        callBack.onSuccess();
    }

    @Override
    public void insertChallengeGif(int exerciseId, byte[] gifFile, byte[] thumbnail, InsertChangeImageCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.ChallengeImage.COLUMN_EXERCISE_ID, exerciseId);
        values.put(TableContent.ChallengeImage.COLUMN_CHANGE_THUMBNAIL, thumbnail);
        long id = db.insert(TableContent.ChallengeImage.TABLE_NAME, null, values);
        if (id == -1) {
            callBack.onError();
            return;
        }
        values.clear();
        String imageName = "gif-" + exerciseId + "-" + id;
        values.put(TableContent.ChallengeImage.COLUMN_CHANGE_IMAGE, imageName + ".gif");
        if (db.update(TableContent.ChallengeImage.TABLE_NAME, values,
                TableContent.ChallengeImage._ID + "=" + id, null) == -1) {
            db.close();
            callBack.onError();
            return;
        }

        db.close();
        FileUtils.saveGifImage(gifFile, imageName);
        callBack.onSuccess(new ChallengeImage((int) id, exerciseId, imageName + ".gif"));
    }

    @Override
    public void deleteChallengeDayImage(ChallengeDay challengeDay, ChallengeCallBack callBack) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.ChallengeDay.COLUMN_THUMBNAIL, (byte[]) null);
        values.put(TableContent.ChallengeDay.COLUMN_IMAGE, (byte[]) null);

        if (db.update(TableContent.ChallengeDay.TABLE_NAME, values,
                TableContent.ChallengeDay._ID + "=" + challengeDay.getId(), null) == -1) {
            db.close();
            callBack.onError();
            return;
        }

        db.close();
        FileUtils.deleteImage(FileUtils.getImageDir(), challengeDay.getImage());
        callBack.onSuccess();
    }

    @Override
    public void deleteChangeImage(ChallengeImage challengeImage, @NonNull ChallengeCallBack callback) {
        FileUtils.deleteImage(FileUtils.getImageGifDir(), challengeImage.getChallengeImage());
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        if (db.delete(TableContent.ChallengeImage.TABLE_NAME,
                TableContent.ChallengeImage._ID + "=" + challengeImage.getId(), null) == -1) {
            db.close();
            callback.onError();
            return;
        }

        db.close();
        callback.onSuccess();
    }

    @Override
    public void getChangeThumbnail(int changeImageId, @NonNull LoadThumbnailCallBack callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        String[] projection = {
                TableContent.ChallengeImage.COLUMN_CHANGE_THUMBNAIL
        };

        String selection = TableContent.ChallengeImage._ID + " = ?";
        String[] selectionArgs = {String.valueOf(changeImageId)};

        Cursor c = db.query(
                TableContent.ChallengeImage.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        byte[] image = null;
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                image = c.getBlob(c.getColumnIndexOrThrow(TableContent.ChallengeImage.COLUMN_CHANGE_THUMBNAIL));
            }
        }

        if (c != null) {
            c.close();
        }
        db.close();
        if (image != null)
            callback.onSuccess(image);
        else
            callback.onError();
    }

    @Override
    public void getAllChangeImages(int exerciseId, GetChangeImagesCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        List<ChallengeImage> challengeImageList = new ArrayList<>();

        String[] projection = {
                TableContent.ChallengeImage._ID,
                TableContent.ChallengeImage.COLUMN_CHANGE_IMAGE
        };

        String selection = TableContent.ChallengeImage.COLUMN_EXERCISE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(exerciseId)};
        String orderBy = TableContent.ChallengeImage._ID + " ASC";

        Cursor c = db.query(
                TableContent.ChallengeImage.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.ChallengeImage._ID));
                String image = c.getString(c.getColumnIndexOrThrow(TableContent.ChallengeImage.COLUMN_CHANGE_IMAGE));
                ChallengeImage challengeImage = new ChallengeImage(id, exerciseId, image);
                challengeImageList.add(challengeImage);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();

        if (challengeImageList.isEmpty())
            callback.onError();
        else
            callback.onSuccess(challengeImageList);
    }
}
