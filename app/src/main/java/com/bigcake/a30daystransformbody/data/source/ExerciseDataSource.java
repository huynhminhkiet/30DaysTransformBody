package com.bigcake.a30daystransformbody.data.source;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseDataSource {
    interface LoadExerciseCategoryCallBack {
        void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList);
        void onDataNotAvailable();
    }

    interface LoadExerciseCallBack {
        void onExerciseLoaded(Exercise exercise);
        void onDataNotAvailable();
    }

    void getExerciseCategorise(@NonNull LoadExerciseCategoryCallBack callBack);
    void getExercise(@NonNull LoadExerciseCallBack callBack);
}
