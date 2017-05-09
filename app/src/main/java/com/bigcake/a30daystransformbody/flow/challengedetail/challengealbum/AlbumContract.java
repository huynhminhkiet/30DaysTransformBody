package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public interface AlbumContract {

    interface View {
        void showAllImages(List<ChallengeDayImage> challengeDayImageList);
        void showGifPanel();
        void showPhotoView(ChallengeDayImage challengeDayImage);
        void updateImageStatus(ChallengeDayImage challengeDayImage);
        void updateNumberSelected(int count);
        void updateAllSelectedButton(Boolean isOnSelectAll);
        void hideGifPanel();
        void setProgressDialog(boolean b);
        void showDelayDialog();
        void onChallengeDayImageOnBoardUpdated(ChallengeDay challengeDay);
        void updateChallengeImageOnAlbum(ChallengeDayImage challengeDayImage, int position);
        void addNewImageOnAlbum(ChallengeDayImage challengeDayImage, int position);
    }

    interface Presenter extends BasePresenter {
        void onImageLongClick(ChallengeDayImage challengeDayImage);
        void onImageClick(ChallengeDayImage challengeDayImage);
        void selectAll();
        void createGif(int delay);
        void closeGifPanel();
        void selectImageToUpdate(ChallengeDay challengeDay);
    }
}
