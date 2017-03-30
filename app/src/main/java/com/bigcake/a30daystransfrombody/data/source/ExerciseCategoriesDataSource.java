package com.bigcake.a30daystransfrombody.data.source;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransfrombody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseCategoriesDataSource {
    interface LoadExercisesCallBack {
        void onExercisesLoaded(List<ExerciseCategory> exerciseList);
        void onDataNotAvailable();
    }

    void getExercises(@NonNull LoadExercisesCallBack callBack);
}