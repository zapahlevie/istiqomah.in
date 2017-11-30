package com.developer.avengers.istiqomahin.act_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.SQLiteHandler;
import com.developer.avengers.istiqomahin.SessionManager;
import com.developer.avengers.istiqomahin.act_about.AboutActivity;
import com.developer.avengers.istiqomahin.act_hijri.HijriActivity;
import com.developer.avengers.istiqomahin.act_kompas.Kiblat;
import com.developer.avengers.istiqomahin.act_login.LoginActivity;
import com.developer.avengers.istiqomahin.act_theme.ThemeActivity;
import com.developer.avengers.istiqomahin.act_user.UserActivity;

public class SettingFragment extends Fragment {

    private SQLiteHandler db;
    private SessionManager session;

    public String[] menuItems = {"Edit Profil", "Tema", "Tentang", "Logout"};
    public String[] menuTools = {"Kalender Hijriyah", "Kompas Kiblat"};
    public Integer[] imageId = {
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_color_lens_black_24dp,
            R.drawable.ic_info_black_24dp,
            R.drawable.ic_exit_to_app_black_24dp,
    };
    public Integer[] imageId2 = {
            R.drawable.ic_date_range_black_24dp,
            R.drawable.ic_navigation_black_24dp,
    };

    public SettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_setting, container, false);

        db = new SQLiteHandler(getActivity().getApplicationContext());
        session = new SessionManager(getActivity().getApplicationContext());

        CustomList adapter = new
                CustomList(getActivity(), menuItems, imageId);
        CustomList adapter2 = new
                CustomList(getActivity(), menuTools, imageId2);
        ListView listView = (ListView) rootView.findViewById(R.id.mainMenu);
        ListView listView2 = (ListView) rootView.findViewById(R.id.mainTools);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent i = new Intent(getActivity(), UserActivity.class);
                    startActivity(i);;
                }
                else if(position==1){
                    Intent i = new Intent(getActivity(), ThemeActivity.class);
                    startActivity(i);;
                    getActivity().finish();
                }
                else if(position==2){
                    Intent i = new Intent(getActivity(), AboutActivity.class);
                    startActivity(i);;
                }
                else if(position==3){
                    session.setLogin(false);

                    db.deleteUsers();

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getActivity(), menuItems[+ position], Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent i = new Intent(getActivity(), HijriActivity.class);
                    startActivity(i);;
                }
                else if(position==1){
                    Intent i = new Intent(getActivity(), Kiblat.class);
                    startActivity(i);;
                }
                else{
                    Toast.makeText(getActivity(), menuItems[+ position], Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
