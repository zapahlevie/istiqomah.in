package com.developer.avengers.istiqomahin.act_theme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.act_main.MainActivity;

public class ThemeActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("goto4", true);
        i.putExtras(b);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        setContentView(R.layout.activity_theme);
        getSupportActionBar().setTitle("Tema");

        Button b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme1");
            }
        });

        Button b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme2");
            }
        });

        Button b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme3");
            }
        });

        Button b4 = (Button)findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme4");
            }
        });

        Button b5 = (Button)findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme5");
            }
        });

        Button b6 = (Button)findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme6");
            }
        });

        Button b7 = (Button)findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme7");
            }
        });

        Button b8 = (Button)findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme("theme8");
            }
        });
    }

    private void changeTheme(String t) {
        SharedPreferences.Editor editor = getSharedPreferences("local", MODE_PRIVATE).edit();
        editor.putString("theme", t);
        editor.apply();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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