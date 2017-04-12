package com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by Big Cake on 4/12/2017
 */

class ChallengeViewHolder extends RecyclerView.ViewHolder {
    TextView tvDay;
    public ChallengeViewHolder(View itemView) {
        super(itemView);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
    }
}
