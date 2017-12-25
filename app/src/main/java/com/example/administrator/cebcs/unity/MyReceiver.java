package com.example.administrator.cebcs.unity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.cebcs.ShowNotificationActivity;

/**
 * Created by masterung on 25/12/2017 AD.
 */

public class MyReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, ShowNotificationActivity.class);

        String idSubject = intent.getStringExtra("idSubject");
        String detailString = intent.getStringExtra("Detail");
        intent1.putExtra("idSubject", idSubject);
        intent1.putExtra("Detail", detailString);

        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);


    }

}   // Main Class
