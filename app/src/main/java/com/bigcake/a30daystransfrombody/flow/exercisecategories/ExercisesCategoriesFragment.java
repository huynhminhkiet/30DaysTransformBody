package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigcake.a30daystransfrombody.R;
import com.bigcake.a30daystransfrombody.base.BaseFragment;
import com.bigcake.a30daystransfrombody.data.ExerciseCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExercisesCategoriesFragment extends BaseFragment implements ExerciseCategoriesContract.View,
        ExerciseCategoryAdapter.ExerciseCategoryAdapterListener{

    private ExerciseCategoriesContract.Presenter mPresenter;

    private ViewPager mExerciseCatViewPager;
    private ExerciseCategoryDescriptionAdapter mExerciseCategoryDescriptionAdapter;

    private RecyclerView rvExerciseCategory;
    private ExerciseCategoryAdapter mExerciseCategoryAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_exercises;
    }

    @Override
    protected void initViews(View view) {
        bindViews(view);
        mPresenter.start();
    }

    private void bindViews(View view) {
        mExerciseCatViewPager = (ViewPager) view.findViewById(R.id.pager);
        mExerciseCategoryDescriptionAdapter = new ExerciseCategoryDescriptionAdapter(getActivity().getSupportFragmentManager());
        mExerciseCatViewPager.setAdapter(mExerciseCategoryDescriptionAdapter);
        mExerciseCatViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mExerciseCategoryAdapter.setCurrentExerciseCat(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rvExerciseCategory = (RecyclerView) view.findViewById(R.id.rv_exercise_category);
        mExerciseCategoryAdapter = new ExerciseCategoryAdapter(getContext(), new ArrayList<ExerciseCategory>());
        mExerciseCategoryAdapter.setExerciseCategoryAdapterListener(this);
        rvExerciseCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvExerciseCategory.setAdapter(mExerciseCategoryAdapter);
    }

    public static ExercisesCategoriesFragment newInstance() {
        return new ExercisesCategoriesFragment();
    }

    @Override
    public void setPresenter(ExerciseCategoriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayCategories(List<ExerciseCategory> exerciseCategoryList) {
        mExerciseCategoryAdapter.replaceAllData(exerciseCategoryList);
        mExerciseCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayExerciseCategoryImages() {

    }

    @Override
    public void onExerciseCategoryItemClick(int pos) {
        mExerciseCatViewPager.setCurrentItem(pos);
    }
}
