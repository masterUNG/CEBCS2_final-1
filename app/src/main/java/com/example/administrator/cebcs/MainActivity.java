package com.example.administrator.cebcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.cebcs.fragment.MainFrangment;
import com.example.administrator.cebcs.unity.MyConstant;
import com.example.administrator.cebcs.unity.MyGetAllData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Frangment to Activity
        addFragment(savedInstanceState);



    }   //Main Method



    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentMain, new MainFrangment())
                    .commit();
        }
    }

}//Main Class
