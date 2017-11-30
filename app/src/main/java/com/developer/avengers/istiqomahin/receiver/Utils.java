package com.developer.avengers.istiqomahin.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.act_main.MainActivity;

public class Utils {

    public static NotificationManager mManager;


    @SuppressWarnings("static-access")
    public static void generateNotification(Context context) {

        android.support.v7.app.NotificationCompat.Builder nb = new android.support.v7.app.NotificationCompat.Builder(context);
        nb.setSmallIcon(R.drawable.ic_launcher);
        nb.setContentTitle("Selamat malam :)");
        nb.setContentText("Jangan lupa isi capaian ibadahmu ya :)");
        nb.setTicker("Lihat");

        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        android.support.v7.app.NotificationCompat.BigPictureStyle s = new android.support.v7.app.NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Jangan lupa isi capaian ibadahmu ya :)");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());


    }
}