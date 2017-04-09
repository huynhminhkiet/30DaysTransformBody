package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by Big Cake on 4/7/2017
 */

class ExerciseImageViewHolder extends RecyclerView.ViewHolder {
    ImageView ivExerciseImage;
    public ExerciseImageViewHolder(View itemView) {
        super(itemView);
        ivExerciseImage = (ImageView) itemView.findViewById(R.id.iv_exercise_image);
    }
}
