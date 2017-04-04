package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransfrombody.data.ExerciseCategory;
import com.bigcake.a30daystransfrombody.data.repository.ExerciseCategoriesRepository;
import com.bigcake.a30daystransfrombody.data.source.ExerciseCategoriesDataSource;

import java.util.List;

/**
 * Created by Big Cake on 4/1/2017.
 */

public class ExerciseCategoryPresenter implements ExerciseCategoriesContract.Presenter {
    private ExerciseCategoriesContract.View mExerciseCatView;
    private ExerciseCategoriesRepository mExerciseCategoriesRepository;
    private ExerciseCategory mCurrentExerciseCategory;

    public ExerciseCategoryPresenter(@NonNull ExerciseCategoriesContract.View exerciseCatView,
                                     @NonNull ExerciseCategoriesRepository exerciseCategoriesRepository) {
        mExerciseCatView = exerciseCatView;
        mExerciseCategoriesRepository = exerciseCategoriesRepository;

        exerciseCatView.setPresenter(this);
    }

    @Override
    public void start() {
        mExerciseCategoriesRepository.getExercises(new ExerciseCategoriesDataSource.LoadExerciseCartegoryCallBack() {
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
