package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.flow.camera.CameraActivity;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.AlbumPresenter;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;
import com.bigcake.a30daystransformbody.utils.Constants;

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

    private int mPositionUpdating;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate");
        mPresenter = new ChallengeDayPresenter(this, Injection.provideChallengeRepository(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_plan, container, false);
        initViews(view);
        Log.d(getClass().getSimpleName(), "onCreateView");
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
    public void displayChallengeDays(List<ChallengeDay> challengeDayList) {
        mChallengeDayAdapter.replaceAllData(challengeDayList);
    }

    @Override
    public void displayProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void openCamera(ChallengeDay challengeDay) {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        intent.putExtra(Constants.EXTRA_CHALLENGE_DAY, challengeDay);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onItemClick(ChallengeDay item, int position) {
        mPositionUpdating = position;
        mPresenter.challengeDayClick(item);
    }

    @Override
    public void updateItemView(ChallengeDay challengeDay, int position) {
        mChallengeDayAdapter.updateItem(challengeDay, position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                mChallengeDayAdapter.updateItem((ChallengeDay) data.getSerializableExtra(Constants.EXTRA_CHALLENGE_DAY), mPositionUpdating);
            }
        }
    }
}
