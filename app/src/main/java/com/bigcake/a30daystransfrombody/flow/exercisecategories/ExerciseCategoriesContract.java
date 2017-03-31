package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import com.bigcake.a30daystransfrombody.base.BasePresenter;
import com.bigcake.a30daystransfrombody.base.BaseView;
import com.bigcake.a30daystransfrombody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public interface ExerciseCategoriesContract {
    interface View extends BaseView<Presenter> {
        void displayCategories(List<ExerciseCategory> exerciseCategoryList);
        void displayExerciseCategoryImages();
    }

    interface Presenter extends BasePresenter {
    }
}
