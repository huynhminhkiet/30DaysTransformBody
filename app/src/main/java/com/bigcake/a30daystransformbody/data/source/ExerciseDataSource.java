package com.bigcake.a30daystransformbody.data.source;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseDataSource {
    interface LoadExerciseListCallBack {
        void onExerciseListLoaded(List<Exercise> exerciseList);
        void onDataNotAvailable();
    }

    interface LoadExerciseCategoryCallBack {
        void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList);
        void onDataNotAvailable();
    }

    interface LoadExerciseCallBack {
        void onExerciseLoaded(Exercise exercise);
        void onDataNotAvailable();
    }

    interface DefaultCallback {
        void onSuccess();
        void onError();
    }

    interface CheckFullDataCallback {
        void onFull();
        void onNotFull();
    }

    void getExerciseCategorise(@NonNull LoadExerciseCategoryCallBack callBack);
    void getExercise(int exerciseId, @NonNull LoadExerciseCallBack callBack);
    void getExercisesByCategory(int exerciseCategoryId, @NonNull LoadExerciseListCallBack callBack);
    void getExercisesOnProgress(@NonNull LoadExerciseListCallBack callBack);
    void saveExercise(@NonNull Exercise exercise, @NonNull DefaultCallback callback);
    void checkFullData(@NonNull CheckFullDataCallback callback);
    void updateExercise(Exercise exercise, @NonNull DefaultCallback callback);
}
