package com.bigcake.a30daystransformbody.flow.main;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.data.Weight;

/**
 * Created by Big Cake on 5/11/2017
 */

public interface MainContract {
    interface View {
        void resetWeightTracker();
        void trackWeight(Weight weight);
    }

    interface Presenter extends BasePresenter {
    }
}
