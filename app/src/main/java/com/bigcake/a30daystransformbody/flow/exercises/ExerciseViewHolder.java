package com.bigcake.a30daystransformbody.flow.exercises;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by Big Cake on 4/9/2017
 */

class ExerciseViewHolder extends RecyclerView.ViewHolder  {
    TextView tvTitle, tvTag;
    public ExerciseViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_exercise_title);
        tvTag = (TextView) itemView.findViewById(R.id.tv_tag);
    }
}
