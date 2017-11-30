package com.developer.avengers.istiqomahin.act_splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.act_welcome.WelcomeActivity;


public class SplashActivity extends AppCompatActivity {

    //Set waktu lama splashscreen
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView iv = (ImageView)findViewById(R.id.splashs);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"), iv);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {

            }
        }, splashInterval);

    };

    private void applyTheme(String theme, ImageView iv){
        switch (theme){
            case "theme1":
                iv.setImageResource(R.color.colorPrimary);
                setTheme(R.style.AppTheme);
                break;
            case "theme2":
                iv.setImageResource(R.color.theme2ColorPrimary);
                setTheme(R.style.AppTheme_theme2);
                break;
            case "theme3":
                iv.setImageResource(R.color.theme3ColorPrimary);
                setTheme(R.style.AppTheme_theme3);
                break;
            case "theme4":
                iv.setImageResource(R.color.theme4ColorPrimary);
                setTheme(R.style.AppTheme_theme4);
                break;
            case "theme5":
                iv.setImageResource(R.color.theme5ColorPrimary);
                setTheme(R.style.AppTheme_theme5);
                break;
            case "theme6":
                iv.setImageResource(R.color.theme6ColorPrimary);
                setTheme(R.style.AppTheme_theme6);
                break;
            case "theme7":
                iv.setImageResource(R.color.theme7ColorPrimary);
                setTheme(R.style.AppTheme_theme7);
                break;
            case "theme8":
                iv.setImageResource(R.color.theme8ColorPrimary);
                setTheme(R.style.AppTheme_theme8);
                break;
            default:
                break;
        }
    }
}