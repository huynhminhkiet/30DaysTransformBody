package com.bigcake.a30daystransformbody.data.source.local;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.R;
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
