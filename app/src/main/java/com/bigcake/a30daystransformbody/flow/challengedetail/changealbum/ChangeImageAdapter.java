package com.bigcake.a30daystransformbody.flow.challengedetail.changealbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;

import java.util.List;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class ChangeImageAdapter extends RecyclerView.Adapter<ChangeViewHolder> {
    private Context mContext;
    private List<ChallengeImage> mChangeImageList;
    private ItemClickListener<ChallengeImage> mListener;
    private ChallengeImageManager mChallengeImageManager;

    public ChangeImageAdapter(Context context, List<ChallengeImage> changeImageList, ChallengeImageManager challengeImageManager) {
        mContext = context;
        mChangeImageList = changeImageList;
        mChallengeImageManager = challengeImageManager;
    }

    @Override
    public ChangeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChangeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(final ChangeViewHolder holder, int position) {
        final ChallengeImage changeImage = mChangeImageList.get(position);
        holder.bind(changeImage, mChallengeImageManager);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(changeImage, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChangeImageList.size();
    }

    public void replaceAllData(List<ChallengeImage> challengeImageList) {
        mChangeImageList = challengeImageList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener<ChallengeImage> listener) {
        mListener = listener;
    }

    public void deleteItem(int mPositionSelected) {
        mChangeImageList.remove(mPositionSelected);
        notifyDataSetChanged();
    }

    public void addNewImage(ChallengeImage challengeImage) {
        mChangeImageList.add(challengeImage);
        notifyDataSetChanged();
    }
}
