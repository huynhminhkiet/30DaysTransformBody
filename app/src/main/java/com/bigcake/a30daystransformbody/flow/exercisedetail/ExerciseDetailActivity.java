package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.flow.challengedetail.ChallengeDetailActivity;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;

public class ExerciseDetailActivity extends BaseActivity implements ExerciseDetailContract.View {
    private TextView tvTag;
    private Button btnStart;
    private RecyclerView rvImage, rvDescription;
    private ExerciseImageAdaper mExerciseImageAdaper;
    private ExerciseDescriptionAdapter mExerciseDescriptionAdapter;

    private ExerciseDetailContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_exercise_detail;
    }

    @Override
    protected void initViews() {
        bindViews();
        Exercise exercise = (Exercise) getIntent().getSerializableExtra(Constants.EXTRA_EXERCISE);
        mPresenter = new ExerciseDetailPresenter(this,
                Injection.provideExerciseCategoriesRepository(this),
                Injection.provideChallengeRepository(this),
                exercise);
        mPresenter.start();
    }

    private void bindViews() {
        tvTag = (TextView) findViewById(R.id.tv_tag);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openChallenge();
            }
        });

        rvImage = (RecyclerView) findViewById(R.id.rv_exercise_image);
        rvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mExerciseImageAdaper = new ExerciseImageAdaper(new ArrayList<String>(), this);
        rvImage.setAdapter(mExerciseImageAdaper);

        rvDescription = (RecyclerView) findViewById(R.id.rv_exercise_desc);
        rvDescription.setLayoutManager(new LinearLayoutManager(this));
        mExerciseDescriptionAdapter = new ExerciseDescriptionAdapter(new ArrayList<String>());
        rvDescription.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvDescription.setAdapter(mExerciseDescriptionAdapter);
    }

    @Override
    public void setPresenter(ExerciseDetailContract.Presenter presenter) {

    }

    @Override
    public void displayExercise(Exercise exercise) {
        getSupportActionBar().setTitle(exercise.getTitle());
        tvTag.setText(exercise.getTag());
        btnStart.setText(exercise.getDay() > 0 ? String.format(getString(R.string.gen_start_day), String.valueOf(exercise.getDay())) : getString(R.string.btn_start));
        mExerciseImageAdaper.replaceAllData(exercise.getImageList());
        mExerciseDescriptionAdapter.replaceAllData(exercise.getDescriptionList());
    }

    @Override
    public void finishActivityAndUpdateData(Exercise exercise) {
    }

    @Override
    public void openChallengeScreen(Exercise exercise) {
        Intent intent = new Intent(this, ChallengeDetailActivity.class);
        intent.putExtra(Constants.EXTRA_EXERCISE, exercise);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
