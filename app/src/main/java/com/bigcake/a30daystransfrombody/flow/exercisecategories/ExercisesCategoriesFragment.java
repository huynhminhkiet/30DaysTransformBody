package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import android.view.View;

import com.bigcake.a30daystransfrombody.R;
import com.bigcake.a30daystransfrombody.base.BaseFragment;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExercisesCategoriesFragment extends BaseFragment {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_exercises;
    }

    @Override
    protected void initViews(View view) {

    }

    public static ExercisesCategoriesFragment newInstance() {
        return new ExercisesCategoriesFragment();
    }
}
