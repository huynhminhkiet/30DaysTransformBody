package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.flow.photoviewer.PhotoViewerActivity;
import com.bigcake.a30daystransformbody.interfaces.AlbumAdapterListener;
import com.bigcake.a30daystransformbody.interfaces.ChallengeAlbumFragmentListener;
import com.bigcake.a30daystransformbody.interfaces.SetDelayDialogCallback;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
import com.bigcake.a30daystransformbody.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Big Cake on 4/14/2017
 */

public class ChallengeAlbumFragment extends BaseFragment implements AlbumContract.View, AlbumAdapterListener,
        View.OnClickListener{
    private AlbumAdapter mAlbumAdapter;
    private RecyclerView rvAlbum;
    private LinearLayout mGifPanel;
    private AlbumContract.Presenter mPresenter;
    private int mPositionSelected;
    private TextView tvNumberItemSelected;
    private ImageView btnSelectAll, btnCreateGif, btnCancel;
    private ProgressDialog mProgressDialog;
    public static final int REQUEST_CODE_CHALLENGE_DAY_ALBUM = 100;
    private ChallengeAlbumFragmentListener mListener;

    public static ChallengeAlbumFragment newInstance(Exercise exercise) {
        ChallengeAlbumFragment fragment = new ChallengeAlbumFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_EXERCISE, exercise);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ChallengeAlbumFragmentListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate");
        Exercise exercise = (Exercise) getArguments().getSerializable(Constants.EXTRA_EXERCISE);
        mPresenter = new AlbumPresenter(this, Injection.provideChallengeRepository(getContext()), exercise);
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

    @Override
    public void showDelayDialog() {
        SetDelayDialog setDelayDialog = SetDelayDialog.create(getContext(), new SetDelayDialogCallback() {
            @Override
            public void onDelaySetted(int delayValue) {
                mPresenter.createGif(delayValue);
            }
        });
        setDelayDialog.show();
    }

    @Override
    public void onChallengeDayImageOnBoardUpdated(ChallengeDay challengeDay) {
        mPresenter.selectImageToUpdate(challengeDay);
    }

    @Override
    public void addNewImageOnAlbum(ChallengeDayImage challengeDayImage, int position) {
        mAlbumAdapter.insertItem(challengeDayImage, position);
    }

    @Override
    public void updateChallengeImageOnAlbum(ChallengeDayImage challengeDayImage, int position) {
        mAlbumAdapter.updateItem(challengeDayImage, position);
    }

    @Override
    public void setProgressDialog(boolean b) {
        if (b)
            mProgressDialog.show();
        else
            mProgressDialog.dismiss();
    }

    private void initViews(View view) {
        rvAlbum = (RecyclerView) view.findViewById(R.id.rv_album);
        rvAlbum.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAlbumAdapter = new AlbumAdapter(getContext(), new ArrayList<ChallengeDayImage>(),
                ChallengeImageManager.getInstance(Injection.provideChallengeRepository(getContext())));
        mAlbumAdapter.setItemClickListener(this);
        rvAlbum.setAdapter(mAlbumAdapter);
        mGifPanel = (LinearLayout) view.findViewById(R.id.ln_gif_panel);
        tvNumberItemSelected = (TextView) view.findViewById(R.id.tv_number_item_selected);
        btnSelectAll = (ImageView) view.findViewById(R.id.btn_all);
        btnSelectAll.setOnClickListener(this);
        btnCreateGif = (ImageView) view.findViewById(R.id.btn_create_gif);
        btnCreateGif.setOnClickListener(this);
        btnCancel = (ImageView) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Creating..");
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
    public void createChangeImageDone(ChallengeImage challengeImage) {
        mListener.onChangeImageCreated(challengeImage);
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
                showDelayDialog();
                break;
            case R.id.btn_cancel:
                mPresenter.closeGifPanel();
                break;
        }
    }

    @Override
    public void updateAllSelectedButton(Boolean isOnSelectAll) {
        btnSelectAll.setImageResource(isOnSelectAll ? R.drawable.ic_select_all : R.drawable.ic_non_all);
    }

    @Override
    public void hideGifPanel() {
        mGifPanel.setVisibility(View.GONE);
    }

    @Override
    public void showPhotoView(ChallengeDayImage challengeDayImage) {
        Intent intent = new Intent(getActivity(), PhotoViewerActivity.class);
        intent.putExtra(Constants.FLOW_PHOTO_VIEWER, Constants.TAG_CHALLENGE_ALBUM);
        intent.putExtra(Constants.EXTRA_CHALLENGE_DAY, challengeDayImage.getChallengeDay());
        startActivityForResult(intent, REQUEST_CODE_CHALLENGE_DAY_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHALLENGE_DAY_ALBUM) {
            if(resultCode == Activity.RESULT_OK){
                mAlbumAdapter.deleteItem(mPositionSelected);
                mListener.onChallengeDayImageDeleted((ChallengeDay) data.getSerializableExtra(Constants.EXTRA_CHALLENGE_DAY));
            }
        }
    }
}
