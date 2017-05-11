package com.bigcake.a30daystransformbody.flow.weightmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.Weight;
import com.bigcake.a30daystransformbody.interfaces.UpdateWeightDialogCallback;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bigcake.a30daystransformbody.utils.Utils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.Date;
import java.util.List;

/**
 * Created by kiethuynh on 08/05/2017
 */

public class WeightManagerFragment extends BaseFragment implements WeightManagerContract.View {
    private LineChart lineChart;
    private Button btnUpdateWeight;
    private WeightManagerContract.Presenter mPresenter;
    private Weight mCurrentWeight;
    private List<Entry> mEntries;
    private List<String> mLabels;


    public static WeightManagerFragment newInstance() {
        return new WeightManagerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new WeightManagerPresenter(this, Injection.provideChallengeRepository(getContext()));
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
    public void showUpdateWeightForm(String lastWeight) {
        UpdateWeightDialog dialog = UpdateWeightDialog.create(getContext(), new UpdateWeightDialogCallback() {
            @Override
            public void onWeightSubmitted(float weight) {
                if (!Utils.getBooleanPrefs(getActivity(), Constants.PREFS_TODAY_WEIGHT_UPDATED, false)) {
                    mPresenter.insertWeight(weight);
                    Utils.putBooleanPrefs(getActivity(), Constants.PREFS_TODAY_WEIGHT_UPDATED, true);
                } else {
                    mPresenter.updateWeight(weight);
                }
            }
        });
        dialog.setLastWeight(lastWeight);
        dialog.show();
    }

    private void initViews(View view) {
        lineChart = (LineChart) view.findViewById(R.id.line_chart);
        btnUpdateWeight = (Button) view.findViewById(R.id.btn_update_weight);
        btnUpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentWeight = mPresenter.getCurrentWeight();
                showUpdateWeightForm(mCurrentWeight == null ? "" : String.valueOf(mCurrentWeight.getWeight()));
            }
        });
    }

    @Override
    public void setPresenter(WeightManagerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showWeightChart(List<Entry> entries, List<String> labels) {
        mEntries = entries;
        mLabels = labels;
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setStartAtZero(false);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setStartAtZero(false);

        LineDataSet dataset = new LineDataSet(mEntries, getString(R.string.gen_gragh_desc));
        dataset.setColor(getContext().getResources().getColor(R.color.colorOrange));
        dataset.setCircleColor(getContext().getResources().getColor(R.color.colorDarkGreen));
        dataset.setLineWidth(3);
        LineData data = new LineData(mLabels, dataset);
        lineChart.setDescription(null);
        lineChart.zoom(entries.size() / 5, 1, 0, 0);
        lineChart.moveViewToX(entries.size());
        lineChart.setData(data);
    }
}
