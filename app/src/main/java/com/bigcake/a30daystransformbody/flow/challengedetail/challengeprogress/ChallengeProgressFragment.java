package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.content.Intent;
import android.os.Bundle;
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
import com.bigcake.a30daystransformbody.flow.camera.CameraActivity;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengeProgressFragment extends BaseFragment implements ChallengeProgressContract.View, ItemClickListener<ChallengeDay> {
    public static final int CAMERA_REQUEST_CODE = 100;
    private ChallengeProgressContract.Presenter mPresenter;

    private RecyclerView rvChallengeDay;
    private ChallengeDayAdapter mChallengeDayAdapter;
    private ProgressBar progressBar;

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
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onItemClick(ChallengeDay item) {
        mPresenter.challengeDayClick(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
