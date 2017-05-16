package com.bigcake.a30daystransformbody.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseLocalDataSource implements ExerciseDataSource {
    private static final long DATA_SIZE = 3;
    private static ExerciseLocalDataSource mInstance;

    private DatabaseHelper mDatabaseHelper;

    public static synchronized ExerciseLocalDataSource getInstance(Context context) {
        if (mInstance == null)
            mInstance = new ExerciseLocalDataSource(context);
        return mInstance;
    }

    private ExerciseLocalDataSource(Context context) {
        mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public void getExerciseCategorise(@NonNull LoadExerciseCategoryCallBack callBack) {
        List<ExerciseCategory> exerciseCategoryList = new ArrayList<>();
        exerciseCategoryList.add(new ExerciseCategory(1, "Push", "Description 1"));
        exerciseCategoryList.add(new ExerciseCategory(2, "Pull", "Description 2"));
        exerciseCategoryList.add(new ExerciseCategory(3, "Led & Glute", "Description 2"));
        exerciseCategoryList.add(new ExerciseCategory(4, "Core", "Description 2"));
        callBack.onExerciseCategoryLoaded(exerciseCategoryList);
    }

    @Override
    public void getExercise(int exerciseId, @NonNull LoadExerciseCallBack callBack) {
        Exercise exercise = null;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.Exercise._ID,
                TableContent.Exercise.COLUMN_CATEGORY_ID,
                TableContent.Exercise.COLUMN_TITLE,
                TableContent.Exercise.COLUMN_TAG,
                TableContent.Exercise.COLUMN_IMAGES,
                TableContent.Exercise.COLUMN_DESCRIPTIONS,
                TableContent.Exercise.COLUMN_DAY
        };
        String selection = TableContent.Exercise._ID + " = ?";
        String [] selectionArgs = {String.valueOf(exerciseId)};

        Cursor c = db.query(TableContent.Exercise.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            if (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise._ID));
                int categoryId = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_CATEGORY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TITLE));
                String tag = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TAG));
                String images = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_IMAGES));
                String descs = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DESCRIPTIONS));
                int day = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DAY));

                exercise = new Exercise(id, categoryId, title, tag, images, descs, day);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (exercise == null)
            callBack.onDataNotAvailable();
        else
            callBack.onExerciseLoaded(exercise);
    }

    @Override
    public void updateExercise(Exercise exercise, @NonNull DefaultCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.Exercise.COLUMN_DAY, exercise.getDay());

        String selection = TableContent.Exercise._ID + " = ?";
        String [] selectionArgs = {String.valueOf(exercise.getId())};

        long result = db.update(TableContent.Exercise.TABLE_NAME, values, selection, selectionArgs);
        db.close();
        if (result == -1) {
            callback.onError();
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void saveExercise(@NonNull Exercise exercise, @NonNull DefaultCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableContent.Exercise.COLUMN_CATEGORY_ID, exercise.getCategoryId());
        values.put(TableContent.Exercise.COLUMN_TITLE, exercise.getTitle());
        values.put(TableContent.Exercise.COLUMN_TAG, exercise.getTag());
        values.put(TableContent.Exercise.COLUMN_IMAGES, exercise.getImages());
        values.put(TableContent.Exercise.COLUMN_DESCRIPTIONS, exercise.getDescriptions());
        values.put(TableContent.Exercise.COLUMN_DAY, 0);

        long id = db.insert(TableContent.Exercise.TABLE_NAME, null, values);
        if (id == -1) {
            callback.onError();
        } else {
            callback.onSuccess();
        }
        db.close();
    }

    @Override
    public void checkFullData(@NonNull CheckFullDataCallback callback) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        long size  = DatabaseUtils.queryNumEntries(db, TableContent.Exercise.TABLE_NAME);
        if (size == DATA_SIZE)
            callback.onFull();
        else
            callback.onNotFull();
    }

    @Override
    public void getExercisesOnProgress(@NonNull LoadExerciseListCallBack callBack) {
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.Exercise._ID,
                TableContent.Exercise.COLUMN_CATEGORY_ID,
                TableContent.Exercise.COLUMN_TITLE,
                TableContent.Exercise.COLUMN_TAG,
                TableContent.Exercise.COLUMN_IMAGES,
                TableContent.Exercise.COLUMN_DESCRIPTIONS,
                TableContent.Exercise.COLUMN_DAY
        };
        String selection = TableContent.Exercise.COLUMN_DAY + " > ?";
        String [] selectionArgs = {String.valueOf(0)};

        Cursor c = db.query(TableContent.Exercise.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise._ID));
                int categoryId = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_CATEGORY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TITLE));
                String tag = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TAG));
                String images = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_IMAGES));
                String descs = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DESCRIPTIONS));
                int day = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DAY));

                Exercise exercise = new Exercise(id, categoryId, title, tag, images, descs, day);
                exerciseList.add(exercise);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (exerciseList.isEmpty())
            callBack.onDataNotAvailable();
        else
            callBack.onExerciseListLoaded(exerciseList);
        callBack.onExerciseListLoaded(exerciseList);
    }

    @Override
    public void getExercisesByCategory(int exerciseCategoryId, @NonNull LoadExerciseListCallBack callBack) {
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.Exercise._ID,
                TableContent.Exercise.COLUMN_CATEGORY_ID,
                TableContent.Exercise.COLUMN_TITLE,
                TableContent.Exercise.COLUMN_TAG,
                TableContent.Exercise.COLUMN_IMAGES,
                TableContent.Exercise.COLUMN_DESCRIPTIONS,
                TableContent.Exercise.COLUMN_DAY
        };
        String selection = TableContent.Exercise.COLUMN_CATEGORY_ID + " = ?";
        String [] selectionArgs = {String.valueOf(exerciseCategoryId)};

        Cursor c = db.query(TableContent.Exercise.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise._ID));
                int categoryId = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_CATEGORY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TITLE));
                String tag = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TAG));
                String images = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_IMAGES));
                String descs = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DESCRIPTIONS));
                int day = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DAY));

                Exercise exercise = new Exercise(id, categoryId, title, tag, images, descs, day);
                exerciseList.add(exercise);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (exerciseList.isEmpty())
            callBack.onDataNotAvailable();
        else
            callBack.onExerciseListLoaded(exerciseList);
        callBack.onExerciseListLoaded(exerciseList);
    }
}
