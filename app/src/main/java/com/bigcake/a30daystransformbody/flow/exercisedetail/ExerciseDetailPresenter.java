package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

/**
 * Created by Big Cake on 4/9/2017
 */

public class ExerciseDetailPresenter implements ExerciseDetailContract.Presenter {
    private ExerciseDetailContract.View mView;
    private ExerciseRepository exerciseRository;

    public ExerciseDetailPresenter(@NonNull ExerciseDetailContract.View mView,@NonNull ExerciseRepository exerciseRository) {
        this.mView = mView;
        this.exerciseRository = exerciseRository;
    }

    @Override
    public void start() {
        exerciseRository.getExercise(new ExerciseDataSource.LoadExerciseCallBack() {
            @Override
            public void onExerciseLoaded(Exercise exercise) {
                mView.displayExercise(exercise);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
