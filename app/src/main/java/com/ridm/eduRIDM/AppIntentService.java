package com.ridm.eduRIDM;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

public class AppIntentService extends IntentService {
    private int notificationID;
    private String description;

    public AppIntentService() {
        super("AppIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationID = Integer.parseInt(intent.getStringExtra("Notif ID"));
        description = intent.getStringExtra("Notif desc");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("eduRIDM");
        builder.setContentText(description);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);

        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 13, notifyIntent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notificationID, notification);
    }
}
