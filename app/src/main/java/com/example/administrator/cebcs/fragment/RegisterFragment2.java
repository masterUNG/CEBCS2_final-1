package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cebcs.MainActivity;
import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.unity.EditPassWherIdStudent;
import com.example.administrator.cebcs.unity.MyAlert;
import com.example.administrator.cebcs.unity.MyConstant;
import com.example.administrator.cebcs.unity.MyGetAllData;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by masterung on 29/11/2017 AD.
 */

public class RegisterFragment2 extends Fragment {

    private String idStudentString, passwordString, rePasswordString;
    private boolean statusABoolean = true; //true ==> สมัครไปแล้ว false ==> ยังไม่เคยสมัคร


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Toolbar
        toolbar();

//        Save Controller
        saveController();


    }   // Main Method

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText idStudentEditText = getView().findViewById(R.id.edtIDstudent);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);
                EditText rePasswordEditText = getView().findViewById(R.id.edtRePassword);

                idStudentString = idStudentEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                rePasswordString = rePasswordEditText.getText().toString().trim();

                MyAlert myAlert = new MyAlert(getActivity());

                if (idStudentString.isEmpty() || passwordString.isEmpty() || rePasswordString.isEmpty()) {
//                    Have Space
                    myAlert.myDialog(getString(R.string.have_space), getString(R.string.message_have_space));
                } else if (!(idStudentString.length() == 13)) {
//                    idStudentString Not 13digi
                    myAlert.myDialog(getString(R.string.id_false), getString(R.string.mess_id_false));
                } else if (checkIdStudent()) {
//                    true ==> idStudentString ไม่มีในฐานข้อมูล
                    myAlert.myDialog(getString(R.string.id_false), getString(R.string.message_no_id));
                } else if (statusABoolean) {
                    //true ==> สมัครไปแล้ว
                    myAlert.myDialog(getString(R.string.id_false), getString(R.string.id_registed));
                } else if (!(passwordString.equals(rePasswordString))) {
//                    Password ไม่ตรงกัน
                    myAlert.myDialog(getString(R.string.pass_false), getString(R.string.mass_pass_false));
                } else {
                    registerPassword();
                }




            }
        });
    }

    private void registerPassword() {

        try {

            MyConstant myConstant = new MyConstant();
            EditPassWherIdStudent editPassWherIdStudent = new EditPassWherIdStudent(getActivity());
            editPassWherIdStudent.execute(idStudentString, passwordString, myConstant.getUrlEditPassWordWhereIdStudent());

            if (Boolean.parseBoolean(editPassWherIdStudent.get())) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(), "Cannot Upload New User", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkIdStudent() {

//        return true ==> idStudentString ไม่มีในฐานข้อมูล
//        return false ==> idStudentString มีในฐานข้อมูล

        String tag = "29novV1";
        boolean result = true;
        MyConstant myConstant = new MyConstant();

        try {

            MyGetAllData myGetAllData = new MyGetAllData(getActivity());
            myGetAllData.execute(myConstant.getUrlgetUserString());
            String strJSON = myGetAllData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0;i<jsonArray.length();i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (idStudentString.equals(jsonObject.getString("idStudent"))) {
                    result = false;
                    if (jsonObject.getString("Password").isEmpty()) {
                        statusABoolean = false;
                    }
                }

            }
            Log.d(tag, "Result => " + result);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }



    }

    private void toolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister2);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space));

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register2, container, false);
        return view;
    }
}   // Main Class
