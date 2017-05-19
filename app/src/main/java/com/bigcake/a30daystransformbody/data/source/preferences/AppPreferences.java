package com.bigcake.a30daystransformbody.data.source.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.bigcake.a30daystransformbody.data.source.PreferencesData;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kiethuynh on 19/05/2017
 */

public class AppPreferences implements PreferencesData {
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
    public boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }
}
