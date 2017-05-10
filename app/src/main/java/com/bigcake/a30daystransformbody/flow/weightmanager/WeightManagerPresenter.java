package com.bigcake.a30daystransformbody.flow.weightmanager;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 08/05/2017
 */

public class WeightManagerPresenter implements WeightManagerContract.Presenter {
    private WeightManagerContract.View mView;

    public WeightManagerPresenter(WeightManagerContract.View view) {
        this.mView = view;
    }

    @Override
    public void updateWeight(int weight) {

    }

    @Override
    public void start() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(50.1f, 0));
        entries.add(new Entry(50.5f, 1));
        entries.add(new Entry(50.6f, 2));
        entries.add(new Entry(51, 3));
        entries.add(new Entry(51.9f, 4));
        entries.add(new Entry(50.1f, 5));
        entries.add(new Entry(50.5f, 6));
        entries.add(new Entry(50.6f, 7));
        entries.add(new Entry(51, 8));
        entries.add(new Entry(51.9f, 9));
        entries.add(new Entry(50.1f, 10));
        entries.add(new Entry(50.5f, 11));
        entries.add(new Entry(50.6f, 12));
        entries.add(new Entry(51, 13));
        entries.add(new Entry(51.9f, 14));
        entries.add(new Entry(50.1f, 15));
        entries.add(new Entry(50.5f, 16));
        entries.add(new Entry(50.6f, 17));
        entries.add(new Entry(51, 18));
        entries.add(new Entry(51.9f, 19));

        List<String> labels = new ArrayList<>();
        labels.add("a");
        labels.add("b");
        labels.add("c");
        labels.add("d");
        labels.add("e");
        labels.add("f");
        labels.add("a");
        labels.add("b");
        labels.add("c");
        labels.add("d");

        labels.add("e");
        labels.add("a");
        labels.add("b");
        labels.add("c");
        labels.add("d");
        labels.add("e");
        labels.add("f");
        labels.add("a");
        labels.add("b");
        labels.add("c");

        mView.showWeightChart(entries, labels);
    }
}
