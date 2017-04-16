package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by Big Cake on 4/12/2017
 */

class ChallengeViewHolder extends RecyclerView.ViewHolder {
    TextView tvDay;
    ImageView ivDay;
    public ChallengeViewHolder(View itemView) {
        super(itemView);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
        ivDay = (ImageView) itemView.findViewById(R.id.iv_day);
    }
}
