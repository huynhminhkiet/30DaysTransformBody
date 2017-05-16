package com.bigcake.a30daystransformbody.data.source.repository;

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
    public void getExercise(int exerciseId, @NonNull final LoadExerciseCallBack callBack) {
        mExerciseDataSource.getExercise(exerciseId, new LoadExerciseCallBack() {
            @Override
            public void onExerciseLoaded(Exercise exercise) {
                callBack.onExerciseLoaded(exercise);
            }

            @Override
            public void onDataNotAvailable() {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveExercise(@NonNull Exercise exercise, @NonNull final DefaultCallback callback) {
        mExerciseDataSource.saveExercise(exercise, new DefaultCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getExercisesOnProgress(@NonNull final LoadExerciseListCallBack callBack) {
        mExerciseDataSource.getExercisesOnProgress(new LoadExerciseListCallBack() {
            @Override
            public void onExerciseListLoaded(List<Exercise> exerciseList) {
                callBack.onExerciseListLoaded(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getExercisesByCategory(int exerciseCategoryId, @NonNull final LoadExerciseListCallBack callBack) {
        mExerciseDataSource.getExercisesByCategory(exerciseCategoryId, new LoadExerciseListCallBack() {
            @Override
            public void onExerciseListLoaded(List<Exercise> exerciseList) {
                callBack.onExerciseListLoaded(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void checkFullData(@NonNull final CheckFullDataCallback callback) {
        mExerciseDataSource.checkFullData(new CheckFullDataCallback() {
            @Override
            public void onFull() {
                callback.onFull();
            }

            @Override
            public void onNotFull() {
                callback.onNotFull();
            }
        });
    }

    @Override
    public void updateExercise(Exercise exercise, @NonNull final DefaultCallback callback) {
        mExerciseDataSource.updateExercise(exercise, new DefaultCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
