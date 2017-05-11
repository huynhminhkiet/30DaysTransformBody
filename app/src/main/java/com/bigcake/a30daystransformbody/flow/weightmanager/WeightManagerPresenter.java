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
import java.util.concurrent.TimeUnit;

/**
 * Created by kiethuynh on 08/05/2017
 */

public class WeightManagerPresenter implements WeightManagerContract.Presenter {
    private WeightManagerContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private Weight mCurrentWeight;
    private List<Entry> mEntries;
    private List<String> mLabels;
    private List<Weight> mWeightList;

    public WeightManagerPresenter(WeightManagerContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        mChallengeRepository = challengeRepository;
        mWeightList = new ArrayList<>();
        mEntries = new ArrayList<>();
        mLabels = new ArrayList<>();
    }

    @Override
    public void updateWeight(final float weight) {
        mCurrentWeight.setWeight(weight);
        mChallengeRepository.updateWeight(mCurrentWeight, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {
                int space = (int) TimeUnit.DAYS.convert((Utils.convertTimeToMillis(Utils.getZeroTimeDate(mCurrentWeight.getDate())) - Utils.convertTimeToMillis(Utils.getZeroTimeDate(mWeightList.get(0).getDate()))), TimeUnit.MILLISECONDS);
                mEntries.set(mEntries.size() - 1, new Entry(weight, space));
                mView.showWeightChart(mEntries, mLabels);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void insertWeight(final float weight) {
        final Date date = new Date();
        mCurrentWeight = new Weight(date, weight);
        mChallengeRepository.insertWeight(mCurrentWeight, new ChallengeDataSource.ChallengeCallBack() {
            @Override
            public void onSuccess() {
                mWeightList.add(mCurrentWeight);

                int space = (int) TimeUnit.DAYS.convert((Utils.convertTimeToMillis(Utils.getZeroTimeDate(date)) - Utils.convertTimeToMillis(Utils.getZeroTimeDate(mWeightList.get(0).getDate()))), TimeUnit.MILLISECONDS);
                // gen new labels
                Calendar calendar = Calendar.getInstance();
                if (mWeightList.size() >= 2) {
                    calendar.setTime(mWeightList.get(mWeightList.size() - 2).getDate());
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    calendar.setTime(date);
                }
                generateChartLabels(calendar.getTime());

                mEntries.add(new Entry(weight, space));
                mView.showWeightChart(mEntries, mLabels);
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
                mWeightList = weightList;
                if (weightList.size() > 0) {
                    generateChartLabels(weightList.get(0).getDate());
                    setChartData(weightList);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setChartData(List<Weight> weightList) {
        if (weightList == null) return;

        if (weightList.get(0) != null) {
            mEntries.add(new Entry(weightList.get(0).getWeight(), 0));
        }

        if (weightList.size() > 1)
            for (int i = 1; i < weightList.size(); i++) {
                Weight w = weightList.get(i);
                int space = (int) TimeUnit.DAYS.convert((Utils.convertTimeToMillis(Utils.getZeroTimeDate(w.getDate())) - Utils.convertTimeToMillis(Utils.getZeroTimeDate(weightList.get(0).getDate()))), TimeUnit.MILLISECONDS);
                mEntries.add(new Entry(weightList.get(i).getWeight(), space));
            }


        mView.showWeightChart(mEntries, mLabels);
    }

    private void generateChartLabels(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().compareTo(mWeightList.get(mWeightList.size() - 1).getDate()) <= 0) {
            mLabels.add(Utils.formatDateChart(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

}
