package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;

/**
 * Created by Big Cake on 4/9/2017
 */

public class ExerciseDetailPresenter implements ExerciseDetailContract.Presenter {
    private ExerciseDetailContract.View mView;
    private ExerciseRepository mExerciseRository;
    private ChallengeRepository mChallengeRepository;
    private Exercise mExercise;

    public ExerciseDetailPresenter(@NonNull ExerciseDetailContract.View mView,
                                   @NonNull ExerciseRepository mExerciseRository,
                                   @NonNull ChallengeRepository challengeRepository,
                                   Exercise exercise) {
        this.mView = mView;
        this.mExerciseRository = mExerciseRository;
        this.mChallengeRepository = challengeRepository;
        mExercise = exercise;
    }

    @Override
    public void start() {
        mExerciseRository.getExercise(mExercise.getId(), new ExerciseDataSource.LoadExerciseCallBack() {
            @Override
            public void onExerciseLoaded(Exercise exercise) {
                mView.displayExercise(exercise);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void openChallenge() {
        if (mExercise.getDay() == 0) {
            mChallengeRepository.generateChallengesDay(mExercise.getId(), new ChallengeDataSource.ChallengeCallBack() {
                @Override
                public void onSuccess() {
                    mExercise.setDay(1);
                    mExerciseRository.updateExercise(mExercise, new ExerciseDataSource.DefaultCallback() {
                        @Override
                        public void onSuccess() {
                            mView.openChallengeScreen(mExercise);
                            mView.finishActivityAndUpdateData(mExercise);
                        }

                        @Override
                        public void onError() {

                        }
                    });
                }

                @Override
                public void onError() {

                }
            });
        } else {
            mView.openChallengeScreen(mExercise);
        }
    }
}

