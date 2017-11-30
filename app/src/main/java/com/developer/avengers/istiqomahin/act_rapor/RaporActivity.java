package com.developer.avengers.istiqomahin.act_rapor;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.SQLiteHandler;
import com.developer.avengers.istiqomahin.Server;
import com.developer.avengers.istiqomahin.SessionManager;
import com.developer.avengers.istiqomahin.act_main.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RaporActivity extends AppCompatActivity {

    private SQLiteHandler db;
    private SessionManager session;
    private String user_id;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> DaftarRapor = new ArrayList<HashMap<String, String>>();
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA_IBADAH = "nama_ibadah";
    public static final String TAG_TERCAPAI = "tercapai";
    public static final String TAG_TARGET = "target";
    public static final String TAG_STATUS = "status";
    public static final String TAG_TANGGAL = "tanggal";
    ListView list;
    RaporAdapter adapter;
    JSONArray string_json = null;
    JSONParser jParser = new JSONParser();
    public String year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        setContentView(R.layout.activity_rapor);

        Bundle b = getIntent().getExtras();
        year = String.valueOf(b.getInt("year"));
        month = String.valueOf(b.getInt("month") + 1);
        day = String.valueOf(b.getInt("day"));

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        user_id = user.get("uid");

        DaftarRapor = new ArrayList<HashMap<String, String>>();
        DaftarRapor.clear();

        new AmbilData().execute();
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarRapor.get(position);
                // Starting new intent
//                Intent in = new Intent(getApplicationContext(), DetailBerita.class);
//                in.putExtra(TAG_ID, map.get(TAG_ID));
//                startActivity(in);
            }
        });
    }

    public void SetListViewAdapter(ArrayList<HashMap<String,
            String>> berita) {
        adapter = new RaporAdapter(RaporActivity.this, berita);
        list.setAdapter(adapter);
    }

    class AmbilData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RaporActivity.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            DaftarRapor.clear();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new
                    ArrayList<NameValuePair>();
            String date = year+"-"+month+"-"+day;
            String url = Server.URL_RAPOR+"select.php?user_id="+user_id+"&tanggal="+date;
            JSONObject json = jParser.makeHttpRequest(url,
                    "GET", params);
            Log.i("Ini nilai json ", ">" + json);
            DaftarRapor.clear();
            try {
                string_json = json.getJSONArray("rapor");
                for (int i = 0; i < string_json.length(); i++) {
                    JSONObject c = string_json.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    String nama_ibadah = c.getString(TAG_NAMA_IBADAH);
                    String tercapai = c.getString(TAG_TERCAPAI);
                    String target = c.getString(TAG_TARGET);
                    String status = c.getString(TAG_STATUS);
                    String tanggal = c.getString(TAG_TANGGAL);
                    HashMap<String, String> map = new HashMap<String,
                            String>();
                    map.put(TAG_ID, id);
                    map.put(TAG_NAMA_IBADAH, nama_ibadah);
                    map.put(TAG_TERCAPAI, tercapai);
                    map.put(TAG_TARGET, target);
                    map.put(TAG_STATUS, status);
                    map.put(TAG_TANGGAL, tanggal);
                    DaftarRapor.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                HashMap<String, String> map = new HashMap<String,
                        String>();
                map.put(TAG_NAMA_IBADAH, "Tidak ada data");
                map.put(TAG_STATUS, "");
                DaftarRapor.add(map);
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    SetListViewAdapter(DaftarRapor);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DaftarRapor.clear();
        this.finish();
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
