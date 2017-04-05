package com.bigcake.a30daystransformbody.data.source.local;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.source.ExerciseCategoriesDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategoriesLocalDataSource implements ExerciseCategoriesDataSource {

    private List<ExerciseCategory> exerciseList;

    public ExerciseCategoriesLocalDataSource() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new ExerciseCategory("Push", "Description 1"));
        exerciseList.add(new ExerciseCategory("Pull", "Description 2"));
        exerciseList.add(new ExerciseCategory("Led & Glute", "Description 2"));
        exerciseList.add(new ExerciseCategory("Core", "Description 2"));
    }

    @Override
    public void getExercises(@NonNull LoadExerciseCartegoryCallBack callBack) {
        callBack.onExerciseCategoryLoaded(exerciseList);
    }
}
