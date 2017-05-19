package com.bigcake.a30daystransformbody.flow.reminder;

import com.bigcake.a30daystransformbody.base.BasePresenter;
import com.bigcake.a30daystransformbody.base.BaseView;

/**
 * Created by Big Cake on 5/19/2017
 */

public interface ReminderContract {
    interface Presenter extends BasePresenter {
        void saveReminder(int hour, int minute, boolean status);
    }

    interface View extends BaseView<Presenter> {
        void showReminder(int hour, int minute, boolean status);
        void showMessage(String message);
        void setNotification(int hour, int minute);
        void cancelReminder();
    }
}
