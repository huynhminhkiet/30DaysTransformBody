package com.bigcake.a30daystransformbody.flow.challengedetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.data.Challenge;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.ChallengeAlbumFragment;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress.ChallengeDayPresenter;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress.ChallengeProgressFragment;
import com.bigcake.a30daystransformbody.interfaces.ChallengeProgressFragmentListener;
import com.bigcake.a30daystransformbody.manager.CameraManager;
import com.bigcake.a30daystransformbody.utils.Constants;

public class ChallengeDetailActivity extends BaseActivity implements ChallengeDetailContract.View, ChallengeProgressFragmentListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
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
        ChallengeProgressFragment challengeProgressFragment = new ChallengeProgressFragment();
        challengeProgressFragment.setPresenter(new ChallengeDayPresenter(challengeProgressFragment,
                Injection.provideChallengeRepository()));
        mChallengeDetailAdapter.addFragment(challengeProgressFragment, "Progress");
        mChallengeDetailAdapter.addFragment(new ChallengeAlbumFragment(), "Album");
        viewPager.setAdapter(mChallengeDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onOpenCamera() {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        CameraManager cameraManager = CameraManager.getInstance();
        cameraManager.genOutputFileUri(new CameraManager.GenOutputFileUriCallBack() {
            @Override
            public void onSuccess(Uri outputUri) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }

            @Override
            public void onError(String e) {
                Toast.makeText(ChallengeDetailActivity.this, e, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

        }
    }
}
