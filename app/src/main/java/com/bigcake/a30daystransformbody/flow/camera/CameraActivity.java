package com.bigcake.a30daystransformbody.flow.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class CameraActivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener {

    public static final int RETAKE_IMAGE_RESULT_CODE = 104;


    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private ImageButton captureImage;
    private ImageView imvBackground,imvLines;
    private int cameraId;
    private boolean flashmode = false;
    private int rotation;
    private Bitmap takenBitmap = null;
    private Camera.Size mPreviewSize;

    private int timer = 0;
    private String lastPhotoId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Utils.isScreenLarge(this)) {
            // width > height, better to use Landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        Intent intent = getIntent();
        if (intent != null) {
            lastPhotoId = intent.getStringExtra("PHOTO_ID");
        }

        if (savedInstanceState != null) {
            lastPhotoId = savedInstanceState.getString("PHOTO_ID");
        }

        setContentView(R.layout.activity_camera);

        cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

        captureImage = (ImageButton) findViewById(R.id.captureImage);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        imvBackground = (ImageView) findViewById(R.id.activity_camera_imv_background);
        imvLines = (ImageView) findViewById(R.id.activity_camera_imv_lines);


        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        captureImage.setOnClickListener(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void resetCameraSize() {

        if (mPreviewSize == null) return;

        Camera.Parameters params = camera.getParameters();

        params.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
        params.setPictureSize(mPreviewSize.width, mPreviewSize.height);
        try {
            camera.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if (!openCamera(Camera.CameraInfo.CAMERA_FACING_BACK)) {
            alertCameraDialog();
        } else {

            List<Camera.Size> mSupportedPreviewSizes = camera.getParameters().getSupportedPictureSizes();
            mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, surfaceView.getWidth(), surfaceView.getHeight());

            resetCameraSize();
        }
    }

    private boolean openCamera(int id) {
        boolean result = false;
        cameraId = id;
        releaseCamera();
        try {
            camera = Camera.open(cameraId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (camera != null) {
            try {
                setUpCamera(camera);
                camera.setErrorCallback(new Camera.ErrorCallback() {

                    @Override
                    public void onError(int error, Camera camera) {

                    }
                });
                camera.setDisplayOrientation(90);
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                result = true;
            } catch (IOException e) {
                e.printStackTrace();
                result = false;
                releaseCamera();
            }
        }
        return result;
    }

    private void setUpCamera(Camera c) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        rotation = getWindow().getWindowManager().getDefaultDisplay().getRotation();
        int degree = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 0;
                break;
            case Surface.ROTATION_90:
                degree = 90;
                break;
            case Surface.ROTATION_180:
                degree = 180;
                break;
            case Surface.ROTATION_270:
                degree = 270;
                break;

            default:
                break;
        }

        rotation = (info.orientation - degree + 360) % 360;

        System.out.println("rotation = " + rotation);
        // }
        c.setDisplayOrientation(rotation);
        Camera.Parameters params = c.getParameters();

        List<String> focusModes = params.getSupportedFlashModes();
        if (focusModes != null) {
            if (focusModes
                    .contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                params.setFlashMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
        }
        params.setRotation(rotation);

        resetCameraSize();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null) {
            releaseCamera();
        }
    }

    private void releaseCamera() {
        try {
            if (camera != null) {
                camera.setPreviewCallback(null);
                camera.setErrorCallback(null);
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", e.toString());
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.captureImage:

                if (timer == 0) {
                    takeImage();
                }
                break;
            default:
                break;
        }
    }


    private void takeImage() {
        camera.takePicture(null, null, new Camera.PictureCallback() {

            private File imageFile;

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    // convert byte array into bitmap
                    Bitmap loadedImage = null;
                    Bitmap rotatedBitmap = null;
                    loadedImage = BitmapFactory.decodeByteArray(data, 0,
                            data.length);

                    Camera.Size size = camera.getParameters().getPictureSize();
                    int width = imvLines.getWidth();
                    int height = imvLines.getHeight();

                    System.out.println("Camera size: " + size.width + " - " + size.height);
                    System.out.println("Surface size: " + width + " - " + height);

                    System.out.println("Camera Ratio = " + (float) size.height / size.width);

                    int newHeight = (int) ((float) (size.height * height) / (float) width);

                    System.out.println("New height: " + newHeight);

                    // rotate Image
                    Matrix rotateMatrix = new Matrix();
                    rotateMatrix.postRotate(rotation);

                    if (cameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        rotateMatrix.preScale(1, -1);
                    }

                    rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0,
                            loadedImage.getWidth(), loadedImage.getHeight(),
                            rotateMatrix, false);
                    rotatedBitmap = Bitmap.createBitmap(rotatedBitmap, 0, 0, rotatedBitmap.getWidth(), newHeight);

                    loadedImage.recycle();

                    takenBitmap = rotatedBitmap;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void flipCamera() {
        int id = (cameraId == Camera.CameraInfo.CAMERA_FACING_BACK ? Camera.CameraInfo.CAMERA_FACING_FRONT
                : Camera.CameraInfo.CAMERA_FACING_BACK);
        if (!openCamera(id)) {
            alertCameraDialog();
        }
    }

    private void alertCameraDialog() {
        AlertDialog.Builder dialog = createAlert(this,
                "Camera info", "Error to open camera");
        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        dialog.show();
    }

    private AlertDialog.Builder createAlert(Context context, String title, String message) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(
                new ContextThemeWrapper(context,
                        android.R.style.Theme_Holo_Light_Dialog));
        dialog.setIcon(R.mipmap.ic_launcher);
        if (title != null)
            dialog.setTitle(title);
        else
            dialog.setTitle("Information");
        dialog.setMessage(message);
        dialog.setCancelable(false);
        return dialog;

    }

    private void flashOnButton() {
        if (camera != null) {
            try {
                Camera.Parameters param = camera.getParameters();
                param.setFlashMode(!flashmode ? Camera.Parameters.FLASH_MODE_TORCH
                        : Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(param);
                flashmode = !flashmode;
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.15;
        double targetRatio = (double) h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.width - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.width - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.width - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.width - targetHeight);
                }
            }
        }

        System.out.println("optimalSize: " + optimalSize.width + " - " + optimalSize.height);

        return optimalSize;
    }
}
