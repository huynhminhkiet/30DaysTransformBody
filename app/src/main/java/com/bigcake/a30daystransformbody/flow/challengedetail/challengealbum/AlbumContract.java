package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeDay;

import java.util.List;

/**
 * Created by kiethuynh on 26/04/2017
 */

public interface AlbumContract {

    interface View extends BaseView<Presenter> {
        void displayAllImages(List<ChallengeDay> challengeDayList);
        void openCreateGifPanel();
    }

    interface Presenter extends BasePresenter {

    }
}
