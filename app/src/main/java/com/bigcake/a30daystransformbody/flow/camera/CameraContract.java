package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.flow.exercises.ExercisesContract;

/**
 * Created by Big Cake on 4/22/2017
 */

public interface CameraContract {
    interface View extends BaseView<ExercisesContract.Presenter> {

    }
    interface Presenter extends BasePresenter {
        void saveImage(Bitmap bitmap);
    }
}
