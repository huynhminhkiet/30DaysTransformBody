package com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengePlanFragment extends BaseFragment implements ChallengePlanContract.View {
    private ChallengePlanContract.Presenter mPresenter;

    private RecyclerView rvChallengeDay;
    private ChallengeAdapter mChallengeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_plan, container, false);
        initViews(view);
        if (mPresenter != null)
            mPresenter.start();
        return view;
    }

    private void initViews(View view) {
        rvChallengeDay = (RecyclerView) view.findViewById(R.id.rv_challenge);
        rvChallengeDay.setLayoutManager(new GridLayoutManager(getContext(), 6));
        mChallengeAdapter = new ChallengeAdapter(getContext(), new ArrayList<ChallengeDay>());
        rvChallengeDay.setAdapter(mChallengeAdapter);
    }

    @Override
    public void setPresenter(ChallengePlanContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayChallengeDays(List<ChallengeDay> challengeDayList) {
        mChallengeAdapter.replaceAllData(challengeDayList);
    }
}
