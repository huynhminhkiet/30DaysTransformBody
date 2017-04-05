package com.bigcake.a30daystransformbody.data.source;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseCategoriesDataSource {
    interface LoadExerciseCartegoryCallBack {
        void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList);
        void onDataNotAvailable();
    }

    void getExercises(@NonNull LoadExerciseCartegoryCallBack callBack);
}
