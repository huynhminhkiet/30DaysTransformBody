package com.bigcake.a30daystransformbody.flow.challengedetail.challengeplan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeViewHolder> {
    private Context mContext;
    private List<ChallengeDay> mChallengeDayList;

    public ChallengeAdapter(Context context, List<ChallengeDay> challengeDayList) {
        mContext = context;
        mChallengeDayList = challengeDayList;
    }
    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChallengeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_challenge, parent, false));
    }

    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {
        ChallengeDay challengeDay = mChallengeDayList.get(position);
        holder.tvDay.setText(challengeDay.getDay());
    }

    @Override
    public int getItemCount() {
        return mChallengeDayList.size();
    }

    public void replaceAllData(List<ChallengeDay> challengeDayList) {
        mChallengeDayList = challengeDayList;
        notifyDataSetChanged();
    }
}
