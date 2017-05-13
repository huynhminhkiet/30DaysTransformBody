package com.bigcake.a30daystransformbody.flow.challengedetail.changealbum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.flow.photoviewer.PhotoViewerActivity;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class ChangeFragment extends BaseFragment implements ChangeImagesContract.View, ItemClickListener<ChallengeImage> {
    private static final int REQUEST_CHANGE_IMAGE_ALBUM = 101;
    private ChangeImagesContract.Presenter mPresenter;
    private RecyclerView rvChangeImages;
    private ChangeImageAdapter mChangeImageAdapter;
    private int mPositionSelected;

    public static ChangeFragment newInstance(Exercise exercise) {
        ChangeFragment fragment = new ChangeFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_EXERCISE, exercise);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate");
        Exercise exercise = (Exercise) getArguments().getSerializable(Constants.EXTRA_EXERCISE);
        mPresenter = new ChangeImagesPresenter(this, Injection.provideChallengeRepository(getContext()), exercise);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gif_album, container, false);
        Log.d(getClass().getSimpleName(), "onCreateView");
        initViews(view);
        mPresenter.start();
        return view;
    }

    private void initViews(View view) {
        rvChangeImages = (RecyclerView) view.findViewById(R.id.rv_change_album);
        rvChangeImages.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mChangeImageAdapter = new ChangeImageAdapter(getContext(), new ArrayList<ChallengeImage>(),
                ChallengeImageManager.getInstance(Injection.provideChallengeRepository(getContext())));
        mChangeImageAdapter.setItemClickListener(this);
        rvChangeImages.setAdapter(mChangeImageAdapter);
    }

    @Override
    public void setPresenter(ChangeImagesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAllChangeImages(List<ChallengeImage> challengeImageList) {
        mChangeImageAdapter.replaceAllData(challengeImageList);
    }

    @Override
    public void onItemClick(ChallengeImage item, int position) {
        mPositionSelected = position;
        Intent intent = new Intent(getActivity(), PhotoViewerActivity.class);
        intent.putExtra(Constants.FLOW_PHOTO_VIEWER, Constants.TAG_CHAGE_IMAGE);
        intent.putExtra(Constants.EXTRA_CHALLENGE_IMAGE, item);
        startActivityForResult(intent, REQUEST_CHANGE_IMAGE_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHANGE_IMAGE_ALBUM) {
            if(resultCode == Activity.RESULT_OK){
                mChangeImageAdapter.deleteItem(mPositionSelected);
            }
        }
    }

    public void refreshNewImage(ChallengeImage challengeImage) {
        mChangeImageAdapter.addNewImage(challengeImage);
    }
}
