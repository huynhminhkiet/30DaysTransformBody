package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.flow.exercises.ExercisesContract;

/**
 * Created by Big Cake on 4/22/2017
 */

public interface CameraContract {
    interface View extends BaseView<ExercisesContract.Presenter> {
        void onCaptureFinished(ChallengeDay challengeDay);
        void onCaptureFail();
    }
    interface Presenter extends BasePresenter {
        void saveImage(ChallengeDay challengeDay, byte [] bitmap);
    }
}
