package com.bigcake.a30daystransfrombody.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransfrombody.R;

/**
 * Created by kiethuynh on 30/03/2017
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        initViews(view);
        return view;
    }
    protected abstract int getLayoutResourceId();
    protected abstract void initViews(View view);
}
