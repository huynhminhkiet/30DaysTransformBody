package com.bigcake.a30daystransformbody.utils;

import android.content.Context;
import android.content.res.Configuration;

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
}
