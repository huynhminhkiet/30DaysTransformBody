package com.bigcake.a30daystransformbody.flow.weightmanager;

import com.bigcake.a30daystransformbody.data.Weight;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.utils.Utils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kiethuynh on 08/05/2017
 */

public class WeightManagerPresenter implements WeightManagerContract.Presenter {
    private WeightManagerContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private Weight mCurrentWeight;

    public WeightManagerPresenter(WeightManagerContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        mChallengeRepository = challengeRepository;
    }

    @Override
    public void updateWeight(float weight) {
        mCurrentWeight.setWeight(weight);
        mChallengeRepository.updateWeight(mCurrentWeight, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void insertWeight(float weight) {
        mCurrentWeight = new Weight(new Date(), weight);
        mChallengeRepository.insertWeight(mCurrentWeight, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public Weight getCurrentWeight() {
        mChallengeRepository.getLastWeight(new ChallengeDataSource.GetLastWeightCallback() {
            @Override
            public void onSuccess(Weight weight) {
                mCurrentWeight = weight;
            }

            @Override
            public void onError() {
            }
        });
        return mCurrentWeight;
    }

    @Override
    public void start() {
        mChallengeRepository.getAllWeight(new ChallengeDataSource.GetAllWeightCallback() {
            @Override
            public void onSuccess(List<Weight> weightList) {
                setChartData(weightList);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setChartData(List<Weight> weightList) {
        List<Entry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        if (weightList != null && weightList.get(0) != null) {
            entries.add(new Entry(weightList.get(0).getWeight(), 0));
            labels.add(Utils.formatDateChart(weightList.get(0).getDate()));
        }

        if (weightList.size() > 1)
            for (int i = 1; i < weightList.size(); i++) {
                Weight w = weightList.get(i);
                Weight wBefore = weightList.get(i - 1);
                entries.add(new Entry(weightList.get(i).getWeight(),
                        (int) Utils.convertTimeToMillis(Utils.getZeroTimeDate(w.getDate()))));
                labels.add(Utils.formatDateChart(w.getDate()));
        }

        mView.showWeightChart(entries, labels);
    }
}
