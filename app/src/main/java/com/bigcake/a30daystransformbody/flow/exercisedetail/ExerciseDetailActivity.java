package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
        mPresenter = new ExerciseDetailPresenter(this, Injection.provideExerciseCategoriesRepository());
        mPresenter.start();
    }

    private void bindViews() {
        tvTag = (TextView) findViewById(R.id.tv_tag);
        tvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Injection.provideChallengeRepository(ExerciseDetailActivity.this).generateChallengesDay(0, new ChallengeDataSource.ChallengeCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ExerciseDetailActivity.this, "hacked", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });

        rvImage = (RecyclerView) findViewById(R.id.rv_exercise_image);
        rvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mExerciseImageAdaper = new ExerciseImageAdaper(new ArrayList<Integer>());
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
        mExerciseImageAdaper.replaceAllData(exercise.getImages());
        mExerciseDescriptionAdapter.replaceAllData(exercise.getDescriptions());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_start) {
            Exercise exercise = new Exercise(0, 0, "title", "tag", 1, null, null);
            Intent intent = new Intent(this, ChallengeDetailActivity.class);
            intent.putExtra(Constants.EXTRA_EXERCISE, exercise);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
