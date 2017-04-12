package com.bigcake.a30daystransformbody.data;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class FakeExerciseDataSource implements ExerciseDataSource {
    private static FakeExerciseDataSource mInstance;
    private List<ExerciseCategory> exerciseList;

    public static synchronized FakeExerciseDataSource getInstance() {
        if (mInstance == null)
            mInstance = new FakeExerciseDataSource();
        return mInstance;
    }

    private FakeExerciseDataSource() {
        exerciseList = new ArrayList<>();
        exerciseList.add(new ExerciseCategory("Push f", "Description 1"));
        exerciseList.add(new ExerciseCategory("Pull f", "Description 2"));
        exerciseList.add(new ExerciseCategory("Led & Glute f", "Description 2"));
        exerciseList.add(new ExerciseCategory("Core f", "Description 2"));
    }

    @Override
    public void getExerciseCategorise(@NonNull LoadExerciseCategoryCallBack callBack) {
        callBack.onExerciseCategoryLoaded(exerciseList);
    }

    @Override
    public void getExercise(@NonNull LoadExerciseCallBack callBack) {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ei_pull_1);
        images.add(R.drawable.ei_pull_2);
        images.add(R.drawable.ei_pull_3);

        List<String> descriptions = new ArrayList<>();
        descriptions.add("With your legs straight and feet spread a few inches apart, bend over\n" +
                "at the waist and put your hands on the ground, about three to four feet\n" + "in front of your toes as you would for Classic Push Ups");

        descriptions.add("With your legs straight and feet spread a few inches apart, bend over\n" +
                "at the waist and put your hands on the ground, about three to four feet\n" +
                "in front of your toes as you would for Classic Push Ups");
        descriptions.add("With your legs straight and feet spread a few inches apart, bend over\n" +
                "at the waist and put your hands on the ground, about three to four feet\n" +
                "in front of your toes as you would for Classic Push Ups");

        Exercise exercise = new Exercise(123, 123, "Dive Bombers", "pectorals, triceps, deltoids, core (3-4)",
                1, images, descriptions);
        callBack.onExerciseLoaded(exercise);
    }

    @Override
    public void getExerciseList(@NonNull LoadExerciseListCallBack callBack) {
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise(123, 123, "Dive Bombers", "pectorals, triceps, deltoids, core (3-4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Pec Flies", "pectorals, core, shoulders (4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Seated Dips", "triceps (1-3)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Dive Bombers", "pectorals, triceps, deltoids, core (3-4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Pec Flies", "pectorals, core, shoulders (4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Seated Dips", "triceps (1-3)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Dive Bombers", "pectorals, triceps, deltoids, core (3-4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Pec Flies", "pectorals, core, shoulders (4)",
                1, null, null));
        exerciseList.add(new Exercise(123, 123, "Seated Dips", "triceps (1-3)",
                1, null, null));
        callBack.onExerciseListLoaded(exerciseList);

    }
}
