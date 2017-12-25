package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.unity.GetCEwhereIDstudent;
import com.example.administrator.cebcs.unity.GetSubjectWhereIdSubject;
import com.example.administrator.cebcs.unity.MyAdapter;
import com.example.administrator.cebcs.unity.MyConstant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ASUS on 11/11/2560.
 */

public class CEdetailFragment extends Fragment {

    private String[] loginStrings;
    private String tag = "29novV3";

    public static CEdetailFragment ceDetailInstance(String[] loginStrings) {

        CEdetailFragment cEdetailFragment = new CEdetailFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        cEdetailFragment.setArguments(bundle);
        return cEdetailFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");
        Log.d(tag, "idSt2 ==>" + loginStrings[2]);

//        Create ListView
        createListView();


    }   // Main Method

    private void createListView() {

        ListView listView = getView().findViewById(R.id.livCE);
        MyConstant myConstant = new MyConstant();

        try {

            GetCEwhereIDstudent getCEwhereIDstudent = new GetCEwhereIDstudent(getActivity());
            getCEwhereIDstudent.execute(loginStrings[2], myConstant.getUrlGetCEwhereIDstudent());
            String resultJSON = getCEwhereIDstudent.get();
            Log.d(tag, "resultJSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String ceString = jsonObject.getString("CE");
            Log.d(tag, "ce ==> " + ceString);


            ceString = ceString.replace("[", "");
            ceString = ceString.replace("]", "");
            Log.d(tag, "ce ที่ตัด [] แล้ว ==> " + ceString);

            String[] ceStrings = ceString.split(",");
            String[] ceSubjectStrings = new String[ceStrings.length];
            String[] numberStrings = new String[ceStrings.length];
            int intNumber = 0;

            for (int i=0; i<ceStrings.length; i+=1) {
                Log.d(tag, "ceStrings[" + i + "] ==> " + ceStrings[i]);
                ceSubjectStrings[i] = findSubject(ceStrings[i]);
                Log.d(tag, "Subject[" + i + "] ==> " + ceSubjectStrings[i]);
                numberStrings[i] = Integer.toString(intNumber += 1);
            }

            MyAdapter myAdapter = new MyAdapter(getActivity(), ceStrings, ceSubjectStrings, numberStrings);
            listView.setAdapter(myAdapter);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String findSubject(String ceString) {

        try {

            String result = "";
            MyConstant myConstant = new MyConstant();
            GetSubjectWhereIdSubject getSubjectWhereIdSubject = new GetSubjectWhereIdSubject(getActivity());
            getSubjectWhereIdSubject.execute(ceString, myConstant.getUrlGetSubjectWhereIdSub());
            String strJSON = getSubjectWhereIdSubject.get();
            Log.d("29novV4", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            result = jsonObject.getString("Subject");
            getSubjectWhereIdSubject.cancel(true);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ce_detail, container, false);
        return view;
    }
}   // Main Class
