package com.developer.avengers.istiqomahin.act_main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.developer.avengers.istiqomahin.R;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    public String s;
    public String jam;
    public String menit;

    public TimePickerFragment(String s, String jam, String menit) {
        this.s = s;
        this.jam = jam;
        this.menit = menit;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = Integer.parseInt(jam);
        int minute = Integer.parseInt(menit);

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK
                ,this, hour, minute, DateFormat.is24HourFormat(getActivity()));

        return tpd;
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        TextView tv = null;
        switch(s){
            case "switch1":
                tv = (TextView) getActivity().findViewById(R.id.set1);
                break;
            case "switch2":
                tv = (TextView) getActivity().findViewById(R.id.set2);
                break;
            case "switch3":
                tv = (TextView) getActivity().findViewById(R.id.set3);
                break;
            case "switch4":
                tv = (TextView) getActivity().findViewById(R.id.set4);
                break;
            case "switch5":
                tv = (TextView) getActivity().findViewById(R.id.set5);
                break;
            default:
                break;
        }
        String h, m;
        if(hourOfDay<10){
            h = "0" + hourOfDay;
        }
        else{
            h = String.valueOf(hourOfDay);
        }
        if(minute < 10){
            m = "0" + minute;
        }
        else{
            m = String.valueOf(minute);
        }
        tv.setText(h + ":" + m);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("time", MODE_PRIVATE).edit();
        editor.putString(s, h + ":" + m);
        editor.apply();
    }
}