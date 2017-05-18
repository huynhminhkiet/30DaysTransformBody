package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.support.annotation.NonNull;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeDayPresenter implements ChallengeProgressContract.Presenter {
    private ChallengeProgressContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private ExerciseRepository mExerciseRepository;
    private List<ChallengeDay> mChallengeDayList;
    private Exercise mExercise;

    public ChallengeDayPresenter(@NonNull ChallengeProgressContract.View view,
                                 @NonNull ChallengeRepository challengeRepository,
                                 @NonNull ExerciseRepository exerciseRepository,
                                 @NonNull Exercise exercise) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
        this.mExerciseRepository = exerciseRepository;
        this.mExercise = exercise;
    }

    @Override
    public void start() {
        if (mChallengeDayList == null)
            mChallengeRepository.getChallengeDays(mExercise.getId(), new ChallengeDataSource.LoadChallengeDaysCallBack() {
                @Override
                public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                    mChallengeDayList = challengeDayList;
                    mView.displayChallengeDays(mChallengeDayList);
                    mView.displayProgressBar(mExercise.getDay() - 1);
                }

                @Override
                public void onError() {

                }
            });

        else {
            mView.displayChallengeDays(mChallengeDayList);
            mView.displayProgressBar(mExercise.getDay() - 1);
        }
    }

    @Override
    public void updateProgress(final int day) {
        mExercise.setDay(day);
        mExerciseRepository.updateExercise(mExercise, new ExerciseDataSource.DefaultCallback() {
            @Override
            public void onSuccess() {
                mView.displayProgressBar(day - 1);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void updateDataOnDatabase(ChallengeDay challengeDay) {
        mChallengeRepository.updateChallengeDay(challengeDay, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void deleteChallenge() {
        mExercise.setDay(0);
        mChallengeRepository.deleteAllChallengeDayByExercise(mExercise.getId(), new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {
                mExerciseRepository.updateExercise(mExercise, new ExerciseDataSource.DefaultCallback() {
                    @Override
                    public void onSuccess() {
                        mView.closeChallenge();
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
    }

    @Override
    public void resetChallenge() {
        mExercise.setDay(1);
        mExerciseRepository.updateExercise(mExercise, new ExerciseDataSource.DefaultCallback() {
            @Override
            public void onSuccess() {
                mChallengeRepository.resetChallengeDayByExercise(mExercise.getId(), new ChallengeDataSource.ChallengeCallBack() {
                    @Override
                    public void onSuccess() {
                        mChallengeDayList = null;
                        start();
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
    }

    @Override
    public void challengeDayClick(ChallengeDay challengeDay) {
        if (challengeDay.getStatus() != ChallengeDay.STATUS_IN_PROGRESS) {
            mView.openCamera(challengeDay);
        }
    }
}
