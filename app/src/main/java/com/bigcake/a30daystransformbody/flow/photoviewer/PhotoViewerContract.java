package com.bigcake.a30daystransformbody.flow.photoviewer;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;

/**
 * Created by Big Cake on 5/3/2017
 */

public interface PhotoViewerContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void onShowPhoto(byte [] image);
    }
}
