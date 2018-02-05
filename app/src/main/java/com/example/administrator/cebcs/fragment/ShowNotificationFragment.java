package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.unity.GetNotiWhereIdSubject;
import com.example.administrator.cebcs.unity.GetSubjectWhereIdSubject;
import com.example.administrator.cebcs.unity.MyConstant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by masterung on 25/12/2017 AD.
 */

public class ShowNotificationFragment extends Fragment {

    private String idSubjectString, detailString,
            dateString, idSt2String, firstNotiString,
            secondNotiString, lastNotiString, labelButtonString;
    private String columnString = null;

    public static ShowNotificationFragment showNotiInstance(String idSubjectString,
                                                            String detailString,
                                                            String dateString,
                                                            String idSt2String) {

        ShowNotificationFragment showNotificationFragment = new ShowNotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idSubject", idSubjectString);
        bundle.putString("Detail", detailString);
        bundle.putString("Date", dateString);
        bundle.putString("idSt2", idSt2String);
        showNotificationFragment.setArguments(bundle);

        return showNotificationFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idSubjectString = getArguments().getString("idSubject");
        detailString = getArguments().getString("Detail");
        dateString = getArguments().getString("Date");
        idSt2String = getArguments().getString("idSt2");

//        Show Subject
        showSubject();

//        Show Date
        showDate();

//        Show Detail
        showDetail();

//        Submit Controller
        submitController();

    }   // Main Method

    private void submitController() {

        String tag = "5FebV1";
        Log.d(tag, "idSt2 ==> " + idSt2String);
        Log.d(tag, "idSubject ==> " + idSubjectString);


        try {

            Button button = getView().findViewById(R.id.btnSubmit);
            MyConstant myConstant = new MyConstant();
            GetNotiWhereIdSubject getNotiWhereIdSubject = new GetNotiWhereIdSubject(getActivity());
            getNotiWhereIdSubject.execute(idSubjectString, idSt2String,
                    myConstant.getUrlGetNotiWhereIdSubject());

            String jsonString = getNotiWhereIdSubject.get();
            Log.d(tag, "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            firstNotiString = jsonObject.getString("FirstNoti");
            secondNotiString = jsonObject.getString("SecondNoti");
            lastNotiString = jsonObject.getString("LastNoti");

            if (Integer.parseInt(firstNotiString) == 0) {
                labelButtonString = "First Notification";
                columnString = "FirstNoti";
            } else {
                if (Integer.parseInt(secondNotiString) == 0) {
                    labelButtonString = "Second Notification";
                    columnString = "SecondNoti";
                } else {
                    if (Integer.parseInt(lastNotiString) == 0) {
                        labelButtonString = "Last Notification";
                        columnString = "LastNoti";
                    } else {
                        labelButtonString = "No Notification";
                    }
                }
            }

            button.setText(labelButtonString);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editStatus(columnString);
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void editStatus(String columnString) {

        try {




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtDate);
        textView.setText("Date : " + dateString);
    }

    private void showDetail() {
        TextView textView = getView().findViewById(R.id.txtDetail);
        textView.setText("Detail : " + detailString);
    }

    private void showSubject() {
        try {

            MyConstant myConstant = new MyConstant();
            GetSubjectWhereIdSubject getSubjectWhereIdSubject = new GetSubjectWhereIdSubject(getActivity());
            getSubjectWhereIdSubject.execute(idSubjectString,
                    myConstant.getUrlGetSubjectWhereIdSub());

            String resultJSON = getSubjectWhereIdSubject.get();
            JSONArray jsonArray = new JSONArray(resultJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            TextView textView = getView().findViewById(R.id.txtSubject);
            textView.setText("Subject : " + jsonObject.getString("Subject"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_notifiction, container, false);
        return view;
    }
}   // Main Class
