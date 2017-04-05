package com.bigcake.a30daystransformbody.data.repository;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.source.ExerciseCategoriesDataSource;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategoriesRepository implements ExerciseCategoriesDataSource {
    private static ExerciseCategoriesRepository mInstance = null;

    private ExerciseCategoriesDataSource mExerciseCategoriesDataSource;

    private ExerciseCategoriesRepository(ExerciseCategoriesDataSource exerciseCategoriesLocalDataSource) {
        mExerciseCategoriesDataSource = exerciseCategoriesLocalDataSource;
    }

    public static synchronized ExerciseCategoriesRepository getInstance(ExerciseCategoriesDataSource exerciseCategoriesDataSource) {
        if (mInstance == null)
            mInstance = new ExerciseCategoriesRepository(exerciseCategoriesDataSource);
        return mInstance;
    }

    @Override
    public void getExercises(@NonNull final LoadExerciseCartegoryCallBack callBack) {
        mExerciseCategoriesDataSource.getExercises(new LoadExerciseCartegoryCallBack() {
            @Override
            public void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList) {
                callBack.onExerciseCategoryLoaded(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
