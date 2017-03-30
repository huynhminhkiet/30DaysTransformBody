package com.bigcake.a30daystransfrombody.data.repository;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransfrombody.data.ExerciseCategory;
import com.bigcake.a30daystransfrombody.data.source.ExerciseCategoriesDataSource;
import com.bigcake.a30daystransfrombody.data.source.local.ExerciseCategoriesLocalDataSource;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategoriesRepository implements ExerciseCategoriesDataSource {
    private static ExerciseCategoriesRepository mInstance = null;

    private ExerciseCategoriesDataSource mExerciseCategoriesLocalDataSource;

    private ExerciseCategoriesRepository(ExerciseCategoriesDataSource exerciseCategoriesLocalDataSource) {
        mExerciseCategoriesLocalDataSource = exerciseCategoriesLocalDataSource;
    }

    public static synchronized ExerciseCategoriesRepository getInstance(ExerciseCategoriesDataSource exerciseCategoriesLocalDataSource) {
        if (mInstance == null)
            mInstance = new ExerciseCategoriesRepository(exerciseCategoriesLocalDataSource);
        return mInstance;
    }

    @Override
    public void getExercises(@NonNull final LoadExercisesCallBack callBack) {
        mExerciseCategoriesLocalDataSource.getExercises(new LoadExercisesCallBack() {
            @Override
            public void onExercisesLoaded(List<ExerciseCategory> exerciseList) {
                callBack.onExercisesLoaded(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
