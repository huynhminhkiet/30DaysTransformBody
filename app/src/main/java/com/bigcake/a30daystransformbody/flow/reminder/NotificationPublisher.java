package com.bigcake.a30daystransformbody.flow.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.flow.main.MainActivity;
import com.bigcake.a30daystransformbody.flow.splash.SplashActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Big Cake on 5/18/2017
 */

public class NotificationPublisher extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("30 Days Transform Body")
                        .setContentText("It's is the time to do your challenges!")
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
