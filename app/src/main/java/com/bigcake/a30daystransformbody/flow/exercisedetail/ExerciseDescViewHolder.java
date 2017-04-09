package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by Big Cake on 4/7/2017
 */

class ExerciseDescViewHolder extends RecyclerView.ViewHolder {
    TextView tvDesciption;
    public ExerciseDescViewHolder(View itemView) {
        super(itemView);
        tvDesciption = (TextView) itemView.findViewById(R.id.tv_description);
    }
}
