package com.bigcake.a30daystransformbody.flow.exercisecategories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.flow.exercises.ExerciseFragment;
import com.bigcake.a30daystransformbody.interfaces.ExercisesCategoriesFragmentListener;
import com.bigcake.a30daystransformbody.utils.ActivityUtils;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExercisesCategoriesFragment extends BaseFragment implements ExerciseCategoriesContract.View,
        ExerciseCategoryAdapter.ExerciseCategoryAdapterListener {

    private ExerciseCategoriesContract.Presenter mPresenter;

    private ViewPager mExerciseCatViewPager;
    private ExerciseCategoryDescriptionAdapter mExerciseCategoryDescriptionAdapter;

    private RecyclerView rvExerciseCategory;
    private ExerciseCategoryAdapter mExerciseCategoryAdapter;

    private ImageButton ivBtnOpenExercises;
    private ExercisesCategoriesFragmentListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ExercisesCategoriesFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        initViews(view);
        return view;
    }

    protected void initViews(View view) {
        bindViews(view);
        mPresenter = new ExerciseCategoryPresenter(this, Injection.provideExerciseCategoriesRepository(getContext()));
        mPresenter.start();
    }

    private void bindViews(View view) {
        ivBtnOpenExercises = (ImageButton) view.findViewById(R.id.btn_go);
        ivBtnOpenExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openExercises();
            }
        });

        mExerciseCatViewPager = (ViewPager) view.findViewById(R.id.pager);
        mExerciseCategoryDescriptionAdapter = new ExerciseCategoryDescriptionAdapter(getChildFragmentManager());
        mExerciseCategoryDescriptionAdapter.addFragment(new PullExerciseFragment());
        mExerciseCategoryDescriptionAdapter.addFragment(new PullExerciseFragment());
        mExerciseCategoryDescriptionAdapter.addFragment(new PullExerciseFragment());
        mExerciseCategoryDescriptionAdapter.addFragment(new PullExerciseFragment());

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
    public void displayCategories(List<ExerciseCategory> exerciseCategoryList) {
        mExerciseCategoryAdapter.replaceAllData(exerciseCategoryList);
        mExerciseCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseCategoryItemClick(ExerciseCategory exerciseCategory, int pos) {
        mExerciseCatViewPager.setCurrentItem(pos);
        mPresenter.setCurrentExerciseCategory(exerciseCategory);
    }

    @Override
    public void setPresenter(ExerciseCategoriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showExercises(ExerciseCategory exerciseCategory) {
        mListener.onOpenExerciseList(exerciseCategory);
    }
}
