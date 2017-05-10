package com.bigcake.a30daystransformbody.flow.weightmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.interfaces.UpdateWeightDialogCallback;
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
    private Button btnUpdateWeight;
    private WeightManagerContract.Presenter mPresenter;

    public static WeightManagerFragment newInstance() {
        return new WeightManagerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new WeightManagerPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_manager, container, false);
        initViews(view);
        mPresenter.start();
        return view;
    }

    @Override
    public void showUpdateWeightForm() {
        UpdateWeightDialog dialog = UpdateWeightDialog.create(getContext(), new UpdateWeightDialogCallback() {
            @Override
            public void onWeightSubmitted(int weight) {
                mPresenter.updateWeight(weight);
            }
        });
        dialog.show();
    }

    private void initViews(View view) {
        lineChart = (LineChart) view.findViewById(R.id.line_chart);
        btnUpdateWeight = (Button) view.findViewById(R.id.btn_update_weight);
        btnUpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateWeightForm();
            }
        });
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
        dataset.setColor(getContext().getResources().getColor(R.color.colorOrange));
        dataset.setCircleColor(getContext().getResources().getColor(R.color.colorDarkGreen));
        dataset.setLineWidth(3);
        LineData data = new LineData(labels, dataset);
        lineChart.setDescription(null);
        lineChart.zoom(entries.size() / 5, 1, 0, 0);
        lineChart.moveViewToX(entries.size());
        lineChart.setData(data);
    }
}
