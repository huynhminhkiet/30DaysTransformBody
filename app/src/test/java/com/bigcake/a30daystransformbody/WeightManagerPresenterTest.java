package com.bigcake.a30daystransformbody;

import com.bigcake.a30daystransformbody.data.Weight;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.flow.weightmanager.WeightManagerContract;
import com.bigcake.a30daystransformbody.flow.weightmanager.WeightManagerPresenter;
import com.github.mikephil.charting.data.Entry;
import com.squareup.haha.guava.collect.Lists$RandomAccessReverseList;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;

/**
 * Created by Big Cake on 5/21/2017
 */

public class WeightManagerPresenterTest {

    private List<Weight> LIST_WEIGHT;
    private Weight mCurrentWeight;

    @Mock
    private WeightManagerContract.View mView;

    @Mock
    private ChallengeRepository mChallengeRepository;

    @Mock
    private List<Entry> mEntries;

    @Mock
    private List<String> mLabels;

    @Captor
    private ArgumentCaptor<ChallengeDataSource.GetLastWeightCallback> mGetLastWeightCallbackCaptor;

    @Captor
    private ArgumentCaptor<ChallengeDataSource.GetAllWeightCallback> mGetAllWeightCallbackCaptor;

    private WeightManagerPresenter mWeightManagerPresenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        mWeightManagerPresenter = new WeightManagerPresenter(mView, mChallengeRepository);

        Calendar cal =Calendar.getInstance();
        cal.setTime(new Date());
        LIST_WEIGHT = new ArrayList<>();
        cal.set(Calendar.DAY_OF_MONTH, 16);
        LIST_WEIGHT.add(new Weight(1, cal.getTime(), 50.1f));
        cal.set(Calendar.DAY_OF_MONTH, 17);
        LIST_WEIGHT.add(new Weight(1, cal.getTime(), 50.2f));
        cal.set(Calendar.DAY_OF_MONTH, 20);
        LIST_WEIGHT.add(new Weight(1, cal.getTime(), 50.4f));

        mCurrentWeight = LIST_WEIGHT.get(LIST_WEIGHT.size() - 1);

        mEntries = new ArrayList<>();
        mEntries.add(new Entry(50.1f, 0));
        mEntries.add(new Entry(50.2f, 1));
        mEntries.add(new Entry(50.4f, 4));

        mLabels = new ArrayList<>();
        mLabels.add("16");
        mLabels.add("17");
        mLabels.add("18");
        mLabels.add("19");
        mLabels.add("20");
    }

    @Test
    public void testPresenterStart() {
        mWeightManagerPresenter.start();
        verify(mChallengeRepository).getLastWeight(mGetLastWeightCallbackCaptor.capture());
        mGetLastWeightCallbackCaptor.getValue().onSuccess(mCurrentWeight);

        verify(mChallengeRepository).getAllWeight(mGetAllWeightCallbackCaptor.capture());
        mGetAllWeightCallbackCaptor.getValue().onSuccess(LIST_WEIGHT);

        ArgumentCaptor<List> entriesArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> labelsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mView).showWeightChart(entriesArgumentCaptor.capture(), labelsArgumentCaptor.capture());

        Assert.assertEquals(entriesArgumentCaptor.getValue().size(), 3);
        Assert.assertArrayEquals(labelsArgumentCaptor.getValue().toArray(), mLabels.toArray());
    }
}
