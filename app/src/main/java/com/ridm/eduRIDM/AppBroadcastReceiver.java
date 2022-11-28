package com.ridm.eduRIDM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppBroadcastReceiver extends BroadcastReceiver {
    private int notificationID;
    private String description;

    public AppBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        description = intent.getStringExtra("Notif Desc - Receiver");
        notificationID = intent.getExtras().getInt("Notif ID - Receiver");

        Intent appIntent = new Intent(context, AppIntentService.class);
        appIntent.putExtra("Notif desc", description);
        appIntent.putExtra("Notif ID", notificationID);
        context.startService(appIntent);
    }
}
