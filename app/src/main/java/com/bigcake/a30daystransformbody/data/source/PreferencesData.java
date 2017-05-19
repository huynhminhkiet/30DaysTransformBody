package com.bigcake.a30daystransformbody.data.source;

/**
 * Created by kiethuynh on 19/05/2017
 */

public interface PreferencesData {
    void putBoolean(String key,boolean b);
    void putString(String key, String value);
    boolean getBoolean(String key, boolean defaultValue);
    String getString(String key, String defaultValue);
}
