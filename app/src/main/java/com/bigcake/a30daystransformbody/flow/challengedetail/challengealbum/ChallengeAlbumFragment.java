package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

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
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.AlbumAdapterListener;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Big Cake on 4/14/2017
 */

public class ChallengeAlbumFragment extends BaseFragment implements AlbumContract.View, AlbumAdapterListener {
    private AlbumAdapter mAlbumAdapter;
    private RecyclerView rvAlbum;

    private AlbumContract.Presenter mPresenter;

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
        mAlbumAdapter = new AlbumAdapter(getContext(), new ArrayList<ChallengeDay>());
        mAlbumAdapter.setItemClickListener(this);
        rvAlbum.setAdapter(mAlbumAdapter);
    }
    @Override
    public void displayAllImages(List<ChallengeDay> challengeDayList) {
        mAlbumAdapter.replaceAllData(challengeDayList);
    }

    @Override
    public void onItemClick(ChallengeDay item, int position) {

    }

    @Override
    public void onItemLongClick(ChallengeDay challengeDay, int position) {

    }

    @Override
    public void openCreateGifPanel() {

    }
}
