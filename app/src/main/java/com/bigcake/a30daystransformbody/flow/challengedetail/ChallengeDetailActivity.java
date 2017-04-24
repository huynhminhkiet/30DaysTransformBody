package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.content.ContextWrapper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.Challenge;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.ChallengeAlbumFragment;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress.ChallengeDayPresenter;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress.ChallengeProgressFragment;
import com.bigcake.a30daystransformbody.flow.challengedetail.gifalbum.GifAlbumFragment;
import com.bigcake.a30daystransformbody.utils.Constants;

public class ChallengeDetailActivity extends BaseActivity implements ChallengeDetailContract.View {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChallengeDetailAdapter mChallengeDetailAdapter;
    ContextWrapper wrapper;

    private ChallengeDetailContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_challenge_detail;
    }

    @Override
    protected void initViews() {
        bindViews();

        Challenge challenge = (Challenge) getIntent().getSerializableExtra(Constants.EXTRA_CHALLENGE);
        wrapper = new ContextWrapper(getApplicationContext());
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
        ChallengeProgressFragment challengeProgressFragment = new ChallengeProgressFragment();
        challengeProgressFragment.setPresenter(new ChallengeDayPresenter(challengeProgressFragment,
                Injection.provideChallengeRepository(this)));
        mChallengeDetailAdapter.addFragment(challengeProgressFragment, "Progress");
        mChallengeDetailAdapter.addFragment(new ChallengeAlbumFragment(), "Album");
        mChallengeDetailAdapter.addFragment(new GifAlbumFragment(), "Gif");
        viewPager.setAdapter(mChallengeDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
