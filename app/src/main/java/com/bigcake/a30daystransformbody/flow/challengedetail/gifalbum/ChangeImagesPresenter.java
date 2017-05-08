package com.bigcake.a30daystransformbody.flow.challengedetail.gifalbum;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.AlbumContract;
import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.ChallengeDayImage;
import com.bigcake.a30daystransformbody.utils.AnimatedGifEncoder;
import com.bigcake.a30daystransformbody.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public class ChangeImagesPresenter implements ChangeImagesContract.Presenter {
    private ChangeImagesContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private List<ChallengeImage> mChallengeImages;

    public ChangeImagesPresenter(ChangeImagesContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
    }

    @Override
    public void start() {
        if (mChallengeImages == null)
            mChallengeRepository.getAllChangeImages(0, new ChallengeDataSource.GetChangeImagesCallback() {
                @Override
                public void onSuccess(List<ChallengeImage> challengeImageList) {
                    mChallengeImages = challengeImageList;
                    mView.showAllChangeImages(challengeImageList);
                }

                @Override
                public void onError() {

                }
            });
        else
            mView.showAllChangeImages(mChallengeImages);
    }
}
