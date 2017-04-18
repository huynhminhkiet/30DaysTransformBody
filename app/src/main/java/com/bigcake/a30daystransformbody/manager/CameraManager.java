package com.bigcake.a30daystransformbody.manager;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Big Cake on 4/17/2017
 */

public class CameraManager {
    private static CameraManager mInstance;
    private final String mFolderExternalName = "/App30DaysTransformBody/";
    private final String mFolderInternalName = "Images";
    private String mFolerPath;

    public static synchronized CameraManager getInstance() {
        if (mInstance == null)
            mInstance = new CameraManager();
        return mInstance;
    }

    private CameraManager() {
        mFolerPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + mFolderExternalName;
        File newdir = new File(mFolerPath);
        newdir.mkdirs();
    }

    public void genOutputFileUriExternal(GenOutputFileUriCallBack callBack) {
        String file = mFolerPath + "challenge-day-" + genDateCreated()+ ".jpg";
        File newfile = new File(file);
        try {
            if(newfile.createNewFile())
                callBack.onSuccess(Uri.fromFile(newfile));
            else
                callBack.onError("error");
        } catch (IOException e){
            callBack.onError(e.getMessage());
        }
    }

    private String genDateCreated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public interface GenOutputFileUriCallBack {
        void onSuccess(Uri outputUri);
        void onError(String e);
    }
}
