package com.bigcake.a30daystransfrombody.flow.exercisecategories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigcake.a30daystransfrombody.R;
import com.bigcake.a30daystransfrombody.data.ExerciseCategory;

import java.util.List;

/**
 * Created by Big Cake on 4/1/2017
 */

public class ExerciseCategoryAdapter extends RecyclerView.Adapter<ExerciseCategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<ExerciseCategory> mExerciseCategoryList;
    private ExerciseCategoryAdapterListener mListener;
    private int mCurrentExerciseCat;

    public ExerciseCategoryAdapter(Context context, List<ExerciseCategory> exerciseCategoryList) {
        mContext = context;
        mExerciseCategoryList = exerciseCategoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_exercise_category, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ExerciseCategory exerciseCategory = mExerciseCategoryList.get(position);
        holder.tvTitle.setText(exerciseCategory.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentExerciseCat = holder.getAdapterPosition();
                mListener.onExerciseCategoryItemClick(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        if (mCurrentExerciseCat == position) {
            holder.tvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorLightGreen));
        } else {
            holder.tvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));
        }
    }

    @Override
    public int getItemCount() {
        return mExerciseCategoryList.size();
    }

    public void setExerciseCategoryAdapterListener(ExerciseCategoryAdapterListener listener) {
        mListener = listener;
    }

    public void setCurrentExerciseCat(int pos) {
        mCurrentExerciseCat = pos;
        notifyDataSetChanged();
    }

    public void replaceAllData(List<ExerciseCategory> exerciseCategoryList) {
        mExerciseCategoryList = exerciseCategoryList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    interface ExerciseCategoryAdapterListener {
        void onExerciseCategoryItemClick(int pos);
    }
}
