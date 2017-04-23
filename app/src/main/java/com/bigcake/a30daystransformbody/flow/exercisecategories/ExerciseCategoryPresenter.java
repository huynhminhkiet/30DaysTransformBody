package com.bigcake.a30daystransformbody.flow.exercisecategories;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

import java.util.List;

/**
 * Created by Big Cake on 4/1/2017.
 */

public class ExerciseCategoryPresenter implements ExerciseCategoriesContract.Presenter {
    private ExerciseCategoriesContract.View mExerciseCatView;
    private ExerciseRepository mExerciseRepository;
    private ExerciseCategory mCurrentExerciseCategory;

    public ExerciseCategoryPresenter(@NonNull ExerciseCategoriesContract.View exerciseCatView,
                                     @NonNull ExerciseRepository exerciseRepository) {
        mExerciseCatView = exerciseCatView;
        mExerciseRepository = exerciseRepository;

        exerciseCatView.setPresenter(this);
    }

    @Override
    public void start() {
        mExerciseRepository.getExerciseCategorise(new ExerciseDataSource.LoadExerciseCategoryCallBack() {
            @Override
            public void onExerciseCategoryLoaded(List<ExerciseCategory> exerciseList) {
                mCurrentExerciseCategory = exerciseList.get(0);
                mExerciseCatView.displayCategories(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void openExercises() {
        mExerciseCatView.showExercises(mCurrentExerciseCategory);
    }

    @Override
    public void setCurrentExerciseCategory(ExerciseCategory exerciseCategory) {
        mCurrentExerciseCategory = exerciseCategory;
    }
}
