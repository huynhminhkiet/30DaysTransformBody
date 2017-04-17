package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.ChallengeProgressFragmentListener;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengeProgressFragment extends BaseFragment implements ChallengeProgressContract.View, ItemClickListener<ChallengeDay> {
    private ChallengeProgressContract.Presenter mPresenter;
    private ChallengeProgressFragmentListener mListener;

    private RecyclerView rvChallengeDay;
    private ChallengeDayAdapter mChallengeDayAdapter;
    private ProgressBar progressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (ChallengeProgressFragmentListener) context;
    }

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
        mChallengeDayAdapter = new ChallengeDayAdapter(getContext(), new ArrayList<ChallengeDay>());
        mChallengeDayAdapter.setItemClickListener(this);
        rvChallengeDay.setAdapter(mChallengeDayAdapter);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
    }

    @Override
    public void setPresenter(ChallengeProgressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayChallengeDays(List<ChallengeDay> challengeDayList) {
        mChallengeDayAdapter.replaceAllData(challengeDayList);
    }

    @Override
    public void displayProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void openCamera() {
        mListener.onOpenCamera();
    }

    @Override
    public void onItemClick(ChallengeDay item) {
        mPresenter.challengeDayClick(item);
    }
}
