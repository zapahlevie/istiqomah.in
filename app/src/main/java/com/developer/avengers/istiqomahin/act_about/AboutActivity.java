package com.developer.avengers.istiqomahin.act_about;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        setContentView(R.layout.activity_about);
        TextView tv = (TextView) findViewById(R.id.textAbout);
        tv.setText("Amalanku\n" +
                "Current Version\n" +
                "v0.1.7 (beta)\n" +
                "\n" +
                "Description\n" +
                "Aplikasi ini dibuat dengan ikhlas sekaligus sebagai upaya memenuhi project akhir mata kuliah Pengembangan Perangkat Lunak.\n" +
                "\n" +
                "Developer\n" +
                "Hamba Allah\n" +
                "\n" +
                "Contact Us\n" +
                "www.avengers-dev.com\n" +
                "(021)123456789");
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
