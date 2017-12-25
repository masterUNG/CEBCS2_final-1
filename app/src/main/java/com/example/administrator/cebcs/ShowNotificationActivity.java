package com.example.administrator.cebcs;

import android.app.Notification;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.administrator.cebcs.fragment.ShowNotificationFragment;

public class ShowNotificationActivity extends AppCompatActivity {

    private String idSubjectString, detailString;
    private String tag = "25DecV2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        idSubjectString = getIntent().getStringExtra("idSubject");
        detailString = getIntent().getStringExtra("Detail");
        Log.d(tag, "idSubject ==> " + idSubjectString);
        Log.d(tag, "Detail ==> " + detailString);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentShowNotiFragment,
                            ShowNotificationFragment.showNotiInstance(idSubjectString, detailString))
                    .commit();
        }


        showNoti();

    }   // Main Method

    private void showNoti() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_alert);
        builder.setTicker("CEBCS Notification");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Title");
        builder.setContentText("Conent");
        builder.setAutoCancel(false);


        Uri soundUri = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        builder.setSound(soundUri);

        android.app.Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);


    }


}   // Main Class
