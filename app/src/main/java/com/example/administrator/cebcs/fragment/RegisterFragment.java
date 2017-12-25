package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.cebcs.MainActivity;
import com.example.administrator.cebcs.R;
import com.example.administrator.cebcs.unity.MyAlert;
import com.example.administrator.cebcs.unity.MyConstant;
import com.example.administrator.cebcs.unity.MyPostUser;

import java.time.LocalTime;

/**
 * Created by Administrator on 12/9/2560.
 */

public class RegisterFragment extends Fragment {

    //Explicit
    private String idStudentString, nameString, surnameString, genderString, passwordString, rePasswordString, majorString = "Major", sectorString = "Sector", classString = "Class";
    private boolean aBoolean = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
        //OnCreevied
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Toolbar Controller
        toolbarController();

        //Save Controller
        saveController();

        //Radio Controller
        radioController();

//        Major Controller
        majorController();


//        Sector Controller
        sectorController();


//        Class Controller
        classController();


    }//onActivityCreat

    private void classController() {
        Spinner spinner = getView().findViewById(R.id.spnClass);
        MyConstant myConstant = new MyConstant();
        final String[] strings = myConstant.getClassStrings();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                strings
        );
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classString = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classString = strings[0];
            }
        });
    }

    private void sectorController() {
        Spinner spinner = getView().findViewById(R.id.spnSector);
        MyConstant myConstant = new MyConstant();
        final String[] strings = myConstant.getSectorStrings();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                strings
        );
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectorString = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sectorString = strings[0];
            }
        });
    }

    private void majorController() {
        Spinner spinner = getView().findViewById(R.id.spnMajor);
        MyConstant myConstant = new MyConstant();
        final String[] strings = myConstant.getMajorStrings();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                strings
        );
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                majorString = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                majorString = strings[0];
            }
        });
    }

    private void radioController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                aBoolean = false;
                switch (i) {
                    case R.id.radMale:
                        genderString = "Male";
                        break;
                    case R.id.radFemale:
                        genderString = "Female";
                        break;
                }
            }
        });
    }

    private void saveController() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From EditText
                EditText idStudentEditText = getView().findViewById(R.id.edtIDstudent);
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText surnameEditText = getView().findViewById(R.id.edtSurName);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);
                EditText repasswordEditText = getView().findViewById(R.id.edtRePassword);

                //Change Data Type to String
                idStudentString = idStudentEditText.getText().toString().trim();
                nameString = nameEditText.getText().toString().trim();
                surnameString = surnameEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                rePasswordString = repasswordEditText.getText().toString().trim();

                //Check Space
                if (idStudentString.equals("") ||
                        nameString.equals("") ||
                        surnameString.equals("") ||
                        passwordString.equals("") ||
                        rePasswordString.equals("")) {
                    //True
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.have_space), getString(R.string.message_have_space));

                } else if (idStudentString.length() != 13) {
                    //idStuudent False
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.id_false), getString(R.string.mess_id_false));


                } else if (aBoolean) {
                    //Non Checked Gender
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.gender_false), getString(R.string.mass_gender_false));
                } else if (passwordString.equals(rePasswordString)) {


                    //True Password
                    uploadValueToServer();


                } else {
                    //False Password
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.pass_false), getString(R.string.mass_pass_false));
                }


            } // on Click
        });


    }

    private void uploadValueToServer() {

        String tag = "2octV1";
        MyConstant myConstant = new MyConstant();

        try {


            //Show Log
            Log.d(tag, "idStident ==> " + idStudentString);
            Log.d(tag, "Name ==> " + nameString);
            Log.d(tag, "Surname ==> " + surnameString);
            Log.d(tag, "Gender ==> " + genderString);
            Log.d(tag, "Password ==> " + passwordString);

            MyPostUser myPostUser = new MyPostUser(getActivity());
            myPostUser.execute(
                    idStudentString,
                    nameString,
                    surnameString,
                    genderString,
                    passwordString,
                    majorString,
                    sectorString,
                    classString,
                    myConstant.getUrlPostUserString()
            );
            String result = myPostUser.get();
            Log.d("2octV1", "Result ==>" + result);

            if (Boolean.parseBoolean(result)) {
                Toast.makeText(getActivity(), "Upload Success", Toast.LENGTH_SHORT ).show();

                getActivity().getSupportFragmentManager().popBackStack();

            } else {
                Toast.makeText(getActivity(), "Please Try Again Cannot Upload To Derver", Toast.LENGTH_SHORT ).show();
            }



        } catch (Exception e) {
            Log.d(tag, "e upload ==> " + e.toString());


        }


    }

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.ToobarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.register));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


    }

}//Main Class