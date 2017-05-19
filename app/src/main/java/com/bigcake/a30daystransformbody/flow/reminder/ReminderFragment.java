package com.bigcake.a30daystransformbody.flow.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bigcake.a30daystransformbody.Injection;
import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.base.BaseFragment;
import com.bigcake.a30daystransformbody.utils.Constants;
import com.bigcake.a30daystransformbody.utils.Utils;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Big Cake on 5/19/2017
 */

public class ReminderFragment extends BaseFragment implements ReminderContract.View {
    private Button btnSave;
    private TimePicker timePicker;
    private ToggleButton toggleButton;

    private ReminderContract.Presenter mPresenter;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public static ReminderFragment newInstance() {
        return new ReminderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ReminderPresenter(this, Injection.providePreferencesData(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        initViews(view);
        mPresenter.start();
        return view;
    }

    private void initViews(View view) {
        bindViews(view);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.saveReminder(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), toggleButton.isChecked());
            }
        });
    }

    private void bindViews(View view) {
        btnSave = (Button) view.findViewById(R.id.btn_save);
        timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggle);
    }

    @Override
    public void setPresenter(ReminderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showReminder(int hour, int minute, boolean status) {
        if (hour != -1) {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
        toggleButton.setChecked(status);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNotification(int hour, int minute) {
        alarmMgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), NotificationPublisher.class);
        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmMgr.cancel(alarmIntent);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    @Override
    public void cancelReminder() {
        alarmMgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), NotificationPublisher.class);
        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmMgr.cancel(alarmIntent);
    }
}
