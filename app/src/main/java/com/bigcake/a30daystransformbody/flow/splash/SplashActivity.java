package com.bigcake.a30daystransformbody.flow.splash;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.flow.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View{
    private LinearLayout lnDataLoading;
    private SplashContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        lnDataLoading = (LinearLayout) findViewById(R.id.progress_bar);
        mPresenter = new SplashPresenter(this,
                Injection.provideExerciseCategoriesRepository(this),
                Injection.provideHardData(this));
        mPresenter.start();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void gotoHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgressBar(boolean b) {
        lnDataLoading.setVisibility(b ? View.VISIBLE : View.GONE);
    }
}
