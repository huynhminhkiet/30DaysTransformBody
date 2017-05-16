package com.bigcake.a30daystransformbody.flow.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.flow.exercisedetail.ExerciseDetailActivity;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends BaseFragment implements ExercisesContract.View {
    private RecyclerView rvExercises;
    private ExerciseAdapter mExerciseAdapter;

    private ExercisesContract.Presenter mPresenter;

    public static ExerciseFragment newInstance(ExerciseCategory exerciseCategory) {
        ExerciseFragment exerciseFragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_EXERCISE_CATEGORY, exerciseCategory);
        exerciseFragment.setArguments(args);
        return exerciseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises, container, false);
        initViews(view);
        return view;

    }


    private void initViews(View view) {
        bindViews(view);
        ExerciseCategory exerciseCategory = (ExerciseCategory) getArguments().getSerializable(Constants.EXTRA_EXERCISE_CATEGORY);
        mPresenter = new ExercisesPresenter(this, Injection.provideExerciseCategoriesRepository(getContext()), exerciseCategory);
        mPresenter.start();
    }

    private void bindViews(View view) {
        rvExercises = (RecyclerView) view.findViewById(R.id.rv_exercises);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));

        mExerciseAdapter = new ExerciseAdapter(new ArrayList<Exercise>(), new ExerciseAdapter.ExerciseAdapterListener() {
            @Override
            public void onExerciseItemClick(Exercise exercise) {
                Intent intent = new Intent(getActivity(), ExerciseDetailActivity.class);
                intent.putExtra(Constants.EXTRA_EXERCISE, exercise);
                startActivity(intent);
            }
        });
        rvExercises.setAdapter(mExerciseAdapter);
    }

    @Override
    public void setPresenter(ExercisesContract.Presenter presenter) {

    }

    @Override
    public void displayExercises(List<Exercise> exerciseList) {
        mExerciseAdapter.replaceAllData(exerciseList);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
