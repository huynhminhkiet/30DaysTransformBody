package com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;

/**
 * Created by kiethuynh on 10/04/2017
 */

public class ChallengePlanFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_plan, container, false);
        return view;
    }
}