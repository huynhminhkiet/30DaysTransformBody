package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.AlbumAdapterListener;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private Context mContext;
    private List<ChallengeDayImage> mChallengeDayImageList;
    private AlbumAdapterListener mListener;

    public AlbumAdapter(Context context, List<ChallengeDayImage> challengeDayImageList) {
        mContext = context;
        mChallengeDayImageList = challengeDayImageList;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, int position) {
        final ChallengeDayImage challengeDayImage = mChallengeDayImageList.get(position);
        holder.bind(challengeDayImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(challengeDayImage, holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onItemLongClick(challengeDayImage, holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChallengeDayImageList.size();
    }

    public void replaceAllData(List<ChallengeDayImage> challengeDayImageList) {
        mChallengeDayImageList = challengeDayImageList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(AlbumAdapterListener listener) {
        mListener = listener;
    }

    public void updateItem(ChallengeDayImage challengeDayImage, int position) {
        mChallengeDayImageList.set(position, challengeDayImage);
        notifyItemChanged(position);
    }
}
