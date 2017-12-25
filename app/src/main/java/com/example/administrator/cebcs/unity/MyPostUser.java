package com.example.administrator.cebcs.unity;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by ASUS on 2/10/2560.
 */

public class MyPostUser extends AsyncTask<String, Void, String>{

    private Context context;

    public MyPostUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("idStudent", strings[0])
                    .add("Name", strings[1])
                    .add("Surname", strings[2])
                    .add("Gender", strings[3])
                    .add("Password", strings[4])
                    .add("Major", strings[5])
                    .add("Sector", strings[6])
                    .add("Class", strings[7])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[8]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
