package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.data.source.ChallengeDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.utils.AnimatedGifEncoder;
import com.bigcake.a30daystransformbody.utils.FileUtils;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public class AlbumPresenter implements AlbumContract.Presenter {
    private AlbumContract.View mView;
    private ChallengeRepository mChallengeRepository;
    private List<ChallengeDayImage> mChallengeDayImageList;
    private boolean isGifPanelShown, isSelectAll;
    private int mNumberItemSelected;

    public AlbumPresenter(AlbumContract.View view, ChallengeRepository challengeRepository) {
        this.mView = view;
        this.mChallengeRepository = challengeRepository;
        isSelectAll = true;
    }

    @Override
    public void start() {
        if (mChallengeDayImageList == null)
            mChallengeRepository.getChallengeDays(0, new ChallengeDataSource.LoadChallengeDaysCallBack() {
                @Override
                public void onChallengeDaysLoaded(List<ChallengeDay> challengeDayList) {
                    filterChallengeDaysHasImage(challengeDayList);
                    mView.showAllImages(mChallengeDayImageList);
                }

                @Override
                public void onError() {

                }
            });
        else
            mView.showAllImages(mChallengeDayImageList);
    }

    @Override
    public void onImageLongClick(ChallengeDayImage challengeDayImage) {
        isGifPanelShown = true;
        mView.showGifPanel();
        updateImageStatus(challengeDayImage);
        updateNumberSelected(challengeDayImage);
    }

    @Override
    public void onImageClick(ChallengeDayImage challengeDayImage) {
        if (isGifPanelShown) {
            updateImageStatus(challengeDayImage);
            updateNumberSelected(challengeDayImage);
        } else {
            mView.showPhotoView(challengeDayImage);
        }
    }

    private void filterChallengeDaysHasImage(List<ChallengeDay> challengeDayList) {
        mChallengeDayImageList = new ArrayList<>();
        for (ChallengeDay challengeDay : challengeDayList)
            if (challengeDay.getImage() != null && !challengeDay.getImage().isEmpty())
                mChallengeDayImageList.add(new ChallengeDayImage(challengeDay, ChallengeDayImage.NOT_SELECTED));
    }

    private void updateImageStatus(ChallengeDayImage challengeDayImage) {
        int status = challengeDayImage.getStatus() == ChallengeDayImage.SELECTED ? ChallengeDayImage.NOT_SELECTED :
                ChallengeDayImage.SELECTED;
        challengeDayImage.setStatus(status);
        mView.updateImageStatus(challengeDayImage);
    }

    private void updateNumberSelected(ChallengeDayImage challengeDayImage) {
        if (isGifPanelShown)
            if (challengeDayImage.getStatus() == ChallengeDayImage.SELECTED) {
                mView.updateNumberSelected(++mNumberItemSelected);
            } else {
                mView.updateNumberSelected(--mNumberItemSelected);
            }
    }

    @Override
    public void selectAll() {
        selectAllOrUnSelectAll(isSelectAll);
        if (isSelectAll)
            mView.updateNumberSelected(mChallengeDayImageList.size());
        else
            mView.updateNumberSelected(0);
        isSelectAll = !isSelectAll;
        mView.updateAllSelectedButton(isSelectAll);
        mView.showAllImages(mChallengeDayImageList);
    }

    @Override
    public void createGif(final int delay) {
        mView.setProgressDialog(true);
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                final int[] status = new int[1];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                AnimatedGifEncoder encoder = new AnimatedGifEncoder();
                encoder.setQuality(20);
                encoder.setRepeat(0);
                encoder.setDelay(delay);
                encoder.start(bos);
                final byte[][] firstImage = {null};
                for (int i = 0; i < mChallengeDayImageList.size(); i++) {
                    ChallengeDayImage challengeDayImage = mChallengeDayImageList.get(i);
                    if (challengeDayImage.getStatus() == ChallengeDayImage.SELECTED) {
                        byte[] image = FileUtils.loadImage(challengeDayImage.getChallengeDay().getImage());
                        if (i == 0)
                            mChallengeRepository.getChallengeDayThumbnail(challengeDayImage.getChallengeDay().getId(), new ChallengeDataSource.LoadChallengeDayThumbnailCallback() {
                                @Override
                                public void onChallengeDayThumbnailLoaded(byte[] thumbnail) {
                                    firstImage[0] = thumbnail;
                                }

                                @Override
                                public void onDataNotAvailable() {

                                }
                            });
                        encoder.addFrame(BitmapFactory.decodeByteArray(image, 0, image.length));
                    }
                }
                encoder.finish();

                mChallengeRepository.insertChallengeGif(mChallengeDayImageList.get(0).getChallengeDay().getChallengeId(),
                        bos.toByteArray(), firstImage[0], new ChallengeDataSource.ChallengeCallBack() {
                            @Override
                            public void onSuccess() {
                                status[0] = 1;
                            }

                            @Override
                            public void onError() {
                                status[0] = 0;
                            }
                        });
                return status[0];
            }

            @Override
            protected void onPostExecute(Integer status) {
                super.onPostExecute(status);
                mView.setProgressDialog(false);
            }
        }.execute();
    }

    @Override
    public void closeGifPanel() {
        selectAllOrUnSelectAll(false);
        isSelectAll = true;
        isGifPanelShown = false;
        mNumberItemSelected = 0;
        mView.hideGifPanel();
        mView.showAllImages(mChallengeDayImageList);
    }

    private void selectAllOrUnSelectAll(boolean isSelectAll) {
        for (ChallengeDayImage challengeDayImage : mChallengeDayImageList)
            challengeDayImage.setStatus(isSelectAll ? ChallengeDayImage.SELECTED : ChallengeDayImage.NOT_SELECTED);
    }
}
