package com.bigcake.a30daystransformbody.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Acoustic on 12/05/2015.
 */
public class ResizeSurfaceView extends SurfaceView {

    public ResizeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        float ratio = 4.0f / 3;

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (ratio * width);

        setMeasuredDimension(width, height);
    }
}
