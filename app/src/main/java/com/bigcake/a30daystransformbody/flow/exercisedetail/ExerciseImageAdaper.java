package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;

import java.util.List;

/**
 * Created by Big Cake on 4/9/2017
 */

public class ExerciseImageAdaper extends RecyclerView.Adapter<ExerciseImageViewHolder> {

    private List<Integer> mImageList;

    public ExerciseImageAdaper(List<Integer> imageList) {
        mImageList = imageList;
    }

    @Override
    public ExerciseImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExerciseImageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ExerciseImageViewHolder holder, int position) {
        holder.ivExerciseImage.setImageResource(mImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public void replaceAllData(List<Integer> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }
}
