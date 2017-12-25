package com.example.administrator.cebcs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.ServiceActivity;
import com.example.administrator.cebcs.unity.MyAlert;
import com.example.administrator.cebcs.unity.MyConstant;
import com.example.administrator.cebcs.unity.MyGetAllData;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 11/9/2560.
 */

public class MainFrangment extends Fragment {

    private  String edtuserString, edtPasswordString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }   //onCreatView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

        //Login Controller
        loginController();

//        Forgot Password
        TextView textView = getView().findViewById(R.id.txtForgotPass);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentMain, new ForgotpasswordFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }   //onActivityCreat

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userFditText = getView().findViewById(R.id.edtuser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                edtuserString = userFditText.getText().toString().trim();
                edtPasswordString = passwordEditText.getText().toString().trim();

                if (edtuserString.equals("") || edtPasswordString.equals("")) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getResources().getString(R.string.message_have_space),
                            getResources().getString(R.string.message_have_space));

                } else {
                    checkUsrtAndFass();
                }

            }
        });
    }

    private void checkUsrtAndFass() {

        boolean bolStatus = true; // true ==> UserFalse
        MyConstant myConstant = new MyConstant();

        try {

            MyGetAllData myGetAllData = new MyGetAllData(getActivity());
            myGetAllData.execute(myConstant.getUrlgetUserString());
            String resultJSON = myGetAllData.get();
            Log.d("10novV1", "Result ==> " + resultJSON);
            String[] columnStrings = myConstant.getColumnUserStrings();
            String[] loginStrings = new String[columnStrings.length];
            MyAlert myAlert = new MyAlert(getActivity());

            JSONArray jsonArray = new JSONArray(resultJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (edtuserString.equals(jsonObject.getString(columnStrings[1]))) {

                    bolStatus = false;
                    for (int i1=0; i1<columnStrings.length; i1+=1) {

                        loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d("29novV2", "login[" + i1 + "] ==> " + loginStrings[i1]);

                    }

                }

            }   // for

            if (bolStatus) {
                myAlert.myDialog("ID False", "No This ID in my Database");
            } else if (edtPasswordString.equals(loginStrings[6])) {

                Toast.makeText(getActivity(), "Welcome " + loginStrings[2],
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                intent.putExtra("Login", loginStrings);
                getActivity().startActivity(intent);
                getActivity().finish();

            } else {

                myAlert.myDialog("Password False", "Please Try Again Password False");

            }




        } catch (Exception e) {
            e.printStackTrace();
        }



    }   // Main Class

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new RegisterFragment2())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
}   // Main Class
