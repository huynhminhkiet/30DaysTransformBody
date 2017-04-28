package com.bigcake.a30daystransformbody.flow.addchallenge;

import android.util.Log;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;
import com.bigcake.a30daystransformbody.utils.ActivityUtils;

public class AddChallengeActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_challenge;
    }

    @Override
    protected void initViews() {
        Log.d(getClass().getSimpleName(), "onCreate");

        AddChallengeFragment fragment = new AddChallengeFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
    }
}
