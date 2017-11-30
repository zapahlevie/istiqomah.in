package com.developer.avengers.istiqomahin.act_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.act_chart.ChartActivity;
import com.developer.avengers.istiqomahin.act_rapor.RaporActivity;

public class RaporFragment extends Fragment {

    private CalendarView calendarView;
    private static View rootView;

    public RaporFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rapor, container, false);
        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent i = new Intent(getActivity(), RaporActivity.class);
                i.putExtra("year", year);i.putExtra("month", month);i.putExtra("day", dayOfMonth);
                startActivity(i);
            }
        });
        final EditText date1 = (EditText) rootView.findViewById(R.id.editText4);
        final EditText date2 = (EditText) rootView.findViewById(R.id.editText5);
        Button b = (Button) rootView.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ChartActivity.class);
                i.putExtra("date1", date1.getText().toString());
                i.putExtra("date2", date2.getText().toString());
                startActivity(i);;
            }
        });
        return rootView;
    }
}