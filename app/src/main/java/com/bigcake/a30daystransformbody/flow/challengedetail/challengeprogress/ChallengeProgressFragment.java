package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.base.MessageDialog;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.flow.camera.CameraActivity;
import com.bigcake.a30daystransformbody.interfaces.ChallengeDayAdapterListener;
import com.bigcake.a30daystransformbody.interfaces.ChallengeProgressFragmentListener;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengeProgressFragment extends BaseFragment implements ChallengeProgressContract.View,
        ItemClickListener<ChallengeDay>, ChallengeDayAdapterListener, View.OnClickListener {
    public static final int CAMERA_REQUEST_CODE = 100;
    private ChallengeProgressContract.Presenter mPresenter;
    private RecyclerView rvChallengeDay;
    private ChallengeDayAdapter mChallengeDayAdapter;
    private ProgressBar progressBar;
    private int mPositionUpdating;
    private ChallengeProgressFragmentListener mListener;
    private ImageButton btnDeleteChallenge, btnResetChallenge;
    private TextView tvDayLeft, tvProgress;

    public static ChallengeProgressFragment newInstance(Exercise exercise) {
        ChallengeProgressFragment fragment = new ChallengeProgressFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_EXERCISE, exercise);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ChallengeProgressFragmentListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate");
        Exercise exercise = (Exercise) getArguments().getSerializable(Constants.EXTRA_EXERCISE);
        mPresenter = new ChallengeDayPresenter(this,
                Injection.provideChallengeRepository(getContext()),
                Injection.provideExerciseCategoriesRepository(getContext()),
                exercise);
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
        mChallengeDayAdapter = new ChallengeDayAdapter(getContext(), new ArrayList<ChallengeDay>(), ChallengeImageManager.getInstance(Injection.provideChallengeRepository(getContext())));
        mChallengeDayAdapter.setItemClickListener(this);
        mChallengeDayAdapter.setChallengeDayAdapterListener(this);
        rvChallengeDay.setAdapter(mChallengeDayAdapter);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        btnDeleteChallenge = (ImageButton) view.findViewById(R.id.btn_delete_challenge);
        btnDeleteChallenge.setOnClickListener(this);
        btnResetChallenge = (ImageButton) view.findViewById(R.id.btn_reset_challenge);
        btnResetChallenge.setOnClickListener(this);
        tvProgress = (TextView) view.findViewById(R.id.tv_progress);
        tvDayLeft = (TextView) view.findViewById(R.id.tv_day_left);
    }

    @Override
    public void displayChallengeDays(List<ChallengeDay> challengeDayList) {
        mChallengeDayAdapter.replaceAllData(challengeDayList);
    }

    @Override
    public void displayProgressBar(int progress) {
        progressBar.setProgress(progress);
        int dayLeft = 30 - progress;
        tvDayLeft.setText(String.format(getContext().getString(dayLeft == 29 ? R.string.gen_1_day_left : R.string.gen_day_left), dayLeft));
        tvProgress.setText(String.format(Locale.getDefault(),"%.1f", (float)
                progress / 30 * 100));
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
                ChallengeDay challengeDay = (ChallengeDay) data.getSerializableExtra(Constants.EXTRA_CHALLENGE_DAY);
                mChallengeDayAdapter.updateItem(challengeDay, mPositionUpdating);
                mListener.onChallengeDayImageUpdated(challengeDay);
            }
        }
    }

    public void deleteImage(ChallengeDay challengeDay) {
        mChallengeDayAdapter.updateItem(challengeDay);
    }

    @Override
    public void requestUpdateProgress(int day) {
        mPresenter.updateProgress(day);
    }

    @Override
    public void requestUpdateOnDatabase(ChallengeDay challengeDay) {
        mPresenter.updateDataOnDatabase(challengeDay);
    }

    @Override
    public void onClick(View view) {
        if (view == btnDeleteChallenge) {
            showDeleteChallengeDialog();
        } else if (view == btnResetChallenge) {
            showResetChallengeDialog();
        }
    }

    private void showResetChallengeDialog() {
        final MessageDialog dialog = MessageDialog.create(getContext());
        dialog.setTitleText(R.string.gen_reset_challenge_dialog)
                .setMessage(R.string.gen_reset_challenge_dialog_message)
                .setLeftButton(R.string.cancel, new MessageDialog.ClickListener() {
                    @Override
                    public void onClick() {
                        dialog.dismiss();
                    }
                })
                .setRightButton(R.string.ok, new MessageDialog.ClickListener() {
                    @Override
                    public void onClick() {
                        mPresenter.resetChallenge();
                    }
                }).show();

    }

    @Override
    public void closeChallenge() {
        getActivity().finish();
    }

    private void showDeleteChallengeDialog() {
        final MessageDialog dialog = MessageDialog.create(getContext());
        dialog.setTitleText(R.string.gen_delete_challenge_dialog)
                .setMessage(R.string.gen_delete_challenge_dialog_message)
                .setLeftButton(R.string.cancel, new MessageDialog.ClickListener() {
                    @Override
                    public void onClick() {
                        dialog.dismiss();
                    }
                })
                .setRightButton(R.string.ok, new MessageDialog.ClickListener() {
                    @Override
                    public void onClick() {
                        mPresenter.deleteChallenge();
                    }
                }).show();
    }
}
