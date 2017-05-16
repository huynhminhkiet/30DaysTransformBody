package com.bigcake.a30daystransformbody.flow.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ExerciseCategory;
import com.bigcake.a30daystransformbody.data.Weight;
import com.bigcake.a30daystransformbody.flow.exercisecategories.ExercisesCategoriesFragment;
import com.bigcake.a30daystransformbody.flow.exercises.ExerciseFragment;
import com.bigcake.a30daystransformbody.flow.weightmanager.WeightManagerFragment;
import com.bigcake.a30daystransformbody.interfaces.ExercisesCategoriesFragmentListener;
import com.bigcake.a30daystransformbody.utils.ActivityUtils;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bigcake.a30daystransformbody.utils.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View, ExercisesCategoriesFragmentListener {
    private static final String KEY_CURRENT_FRAGMENT = "key_current_fragment";
    private Toolbar toolbar;
    private Fragment mCurrentFragment;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initSlideMenu();

        mPresenter = new MainPresenter(this, Injection.provideChallengeRepository(this));
        mPresenter.start();

        if (savedInstanceState == null) {
            mCurrentFragment = ExercisesCategoriesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
        } else {
            mCurrentFragment = getSupportFragmentManager().getFragment(savedInstanceState, KEY_CURRENT_FRAGMENT);
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, KEY_CURRENT_FRAGMENT, mCurrentFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.weight_manager) {
            mCurrentFragment = WeightManagerFragment.newInstance();
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
        } else if (id == R.id.exercises) {
            mCurrentFragment = ExercisesCategoriesFragment.newInstance();
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
        } else if (id == R.id.my_exercises) {
            mCurrentFragment = ExerciseFragment.newInstance(null);
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initSlideMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void resetWeightTracker() {
        Utils.putBooleanPrefs(this, Constants.PREFS_TODAY_WEIGHT_UPDATED, false);
    }

    @Override
    public void trackWeight(Weight weight) {
        Utils.putBooleanPrefs(this, Constants.PREFS_TODAY_WEIGHT_UPDATED, true);
    }

    @Override
    public void onOpenExerciseList(ExerciseCategory exerciseCategory) {
        mCurrentFragment = ExerciseFragment.newInstance(exerciseCategory);
        ActivityUtils.replaceFragmentToActivityWithBackstack(getSupportFragmentManager(), mCurrentFragment, R.id.fragment_container);
    }
}
