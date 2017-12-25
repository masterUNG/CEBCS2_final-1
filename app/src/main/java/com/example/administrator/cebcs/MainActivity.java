package com.example.administrator.cebcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.cebcs.fragment.MainFrangment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Frangment to Activity
        if (savedInstanceState==null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentMain,new MainFrangment())
                    .commit();
        }

    }   //Main Method

}//Main Class
