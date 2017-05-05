package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.AlbumAdapterListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Big Cake on 4/14/2017
 */

public class ChallengeAlbumFragment extends BaseFragment implements AlbumContract.View, AlbumAdapterListener, View.OnClickListener {
    private AlbumAdapter mAlbumAdapter;
    private RecyclerView rvAlbum;
    private LinearLayout mGifPanel;
    private AlbumContract.Presenter mPresenter;
    private int mPositionSelected;
    private TextView tvNumberItemSelected;
    private TextView btnSelectAll, btnCreateGif, btnCancel;
    private ImageView photoView;
    private PhotoViewAttacher mPhotoViewAttacher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate");
        mPresenter = new AlbumPresenter(this, Injection.provideChallengeRepository(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_album, container, false);
        initViews(view);
        Log.d(getClass().getSimpleName(), "onCreateView");
        mPresenter.start();
        return view;
    }

    private void initViews(View view) {
        rvAlbum = (RecyclerView) view.findViewById(R.id.rv_album);
        rvAlbum.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAlbumAdapter = new AlbumAdapter(getContext(), new ArrayList<ChallengeDayImage>());
        mAlbumAdapter.setItemClickListener(this);
        rvAlbum.setAdapter(mAlbumAdapter);
        mGifPanel = (LinearLayout) view.findViewById(R.id.ln_gif_panel);
        tvNumberItemSelected = (TextView) view.findViewById(R.id.tv_number_item_selected);
        btnSelectAll = (TextView) view.findViewById(R.id.btn_all);
        btnSelectAll.setOnClickListener(this);
        btnCreateGif = (TextView) view.findViewById(R.id.btn_create_gif);
        btnCreateGif.setOnClickListener(this);
        btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        photoView = (ImageView) view.findViewById(R.id.photo_view);
        mPhotoViewAttacher = new PhotoViewAttacher(photoView);
    }
    @Override
    public void showAllImages(List<ChallengeDayImage> challengeDayImageList) {
        mAlbumAdapter.replaceAllData(challengeDayImageList);
    }

    @Override
    public void onItemClick(ChallengeDayImage item, int position) {
        mPositionSelected = position;
        mPresenter.onImageClick(item);
    }

    @Override
    public void onItemLongClick(ChallengeDayImage challengeDayImage, int position) {
        mPositionSelected = position;
        mPresenter.onImageLongClick(challengeDayImage);
    }

    @Override
    public void updateImageStatus(ChallengeDayImage challengeDayImage) {
        mAlbumAdapter.updateItem(challengeDayImage, mPositionSelected);
    }

    @Override
    public void updateNumberSelected(int count) {
        tvNumberItemSelected.setText(String.format(getContext().getString(R.string.gen_item_selected), count));
    }

    @Override

    public void showGifPanel() {
        mGifPanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all:
                mPresenter.selectAll();
                break;
            case R.id.btn_create_gif:
                mPresenter.createGif();
                break;
            case R.id.btn_cancel:
                mPresenter.closeGifPanel();
                break;
        }
    }

    @Override
    public void updateAllSelectedButton(Boolean isOnSelectAll) {
        btnSelectAll.setText(isOnSelectAll ? "All" : "D All");
    }

    @Override
    public void hideGifPanel() {
        mGifPanel.setVisibility(View.GONE);
    }

    @Override
    public void showPhotoView(ChallengeDayImage challengeDayImage) {
        rvAlbum.setVisibility(View.GONE);
        photoView.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(challengeDayImage.getChallengeDay().getImage()).into(photoView);
        mPhotoViewAttacher.update();
    }
}
