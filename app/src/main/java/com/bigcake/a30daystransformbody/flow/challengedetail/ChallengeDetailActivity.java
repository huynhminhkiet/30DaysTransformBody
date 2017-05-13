package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.content.ContextWrapper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.ChallengeAlbumFragment;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress.ChallengeProgressFragment;
import com.bigcake.a30daystransformbody.flow.challengedetail.changealbum.ChangeFragment;
import com.bigcake.a30daystransformbody.interfaces.ChallengeAlbumFragmentListener;
import com.bigcake.a30daystransformbody.interfaces.ChallengeProgressFragmentListener;
import com.bigcake.a30daystransformbody.utils.Constants;

public class ChallengeDetailActivity extends BaseActivity implements ChallengeDetailContract.View,
        ChallengeProgressFragmentListener, ChallengeAlbumFragmentListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChallengeDetailAdapter mChallengeDetailAdapter;
    ContextWrapper wrapper;
    private ChallengeProgressFragment challengeProgressFragment;
    private ChallengeAlbumFragment challengeAlbumFragment;
    private ChangeFragment changeFragment;

    private ChallengeDetailContract.Presenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_challenge_detail;
    }

    @Override
    protected void initViews() {
        Log.d(getClass().getSimpleName(), "onCreate");
        bindViews();

        Exercise exercise = (Exercise) getIntent().getSerializableExtra(Constants.EXTRA_EXERCISE);
        wrapper = new ContextWrapper(getApplicationContext());
        mPresenter = new ChallengeDetailPresenter(this, exercise);
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
    public void displayChallenge(Exercise exercise) {
        challengeProgressFragment = ChallengeProgressFragment.newInstance(exercise);
        challengeAlbumFragment = ChallengeAlbumFragment.newInstance(exercise);
        changeFragment = ChangeFragment.newInstance(exercise);

        mChallengeDetailAdapter = new ChallengeDetailAdapter(getSupportFragmentManager());
        mChallengeDetailAdapter.addFragment(challengeProgressFragment, "Progress");
        mChallengeDetailAdapter.addFragment(challengeAlbumFragment, "Album");
        mChallengeDetailAdapter.addFragment(changeFragment, "Change");
        viewPager.setAdapter(mChallengeDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onChallengeDayImageUpdated(ChallengeDay challengeDay) {
        challengeAlbumFragment.onChallengeDayImageOnBoardUpdated(challengeDay);
    }

    @Override
    public void onChallengeDayImageDeleted(ChallengeDay challengeDay) {
        challengeProgressFragment.deleteImage(challengeDay);
    }

    @Override
    public void onChangeImageCreated(ChallengeImage challengeImage) {
        changeFragment.refreshNewImage(challengeImage);
    }
}
