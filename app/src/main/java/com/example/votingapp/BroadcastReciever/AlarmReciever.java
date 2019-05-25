package com.example.votingapp.BroadcastReciever;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.votingapp.MainActivity;
import com.example.votingapp.R;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager  = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_UNSPECIFIED);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(when)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker("Quiz")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Online Quiz App")
                .setContentText("Hey challange awaits !")
                .setContentIntent(pendingIntent)
                .setContentInfo("Test your knowledge");

        notificationManager.notify(/*notification id*/0, notificationBuilder.build());

        /*android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Online Quiz App")
                .setContentText("Hey challange awaits !")
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000,1000,1000,1000,1000});
        notificationIntent.notify(0,builder.build());*/
    }
}
