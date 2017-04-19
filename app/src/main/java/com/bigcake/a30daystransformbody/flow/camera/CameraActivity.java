package com.bigcake.a30daystransformbody.flow.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseActivity;


public class CameraActivity extends BaseActivity {
    private ImageSurfaceView mImageSurfaceView;

    private FrameLayout cameraPreviewLayout;

    private Camera mCamera;
    private int mCurrentCameraId;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initViews() {
        bindViews();
        mCamera = getCameraInstance();
        mCurrentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        setupCamera();
        Button captureButton = (Button) findViewById(R.id.button);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, pictureCallback);
            }
        });
    }

    private void bindViews() {
        cameraPreviewLayout = (FrameLayout) findViewById(R.id.camera_preview);
    }

    private Camera getCameraInstance() {
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }

    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null) {
                Toast.makeText(CameraActivity.this, "Captured image is empty", Toast.LENGTH_LONG).show();
                return;
            }
        }
    };

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

    private void setupCamera() {
        mImageSurfaceView = new ImageSurfaceView(this, mCamera);
        cameraPreviewLayout.addView(mImageSurfaceView);
        setCameraDisplayOrientation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCameraInstance();
            mCamera.startPreview();
            setupCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.setPreviewCallback(null);
        mImageSurfaceView.getHolder().removeCallback(mImageSurfaceView);
        mCamera.release();
        mCamera = null;
    }

    private void setCameraDisplayOrientation() {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(mCurrentCameraId, info);
        int rotation = getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }
}
