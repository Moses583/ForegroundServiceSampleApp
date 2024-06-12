package com.example.foregroundservicesampleapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException e){
                                Toast.makeText(MyService.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        ).start();

        final String CHANNEL_ID = "Foreground service";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_LOW);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
            Notification.Builder notification = new Notification.Builder(this,CHANNEL_ID)
                    .setContentText("Foreground service is running")
                    .setContentTitle("FROM SAMPLE SERVICE APP");
            startForeground(1001, notification.build());

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
