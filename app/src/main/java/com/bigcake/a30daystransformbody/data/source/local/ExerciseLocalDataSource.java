package com.bigcake.a30daystransformbody.data.source.local;

import android.support.annotation.NonNull;

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
    private List<ExerciseCategory> exerciseCategoryList;
    private List<Exercise> exerciseList;

    public static synchronized ExerciseLocalDataSource getInstance() {
        if (mInstance == null)
            mInstance = new ExerciseLocalDataSource();
        return mInstance;
    }

    private ExerciseLocalDataSource() {
        exerciseCategoryList = new ArrayList<>();
        exerciseCategoryList.add(new ExerciseCategory("Push", "Description 1"));
        exerciseCategoryList.add(new ExerciseCategory("Pull", "Description 2"));
        exerciseCategoryList.add(new ExerciseCategory("Led & Glute", "Description 2"));
        exerciseCategoryList.add(new ExerciseCategory("Core", "Description 2"));
    }

    @Override
    public void getExerciseCategorise(@NonNull LoadExerciseCategoryCallBack callBack) {
        callBack.onExerciseCategoryLoaded(exerciseCategoryList);
    }

    @Override
    public void getExercise(@NonNull LoadExerciseCallBack callBack) {

    }

    @Override
    public void getExerciseList(@NonNull LoadExerciseListCallBack callBack) {

    }
}
