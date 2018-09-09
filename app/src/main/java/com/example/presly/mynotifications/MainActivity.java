package com.example.presly.mynotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private NotificationManager mNotificationMananger;
private int notificationID = 100;
private int numMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button)findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        Button cancelBtn = (Button)findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });

        Button updateBtn = (Button)findViewById(R.id.update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });
    }

    protected void displayNotification(){
        Log.i("Start", "notification");

        /* Invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Messsage");
        mBuilder.setContentText("You Have Received A New Message");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.alerticon);

        /* increase notification every time a notification arrives */
        mBuilder.setNumber(++numMessages);

        /* create an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

        /* adds the intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationMananger = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        /* notificationID allows you to update the notification later on  */
       mNotificationMananger.notify(notificationID, mBuilder.build());

    }

    protected void cancelNotification(){
        Log.i("Cancel", "notification");
        mNotificationMananger.cancel(notificationID);
    }

    protected void updateNotification(){
        Log.i("Update", "notification");
        /* invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Update Message");
        mBuilder.setContentText("You've got updated message");
        mBuilder.setTicker("Updated Message Alert");
        mBuilder.setSmallIcon(R.drawable.alerticon);

        /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

        /*  creates an explicit intent for the activity in your app */
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

        /* adds the intent that starts the activity to the top of the stack  */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationMananger = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        /* Update the existing notification using the same notification ID  */
        mNotificationMananger.notify(notificationID, mBuilder.build());

    }
}
