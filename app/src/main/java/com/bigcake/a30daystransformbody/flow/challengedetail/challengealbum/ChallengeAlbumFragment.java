package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;

/**
 * Created by Big Cake on 4/14/2017
 */

public class ChallengeAlbumFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_album, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
    }
}
