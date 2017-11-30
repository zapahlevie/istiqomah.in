package com.developer.avengers.istiqomahin.act_chart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        setContentView(R.layout.activity_chart);
        TextView t1 = (TextView) findViewById(R.id.tglawal);
        TextView t2 = (TextView) findViewById(R.id.tglakhir);
        Intent intent = getIntent();
        String date1 = intent.getStringExtra("date1");
        String date2 = intent.getStringExtra("date2");
        t1.setText("Tanggal Awal : " + date1);
        t2.setText("Tanggal Awal : " + date2);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(5f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(5f, 3));
        entries.add(new BarEntry(5f, 4));
        entries.add(new BarEntry(8f, 5));
        entries.add(new BarEntry(7f, 6));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("2017-11-13");
        labels.add("2017-11-14");
        labels.add("2017-11-15");
        labels.add("2017-11-16");
        labels.add("2017-11-17");
        labels.add("2017-11-18");
        labels.add("2017-11-19");

        BarDataSet dataset = new BarDataSet(entries, "Tercapai");

        BarChart chart = new BarChart(getApplicationContext());
        setContentView(chart);
        chart.setDescription("");
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.animateY(5000);
    }

    private void applyTheme(String theme){
        switch (theme){
            case "theme1":
                setTheme(R.style.AppTheme);
                break;
            case "theme2":
                setTheme(R.style.AppTheme_theme2);
                break;
            case "theme3":
                setTheme(R.style.AppTheme_theme3);
                break;
            case "theme4":
                setTheme(R.style.AppTheme_theme4);
                break;
            case "theme5":
                setTheme(R.style.AppTheme_theme5);
                break;
            case "theme6":
                setTheme(R.style.AppTheme_theme6);
                break;
            case "theme7":
                setTheme(R.style.AppTheme_theme7);
                break;
            case "theme8":
                setTheme(R.style.AppTheme_theme8);
                break;
            default:
                break;
        }
    }
}
