package com.developer.avengers.istiqomahin.act_main;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.SQLiteHandler;
import com.developer.avengers.istiqomahin.SessionManager;
import com.developer.avengers.istiqomahin.act_chart.ChartActivity;
import com.developer.avengers.istiqomahin.act_login.LoginActivity;
import com.developer.avengers.istiqomahin.receiver.MyReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private boolean goTo4 = false;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private static String formattedDate;
    public static int day_, month_, year_;
    private int dateEditText = 0;
    private int year, month, day;
    private SQLiteHandler db;
    private SessionManager session;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, 1);
        long startup = calendar.getTimeInMillis();

        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startup,
                AlarmManager.INTERVAL_DAY, pendingIntent);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        HashMap<String, String> user = db.getUserDetails();

        Bundle b = getIntent().getExtras();
        if(b != null){
            goTo4 = b.getBoolean("goto4");
        }
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.navigation);

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(goTo4){
            transaction.replace(R.id.main_container, new SettingFragment()).commit();
            bottomNavigation.getMenu().getItem(4).setChecked(true);
        }
        else{
            transaction.replace(R.id.main_container, new HomeFragment()).commit();
            bottomNavigation.getMenu().getItem(2).setChecked(true);
        }
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_ibadah:
                        fragment = new IbadahFragment();
                        break;
                    case R.id.navigation_rapor:
                        fragment = new RaporFragment();
                        break;
                    case R.id.navigation_beranda:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_notif:
                        fragment = new NotifFragment();
                        break;
                    case R.id.navigation_setting:
                        fragment = new SettingFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
        dateEditText = 1;
    }

    @SuppressWarnings("deprecation")
    public void setDate2(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
        dateEditText = 2;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            day_ = arg1;
            month_ = arg2+1;
            year_ = arg3;
            if(dateEditText == 1){
                showDate();
            }
            else{
                showDate2();
            }

        }
    };

    private void showDate() {
        TextView dateView = (TextView) findViewById(R.id.editText4);
        dateView.setText(new StringBuilder().append(MainActivity.day_).append("/")
                .append(MainActivity.month_).append("/").append(MainActivity.year_));
    }

    private void showDate2() {
        TextView dateView = (TextView) findViewById(R.id.editText5);
        dateView.setText(new StringBuilder().append(MainActivity.day_).append("/")
                .append(MainActivity.month_).append("/").append(MainActivity.year_));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Keluar?")
                .setTitle("Konfirmasi")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                        dialog.dismiss();
                        // CONFIRM
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert =  builder.create();
        alert.show();
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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