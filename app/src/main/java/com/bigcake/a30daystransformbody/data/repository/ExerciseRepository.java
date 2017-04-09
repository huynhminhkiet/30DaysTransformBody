package com.bigcake.a30daystransformbody.data.repository;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseRepository implements ExerciseDataSource {
    private static ExerciseRepository mInstance = null;

    private ExerciseDataSource mExerciseDataSource;

    private ExerciseRepository(ExerciseDataSource exerciseDataSource) {
        mExerciseDataSource = exerciseDataSource;
    }

    public static synchronized ExerciseRepository getInstance(ExerciseDataSource exerciseDataSource) {
        if (mInstance == null)
            mInstance = new ExerciseRepository(exerciseDataSource);
        return mInstance;
    }

    @Override
    public void getExerciseCategorise(@NonNull final LoadExerciseCategoryCallBack callBack) {
        mExerciseDataSource.getExerciseCategorise(new LoadExerciseCategoryCallBack() {
            @Override
            public void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList) {
                callBack.onExerciseCategoryLoaded(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getExercise(@NonNull final LoadExerciseCallBack callBack) {
        mExerciseDataSource.getExercise(new LoadExerciseCallBack() {
            @Override
            public void onExerciseLoaded(Exercise exercise) {
                callBack.onExerciseLoaded(exercise);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
