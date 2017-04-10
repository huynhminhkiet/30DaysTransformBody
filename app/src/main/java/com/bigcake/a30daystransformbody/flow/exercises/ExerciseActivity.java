package com.bigcake.a30daystransformbody.flow.exercises;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.flow.exercisedetail.ExerciseDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends BaseActivity implements ExercisesContract.View {
    private RecyclerView rvExercises;
    private ExerciseAdapter mExerciseAdapter;

    private ExercisesContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_exercises;
    }

    @Override
    protected void initViews() {
        bindViews();
        mPresenter = new ExercisesPresenter(this, Injection.provideExerciseCategoriesRepository());
        mPresenter.start();
    }

    private void bindViews() {
        rvExercises = (RecyclerView) findViewById(R.id.rv_exercises);
        rvExercises.setLayoutManager(new LinearLayoutManager(this));

        mExerciseAdapter = new ExerciseAdapter(new ArrayList<Exercise>(), new ExerciseAdapter.ExerciseAdapterListener() {
            @Override
            public void onExerciseItemClick(Exercise exercise) {
                Intent intent = new Intent(ExerciseActivity.this, ExerciseDetailActivity.class);
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
}
