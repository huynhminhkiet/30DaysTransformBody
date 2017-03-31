package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Big Cake on 4/1/2017.
 */

public class ExerciseCategoryDescriptionAdapter extends FragmentStatePagerAdapter {
    public ExerciseCategoryDescriptionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new PullExerciseFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

}
