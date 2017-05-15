package com.bigcake.a30daystransformbody.manager;

import android.content.Context;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kiethuynh on 15/05/2017
 */

public class AssetsManager {
    private Context mContext;

    public AssetsManager(Context mContext) {
        this.mContext = mContext;
    }

    public String convertJsonFileToString(String filePath) {
        String jsonString = "";
        try {
            InputStream is = mContext.getAssets().open(filePath);
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
