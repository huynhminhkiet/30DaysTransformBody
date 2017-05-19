package com.bigcake.a30daystransformbody.data.source.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.bigcake.a30daystransformbody.data.source.PreferencesDataSource;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kiethuynh on 19/05/2017
 */

public class AppPreferences implements PreferencesDataSource {
    public SharedPreferences mSharedPreferences;

    private static AppPreferences mInstance;
    public static synchronized AppPreferences getInstance(Context context) {
        if (mInstance == null)
            mInstance = new AppPreferences(context);
        return mInstance;
    }

    public AppPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences("appPrefs", MODE_PRIVATE);

    }
    @Override
    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    @Override
    public void getBoolean(String key, boolean defaultValue) {
        mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public void getString(String key, String defaultValue) {
        mSharedPreferences.getString(key, defaultValue);
    }
}
