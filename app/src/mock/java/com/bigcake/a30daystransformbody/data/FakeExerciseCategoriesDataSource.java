package com.bigcake.a30daystransformbody.data;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.source.ExerciseCategoriesDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class FakeExerciseCategoriesDataSource implements ExerciseCategoriesDataSource {
    private static FakeExerciseCategoriesDataSource mInstance;
    private List<ExerciseCategory> exerciseList;

    public static synchronized FakeExerciseCategoriesDataSource getInstance() {
        if (mInstance == null)
            mInstance = new FakeExerciseCategoriesDataSource();
        return mInstance;
    }

    private FakeExerciseCategoriesDataSource() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new ExerciseCategory("Push f", "Description 1"));
        exerciseList.add(new ExerciseCategory("Pull f", "Description 2"));
        exerciseList.add(new ExerciseCategory("Led & Glute f", "Description 2"));
        exerciseList.add(new ExerciseCategory("Core f", "Description 2"));
    }

    @Override
    public void getExercises(@NonNull LoadExerciseCartegoryCallBack callBack) {
        callBack.onExerciseCategoryLoaded(exerciseList);
    }
}
