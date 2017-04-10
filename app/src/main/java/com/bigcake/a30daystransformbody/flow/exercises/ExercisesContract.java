package com.bigcake.a30daystransformbody.flow.exercises;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.Exercise;

import java.util.List;

/**
 * Created by Big Cake on 4/10/2017
 */

public interface ExercisesContract {
    interface View extends BaseView<Presenter> {
        void displayExercises(List<Exercise> exerciseList);
    }
    interface Presenter extends BasePresenter{

    }
}
