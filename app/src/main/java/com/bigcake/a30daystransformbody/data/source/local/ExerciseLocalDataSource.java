package com.bigcake.a30daystransformbody.data.source.local;

import android.content.Context;
import android.database.Cursor;
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
    public void getExercise(@NonNull LoadExerciseCallBack callBack) {

        Exercise exercise = new Exercise();
        callBack.onExerciseLoaded(exercise);
    }

    @Override
    public void saveExercise(@NonNull Exercise exercise, @NonNull DefaultCallback callback) {

    }

    @Override
    public void getExerciseList(@NonNull LoadExerciseListCallBack callBack) {
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                TableContent.Exercise._ID,
                TableContent.Exercise.COLUMN_CATEGORY_ID,
                TableContent.Exercise.COLUMN_TITLE,
                TableContent.Exercise.COLUMN_TAG,
                TableContent.Exercise.COLUMN_IMAGES,
                TableContent.Exercise.COLUMN_DESCRIPTIONS
        };

        Cursor c = db.query(TableContent.Exercise.TABLE_NAME,
                projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise._ID));
                int categoryId = c.getInt(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_CATEGORY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TITLE));
                String tag = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_TAG));
                String images = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_IMAGES));
                String descs = c.getString(c.getColumnIndexOrThrow(TableContent.Exercise.COLUMN_DESCRIPTIONS));

                Exercise exercise = new Exercise(id, categoryId, title, tag, images, descs);
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
