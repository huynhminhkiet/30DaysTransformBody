package com.bigcake.a30daystransformbody.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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

}
