package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Big Cake on 4/9/2017
 */

public class ExerciseImageAdaper extends RecyclerView.Adapter<ExerciseImageViewHolder> {

    private List<String> mImageList;
    private Context mContext;

    public ExerciseImageAdaper(List<String> imageList, Context context) {
        mImageList = imageList;
        this.mContext = context;
    }

    @Override
    public ExerciseImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExerciseImageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ExerciseImageViewHolder holder, int position) {

        try {
            InputStream ims = mContext.getAssets().open("images/" + mImageList.get(position));
            holder.ivExerciseImage.setImageBitmap(BitmapFactory.decodeStream(ims));
            ims .close();
        }
        catch(IOException ex)
        {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public void replaceAllData(List<String> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }
}
