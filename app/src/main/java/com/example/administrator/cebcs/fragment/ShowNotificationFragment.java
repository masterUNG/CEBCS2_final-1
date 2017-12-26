package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.unity.GetSubjectWhereIdSubject;
import com.example.administrator.cebcs.unity.MyConstant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by masterung on 25/12/2017 AD.
 */

public class ShowNotificationFragment extends Fragment{

    private String idSubjectString, detailString, dateString;

    public static ShowNotificationFragment showNotiInstance(String idSubjectString,
                                                            String detailString,
                                                            String dateString) {

        ShowNotificationFragment showNotificationFragment = new ShowNotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idSubject", idSubjectString);
        bundle.putString("Detail", detailString);
        bundle.putString("Date", dateString);
        showNotificationFragment.setArguments(bundle);

        return showNotificationFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idSubjectString = getArguments().getString("idSubject");
        detailString = getArguments().getString("Detail");
        dateString = getArguments().getString("Date");

//        Show Subject
        showSubject();

//        Show Date
        showDate();

//        Show Detail
        showDetail();


    }   // Main Method

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
