package com.bigcake.a30daystransformbody.flow.reminder;

import com.bigcake.a30daystransformbody.data.source.PreferencesData;
import com.bigcake.a30daystransformbody.utils.Constants;

/**
 * Created by Big Cake on 5/19/2017
 */

public class ReminderPresenter implements ReminderContract.Presenter {
    private ReminderContract.View mView;
    private PreferencesData mPreferencesData;

    public ReminderPresenter(ReminderContract.View mView, PreferencesData preferencesData) {
        this.mView = mView;
        this.mPreferencesData = preferencesData;
    }

    @Override
    public void start() {
        String timeString = mPreferencesData.getString(Constants.REMINDER_TIME, "");
        boolean status = mPreferencesData.getBoolean(Constants.REMINDER_STATUS, false);
        int hour, minute;
        if (timeString.isEmpty()) {
            hour = -1;
            minute = 0;
        } else {
            String [] strArr = timeString.split(":");
            hour = Integer.parseInt(strArr[0]);
            minute = Integer.parseInt(strArr[1]);
        }
        mView.showReminder(hour, minute, status);
    }

    @Override
    public void saveReminder(int hour, int minute, boolean status) {
        mPreferencesData.putBoolean(Constants.REMINDER_STATUS, status);
        mPreferencesData.putString(Constants.REMINDER_TIME, hour + ":" + minute);
        if (status) {
            mView.setNotification(hour, minute);
            mView.showMessage("Reminder ON!");
        } else {
            mView.cancelReminder();
            mView.showMessage("Reminder OFF!");
        }
    }
}
