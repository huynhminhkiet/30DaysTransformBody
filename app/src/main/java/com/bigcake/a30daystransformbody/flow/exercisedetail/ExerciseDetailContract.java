package com.bigcake.a30daystransformbody.flow.exercisedetail;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.Exercise;

/**
 * Created by Big Cake on 4/9/2017
 */

public interface ExerciseDetailContract {
    interface View extends BaseView<Presenter> {
        void displayExercise(Exercise exercise);
    }

    interface Presenter extends BasePresenter {
    }
}
