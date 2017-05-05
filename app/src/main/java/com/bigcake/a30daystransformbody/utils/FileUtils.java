package com.bigcake.a30daystransformbody.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Big Cake on 5/5/2017
 */

public class FileUtils {

    public static String saveImage(Bitmap bitmap) {
        Bitmap scaledBitmap = scaleBitmap(bitmap, 600);
        String imageName = genarateImageName() + ".png";
        File file = new File (getImageDir(), imageName);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }

    private static File getImageDir() {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        return myDir;
    }

    private static String genarateImageName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newHeight) {
        float scale = (float) newHeight / bitmap.getHeight();
        return Bitmap.createScaledBitmap(bitmap, (int) (scale * bitmap.getWidth()), newHeight, true);
    }
}
