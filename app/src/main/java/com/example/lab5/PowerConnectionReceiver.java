package com.example.lab5;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private boolean isCharging;
    private String chargingStatus;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent newIntent = new Intent(context, MainActivity.class);
        //newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        isCharging = MainActivity.getIsCharging();
        newIntent.putExtra("status", isCharging);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newIntent, 0);

        if(isCharging) {
            chargingStatus = "charging";
        }
        else {
            chargingStatus = "not charging";
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "channelID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Charging status changed!")
                .setContentText(chargingStatus)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, mBuilder.build());

    }
}
