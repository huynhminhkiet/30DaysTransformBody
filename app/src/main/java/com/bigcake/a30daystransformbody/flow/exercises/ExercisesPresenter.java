package com.bigcake.a30daystransformbody.flow.exercises;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

import java.util.List;

/**
 * Created by Big Cake on 4/10/2017
 */

public class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mView;
    private ExerciseRepository mExerciseRepository;

    public ExercisesPresenter(@NonNull ExercisesContract.View view, @NonNull ExerciseRepository exerciseRepository) {
        this.mView = view;
        this.mExerciseRepository = exerciseRepository;
    }

    @Override
    public void start() {
        mExerciseRepository.getExerciseList(new ExerciseDataSource.LoadExerciseListCallBack() {
            @Override
            public void onExerciseListLoaded(List<Exercise> exerciseList) {
                mView.displayExercises(exerciseList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
