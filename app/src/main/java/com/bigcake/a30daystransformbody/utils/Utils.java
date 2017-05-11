package com.bigcake.a30daystransformbody.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

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

    public static void saveGifImage(byte[] gifImage) {

        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root + "/my_images_folder");
        dir.mkdirs();
        String imageName = generateImageName() + ".gif";
        File file = new File(dir, imageName);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(gifImage);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateImageName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Date convertTimeInMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static long convertTimeToMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static void putBooleanPrefs(Activity activity, String key, boolean value) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("appPrefs", MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    public static boolean getBooleanPrefs(Activity activity, String key, boolean defaultValue) {
        SharedPreferences prefs = activity.getSharedPreferences("appPrefs", MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public static Date getZeroTimeDate(Date fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String formatDateChart(Date date) {
        SimpleDateFormat simpleDateFormat;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        if (day == 1) {
            if (month == Calendar.DECEMBER) {
                simpleDateFormat = new SimpleDateFormat("dd/M", Locale.getDefault());
                return simpleDateFormat.format(date) + year % 100;
            } else {
                simpleDateFormat = new SimpleDateFormat("dd/M", Locale.getDefault());
                return simpleDateFormat.format(date);
            }
        } else {
            simpleDateFormat = new SimpleDateFormat("d", Locale.getDefault());
            return simpleDateFormat.format(date);
        }
    }
}
