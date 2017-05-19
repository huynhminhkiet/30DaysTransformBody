package com.bigcake.a30daystransformbody.data.source;

/**
 * Created by kiethuynh on 19/05/2017
 */

public interface PreferencesDataSource {
    void putBoolean(String key,boolean b);
    void putString(String key, String value);
    void getBoolean(String key, boolean defaultValue);
    void getString(String key, String defaultValue);
}
