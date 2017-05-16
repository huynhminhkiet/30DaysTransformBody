package com.bigcake.a30daystransformbody.flow.splash;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;

/**
 * Created by kiethuynh on 16/05/2017
 */

public class SplashContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void gotoHomeScreen();
        void showProgressBar(boolean b);
    }
}
