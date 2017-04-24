package com.bigcake.a30daystransformbody.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by Big Cake on 4/21/2017
 */

public class Utils {
    public static boolean isScreenLarge(Context context) {
        final int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
                || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
