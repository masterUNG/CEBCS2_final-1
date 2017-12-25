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
 * Created by masterung on 10/11/2017 AD.
 */

public class ServiceFragment extends Fragment{

    private String[] loginStrings;

    public static ServiceFragment serviceInstance(String[] loginStrings) {

        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        serviceFragment.setArguments(bundle);
        return serviceFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get ValueFrom Argument
        loginStrings = getArguments().getStringArray("Login");

//        Show Text
        showText();

    }

    private void showText() {
        TextView nameTextView = getView().findViewById(R.id.txtName);
        TextView idTextView = getView().findViewById(R.id.txtID);

        nameTextView.setText(loginStrings[2] + " " + loginStrings[3]);
        idTextView.setText(loginStrings[1]);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }
}   // Main Class
