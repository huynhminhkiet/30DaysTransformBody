package com.bigcake.a30daystransformbody.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Big Cake on 5/5/2017
 */

public class FileUtils {

    public static String saveImage(Bitmap bitmap, String fileName) {
        Bitmap scaledBitmap = scaleBitmap(bitmap, 600);
        File file = new File (getImageDir(), fileName);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static byte[] loadImage(String imageName) {
        FileInputStream inputStream = null;
        File file = new File (getImageDir(), imageName);
        try {
            inputStream = new FileInputStream(file);
            return Utils.convertBitmapToByteArray(BitmapFactory.decodeStream(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static File getImageDir() {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        return myDir;
    }

    public static String genarateImageRandomName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newHeight) {
        float scale = (float) newHeight / bitmap.getHeight();
        return Bitmap.createScaledBitmap(bitmap, (int) (scale * bitmap.getWidth()), newHeight, true);
    }
}
