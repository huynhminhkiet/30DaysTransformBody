package com.bigcake.a30daystransformbody.flow.weightmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

/**
 * Created by kiethuynh on 08/05/2017
 */

public class WeightManagerFragment extends BaseFragment implements WeightManagerContract.View {
    private LineChart lineChart;
    private WeightManagerContract.Presenter mPresenter;

    public static WeightManagerFragment newInstance() {
        return new WeightManagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_manager, container, false);
        initViews(view);
        mPresenter.start();
        return view;
    }

    private void initViews(View view) {
        lineChart = (LineChart) view.findViewById(R.id.line_chart);
    }

    @Override
    public void setPresenter(WeightManagerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showWeightChart(List<Entry> entries, List<String> labels) {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setStartAtZero(false);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setStartAtZero(false);

        LineDataSet dataset = new LineDataSet(entries, "Weight");
        LineData data = new LineData(labels, dataset);
        lineChart.setDescription(null);
        lineChart.setData(data);
    }
}
