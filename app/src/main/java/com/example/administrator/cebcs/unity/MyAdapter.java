package com.example.administrator.cebcs.unity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.cebcs.R;

/**
 * Created by masterung on 29/11/2017 AD.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;
    private String[] ceCodeStrings, ceSubjectStrings, numberStrings;
    private TextView ceCodeTextView, ceSubjectTextView, numberTextView;

    public MyAdapter(Context context, String[] ceCodeStrings, String[] ceSubjectStrings, String[] numberStrings) {
        this.context = context;
        this.ceCodeStrings = ceCodeStrings;
        this.ceSubjectStrings = ceSubjectStrings;
        this.numberStrings = numberStrings;
    }

    @Override
    public int getCount() {
        return ceCodeStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.layout_listview, viewGroup, false);

        ceCodeTextView = view1.findViewById(R.id.txtCEcode);
        ceSubjectTextView = view1.findViewById(R.id.txtCEsubject);
        numberTextView = view1.findViewById(R.id.txtNumber);

        numberTextView.setText(numberStrings[i]);
        ceCodeTextView.setText(ceCodeStrings[i]);
        ceSubjectTextView.setText(ceSubjectStrings[i]);


        return view1;
    }
}   // Main Class
