package com.bigcake.a30daystransformbody.flow.weightmanager;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.Weight;
import com.github.mikephil.charting.data.Entry;

import java.util.List;
import java.util.Map;

/**
 * Created by kiethuynh on 08/05/2017
 */

public interface WeightManagerContract {
    interface View extends BaseView<Presenter> {
        void showWeightChart(List<Entry> entries, List<String> labels);
        void showUpdateWeightForm(String lastWeight);
    }
    interface Presenter extends BasePresenter {
        void updateWeight(float weight);
        void insertWeight(float weight);
        Weight getCurrentWeight();
    }
}
