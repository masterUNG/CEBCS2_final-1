package com.example.administrator.cebcs;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.cebcs.fragment.CEdetailFragment;
import com.example.administrator.cebcs.fragment.DetailStudenFragment;
import com.example.administrator.cebcs.fragment.ServiceFragment;
import com.example.administrator.cebcs.unity.GetNotiWhereID;
import com.example.administrator.cebcs.unity.MyConstant;
import com.example.administrator.cebcs.unity.MyGetAllData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private String[] loginStrings;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        loginStrings = getIntent().getStringArrayExtra("Login");

//        Check Notification
        checkNotification();

//        Show Text
        showText();

//        Create Toolber
        createToolber();

//        Add Fragmant
        addFragmant(savedInstanceState);


//        Exit Controller
        exitController();

//        Detail Controller
        detailController();

//        Home Controller
        homeController();

//        CE Controller
        CEController();


    }   // Main Method


    private void checkNotification() {

        MyConstant myConstant = new MyConstant();
        String tag = "25DecV1";

        try {

            GetNotiWhereID getNotiWhereID = new GetNotiWhereID(ServiceActivity.this);
            getNotiWhereID.execute(loginStrings[2],
                    myConstant.getUrlGetNotificationWhereIdStudentString());
            String resultJSON = getNotiWhereID.get();
            Log.d(tag, "JSON ==> " + resultJSON);

            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String currentDateString = dateFormat.format(calendar.getTime());
            Log.d(tag, "currentTime ==> " + currentDateString);



        } catch (Exception e) {
            Log.d(tag, "e ==> " + e.toString());
        }

    }


    private void CEController() {
        TextView textView = (TextView) findViewById(R.id.txtCEdetail);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentService123, CEdetailFragment.ceDetailInstance(loginStrings)).commit();
                drawerLayout.closeDrawers();

            }
        });
    }

    private void homeController() {
        TextView textView = (TextView) findViewById(R.id.txtHome);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentService123, ServiceFragment.serviceInstance(loginStrings)).commit();
                drawerLayout.closeDrawers();
            }
        });
    }


    @Override
    public void onBackPressed() {

    }

    private void detailController() {
        TextView textView = (TextView) findViewById(R.id.txtDatailStudent);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentService123, DetailStudenFragment.detailStudentInstance(loginStrings)).commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void exitController() {
        TextView textView = (TextView) findViewById(R.id.txtExit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addFragmant(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFragmentService123, ServiceFragment.serviceInstance(loginStrings)).commit();
        }
    }

    private void createToolber() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolberService);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("เมนู");

        drawerLayout = (DrawerLayout) findViewById(R.id.myDrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }   // create Toolber

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showText() {

        TextView nameTextView = (TextView) findViewById(R.id.txtName);
        TextView idTextView = (TextView) findViewById(R.id.txtID);

        nameTextView.setText("ชื่อ " + loginStrings[3] + " " + loginStrings[4]);
        idTextView.setText("รหัสนักศึกษา " + loginStrings[1]);


    }

}   // Main Class
