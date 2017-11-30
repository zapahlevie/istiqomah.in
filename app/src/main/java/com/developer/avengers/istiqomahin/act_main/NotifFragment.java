package com.developer.avengers.istiqomahin.act_main;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;

import static android.content.Context.MODE_PRIVATE;

public class NotifFragment extends Fragment {

    public TextView set1;
    public TextView set2;
    public TextView set3;
    public TextView set4;
    public TextView set5;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif, container, false);

        Switch switch1 = (Switch) view.findViewById(R.id.switch1);
        Switch switch2 = (Switch) view.findViewById(R.id.switch2);
        Switch switch3 = (Switch) view.findViewById(R.id.switch3);
        Switch switch4 = (Switch) view.findViewById(R.id.switch4);
        Switch switch5 = (Switch) view.findViewById(R.id.switch5);
        SharedPreferences preferences = getActivity().getSharedPreferences("alarm", MODE_PRIVATE);
        SharedPreferences pref = getActivity().getSharedPreferences("time", MODE_PRIVATE);
        boolean s1 = preferences.getBoolean("switch1", true);
        boolean s2 = preferences.getBoolean("switch2", true);
        boolean s3 = preferences.getBoolean("switch3", true);
        boolean s4 = preferences.getBoolean("switch4", true);
        boolean s5 = preferences.getBoolean("switch5", true);

        switch1.setChecked(s1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                if(isChecked){
                    try {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
                        r.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("alarm", MODE_PRIVATE).edit();
                editor.putBoolean("switch1", isChecked);
                editor.apply();
            }
        });

        switch2.setChecked(s2);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("alarm", MODE_PRIVATE).edit();
                editor.putBoolean("switch2", isChecked);
                editor.apply();
            }
        });

        switch3.setChecked(s3);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("alarm", MODE_PRIVATE).edit();
                editor.putBoolean("switch3", isChecked);
                editor.apply();
            }
        });

        switch4.setChecked(s4);
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("alarm", MODE_PRIVATE).edit();
                editor.putBoolean("switch4", isChecked);
                editor.apply();
            }
        });

        switch5.setChecked(s5);
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("alarm", MODE_PRIVATE).edit();
                editor.putBoolean("switch5", isChecked);
                editor.apply();
            }
        });

        set1 = (TextView) view.findViewById(R.id.set1);
        set1.setText(pref.getString("switch1", "04:00"));

        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] clock = set1.getText().toString().split(":");
                DialogFragment newFragment = new TimePickerFragment("switch1", clock[0], clock[1]);
                newFragment.show(myContext.getFragmentManager(),"TimePicker");
            }
        });

        set2 = (TextView) view.findViewById(R.id.set2);
        set2.setText(pref.getString("switch2", "12:00"));

        set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] clock = set2.getText().toString().split(":");
                DialogFragment newFragment = new TimePickerFragment("switch2", clock[0], clock[1]);
                newFragment.show(myContext.getFragmentManager(),"TimePicker");
            }
        });

        set3 = (TextView) view.findViewById(R.id.set3);
        set3.setText(pref.getString("switch3", "15:00"));

        set3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] clock = set3.getText().toString().split(":");
                DialogFragment newFragment = new TimePickerFragment("switch3", clock[0], clock[1]);
                newFragment.show(myContext.getFragmentManager(),"TimePicker");
            }
        });

        set4 = (TextView) view.findViewById(R.id.set4);
        set4.setText(pref.getString("switch4", "18:00"));

        set4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] clock = set4.getText().toString().split(":");
                DialogFragment newFragment = new TimePickerFragment("switch4", clock[0], clock[1]);
                newFragment.show(myContext.getFragmentManager(),"TimePicker");
            }
        });

        set5 = (TextView) view.findViewById(R.id.set5);
        set5.setText(pref.getString("switch5", "19:00"));

        set5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] clock = set5.getText().toString().split(":");
                DialogFragment newFragment = new TimePickerFragment("switch5", clock[0], clock[1]);
                newFragment.show(myContext.getFragmentManager(),"TimePicker");
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
}