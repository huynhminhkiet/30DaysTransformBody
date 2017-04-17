package com.bigcake.a30daystransformbody.manager;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Big Cake on 4/17/2017
 */

public class CameraManager {
    private static CameraManager mInstance;
    private final String mFolderName = "/App30DaysTransformBody/";
    private String mFolerPath;

    public static synchronized CameraManager getInstance() {
        if (mInstance == null)
            mInstance = new CameraManager();
        return mInstance;
    }

    private CameraManager() {
        mFolerPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + mFolderName;
        File newdir = new File(mFolerPath);
        newdir.mkdirs();
    }

    public void genOutputFileUri(GenOutputFileUriCallBack callBack) {
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
