package com.example.administrator.cebcs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.cebcs.R;

/**
 * Created by ASUS on 11/11/2560.
 */

public class DetailStudenFragment extends Fragment {

    private String[] loginStrings;

    public static DetailStudenFragment detailStudentInstance(String[] loginStrings) {
        DetailStudenFragment detailStudenFragmentv = new DetailStudenFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        detailStudenFragmentv.setArguments(bundle);
        return detailStudenFragmentv;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        loginStrings = getArguments().getStringArray("Login");

//        Show Text
        showText();
    }

    private void showText() {
        TextView nameTextView = getView().findViewById(R.id.txtName);
        TextView surnameTextView = getView().findViewById(R.id.txtSurname);
        TextView idTextView = getView().findViewById(R.id.txtID);
        TextView majorTextView = getView().findViewById(R.id.txtMajor);
        TextView sectorTextView = getView().findViewById(R.id.txtSector);
        TextView classTextView = getView().findViewById(R.id.txtClass);

        nameTextView.setText(loginStrings[3]);
        surnameTextView.setText(loginStrings[4]);
        idTextView.setText(loginStrings[1]);
        majorTextView.setText(loginStrings[7]);
        sectorTextView.setText(loginStrings[8]);
        classTextView.setText(loginStrings[9]);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_student, container, false);
        return view;
    }
}   // Main Class
