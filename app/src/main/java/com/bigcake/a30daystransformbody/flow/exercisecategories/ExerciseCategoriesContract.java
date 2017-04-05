package com.bigcake.a30daystransformbody.flow.exercisecategories;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseCategoriesContract {
    interface View extends BaseView<Presenter> {
        void displayCategories(List<ExerciseCategory> exerciseCategoryList);
        void showExercises(ExerciseCategory exerciseCategory);
    }

    interface Presenter extends BasePresenter {
        void openExercises();
        void setCurrentExerciseCategory(ExerciseCategory exerciseCategory);
    }
}
