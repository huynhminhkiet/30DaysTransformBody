package com.bigcake.a30daystransformbody.flow.exercisecategories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigcake.a30daystransformbody.base.BaseFragment;

/**
 * Created by Big Cake on 4/2/2017
 */

public abstract class BaseExerciseCategoryFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);

    }

    protected abstract int getLayoutResourceId();

}
