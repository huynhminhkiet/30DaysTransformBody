package com.bigcake.a30daystransformbody.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bigcake.a30daystransformbody.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Big Cake on 4/7/2017
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initViews();
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initViews();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Drawable getDrawableFromAssets(String name) {
        try {
            InputStream ims = getAssets().open(name);
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            return null;
        }
    }
}
