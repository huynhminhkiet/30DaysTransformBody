package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.Challenge;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan.ChallengeDayPresenter;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan.ChallengePlanFragment;
import com.bigcake.a30daystransformbody.utils.Constants;

public class ChallengeDetailActivity extends BaseActivity implements ChallengeDetailContract.View {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChallengeDetailAdapter mChallengeDetailAdapter;

    private ChallengeDetailContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_challenge_detail;
    }

    @Override
    protected void initViews() {
        bindViews();

        Challenge challenge = (Challenge) getIntent().getSerializableExtra(Constants.EXTRA_CHALLENGE);
        mPresenter = new ChallengeDetailPresenter(this, challenge);
        mPresenter.start();
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    @Override
    public void setPresenter(ChallengeDetailContract.Presenter presenter) {

    }

    @Override
    public void displayChallenge(Challenge challenge) {
        mChallengeDetailAdapter = new ChallengeDetailAdapter(getSupportFragmentManager());
        ChallengePlanFragment challengePlanFragment = new ChallengePlanFragment();
        challengePlanFragment.setPresenter(new ChallengeDayPresenter(challengePlanFragment,
                Injection.provideChallengeRepository()));
        mChallengeDetailAdapter.addFragment(challengePlanFragment, "Plan");
        viewPager.setAdapter(mChallengeDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
