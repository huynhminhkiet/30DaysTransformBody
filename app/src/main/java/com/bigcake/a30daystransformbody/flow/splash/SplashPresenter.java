package com.bigcake.a30daystransformbody.flow.splash;

import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;
import com.bigcake.a30daystransformbody.data.source.harddata.HardData;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.manager.AssetsManager;

/**
 * Created by kiethuynh on 16/05/2017
 */

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;
    private ExerciseRepository mExerciseRepository;
    private HardData mHardData;

    public SplashPresenter(SplashContract.View mView, ExerciseRepository mExerciseRepository, HardData hardData) {
        this.mView = mView;
        this.mExerciseRepository = mExerciseRepository;
        this.mHardData = hardData;
    }


    @Override
    public void start() {
        mExerciseRepository.checkFullData(new ExerciseDataSource.CheckFullDataCallback() {
            @Override
            public void onFull() {
                mView.gotoHomeScreen();
            }

            @Override
            public void onNotFull() {
                loadDataFromJsonFile();
            }
        });
    }

    private void loadDataFromJsonFile() {
        mView.showProgressBar(true);
        mHardData.saveExercises(new HardData.SaveExercisesCallback() {
            @Override
            public void onSuccess() {
                mView.showProgressBar(false);
                mView.gotoHomeScreen();
            }

            @Override
            public void onError() {
                mView.showProgressBar(false);
            }
        });
    }
}
