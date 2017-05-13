package com.bigcake.a30daystransformbody.flow.challengedetail.changealbum;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;
import com.bigcake.a30daystransformbody.data.ChallengeImage;

import java.util.List;

/**
 * Created by Big Cake on 5/9/2017
 */

public interface ChangeImagesContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void showAllChangeImages(List<ChallengeImage> challengeImageList);
    }
}
