package com.bigcake.a30daystransfrombody.data.source.local;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransfrombody.data.ExerciseCategory;
import com.bigcake.a30daystransfrombody.data.source.ExerciseCategoriesDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategoriesLocalDataSource implements ExerciseCategoriesDataSource {

    private List<ExerciseCategory> exerciseList;

    public ExerciseCategoriesLocalDataSource() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new ExerciseCategory("Local 1", "Description 1"));
        exerciseList.add(new ExerciseCategory("Local 2", "Description 2"));
    }

    @Override
    public void getExercises(@NonNull ExerciseCategoriesDataSource.LoadExercisesCallBack callBack) {
        callBack.onExercisesLoaded(exerciseList);
    }
}
