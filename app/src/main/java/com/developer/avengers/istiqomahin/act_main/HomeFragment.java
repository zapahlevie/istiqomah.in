package com.developer.avengers.istiqomahin.act_main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.SQLiteHandler;
import com.developer.avengers.istiqomahin.Server;
import com.developer.avengers.istiqomahin.SessionManager;
import com.developer.avengers.istiqomahin.act_ibadah.IbadahActivity;
import com.developer.avengers.istiqomahin.act_rapor.Rapor;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment{

    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> DaftarIbadah = new ArrayList<HashMap<String, String>>();
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_KATEGORI = "kategori";
    public static final String TAG_TIPE = "tipe";
    public static final String TAG_SATUAN = "satuan";
    public static final String TAG_TARGET = "target";
    public static final String TAG_GAMBAR = "gambar";
    JSONArray string_json = null;
    ListView list;
    LazyAdapterIbadah adapter;
    public String user_id;
    public String name;
    List<Rapor> stockList = new ArrayList<Rapor>();
    Rapor stock;
    public RequestQueue rq;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("local", MODE_PRIVATE);

        Button b = (Button)rootView.findViewById(R.id.button7);
        Button button = (Button) rootView.findViewById(R.id.button8);
        ImageView iv = (ImageView) rootView.findViewById((R.id.imageView8));

        applyTheme(preferences.getString("theme", "theme1"), b, button, iv);

        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), IbadahActivity.class);
                startActivity(intent);

            }
        });

        db = new SQLiteHandler(getActivity().getApplicationContext());
        session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        user_id = user.get("uid");
        name = user.get("name");

        TextView nama = (TextView) rootView.findViewById(R.id.username);
        TextView tanggal = (TextView) rootView.findViewById(R.id.tanggal);
        nama.setTextSize(18);
        nama.setText(name);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getDefault());
        sdf2.setTimeZone(TimeZone.getDefault());
        String currentDateandTime = sdf.format(new Date());
        final String currentDateandTime2 = sdf2.format(new Date());
        tanggal.setText(currentDateandTime);

        DaftarIbadah = new ArrayList<HashMap<String, String>>();
        new HomeFragment.AmbilData().execute();
        list = (ListView) rootView.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarIbadah.get(position);
            }
        });

        rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Submit?")
                        .setTitle("Konfirmasi")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                int count = list.getCount();
                                int i = 0;
                                for(i = 0; i < count; i++){
                                    View childView = getViewByPosition(i, list);
                                    TextView nama = (TextView) childView.findViewById(R.id.nama);
                                    EditText tercapai_isi = (EditText) childView.findViewById(R.id.tercapai_isi);
                                    EditText target = (EditText) childView.findViewById(R.id.target);
                                    CheckBox tercapai_cek = (CheckBox) childView.findViewById(R.id.tercapai_cek);
                                    String namaIbadah = nama.getText().toString();
                                    String tercapaiIbadah = tercapai_isi.getText().toString();
                                    String targetIbadah = target.getText().toString();
                                    if(tercapai_cek.isChecked()){
                                        tercapaiIbadah = "1";
                                    }

                                    stock = new Rapor();
                                    stock.setNamaIbadah(namaIbadah);
                                    stock.setTercapai(tercapaiIbadah);
                                    stock.setTarget(targetIbadah);
                                    if(Integer.valueOf(tercapaiIbadah) >= Integer.valueOf(targetIbadah)){
                                        stock.setStatus("Tuntas");
                                    }
                                    else{
                                        stock.setStatus("Belum Tuntas");
                                    }
                                    stock.setTanggal(currentDateandTime2);
                                    stockList.add(stock);
                                    simpanData(stockList.get(i));
                                }
                                Toast.makeText(getActivity(), "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                                HomeFragment fragment = (HomeFragment)
                                        getFragmentManager().findFragmentById(R.id.main_container);

                                getFragmentManager().beginTransaction()
                                        .detach(fragment)
                                        .attach(fragment)
                                        .commit();
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
        });

        return rootView;
    }

    public void simpanData(final Rapor r){
        String url = Server.URL_RAPOR+"insert.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        //Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nama_ibadah", r.getNamaIbadah());
                params.put("tercapai", r.getTercapai());
                params.put("target", r.getTarget());
                params.put("status", r.getStatus());
                params.put("tanggal", r.getTanggal());
                params.put("user_id", user_id);
                return params;
            }
        };
        rq.add(postRequest);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void SetListViewAdapter(ArrayList<HashMap<String,
            String>> ibadah) {
        adapter = new LazyAdapterIbadah(getActivity(), ibadah);
        list.setAdapter(adapter);
    }
    class AmbilData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new
                    ArrayList<NameValuePair>();
            String URL = Server.URL_IBADAH+"ibadah.php?user_id="+user_id;
            JSONObject json = jParser.makeHttpRequest(URL,
                    "GET", params);
            Log.i("Ini nilai json ", ">" + json);
            try {
                string_json = json.getJSONArray("ibadah");
                for (int i = 0; i < string_json.length(); i++) {
                    JSONObject c = string_json.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    String nama = c.getString(TAG_NAMA);
                    String kategori = c.getString(TAG_KATEGORI);
                    String tipe = c.getString(TAG_TIPE);
                    String satuan = c.getString(TAG_SATUAN);
                    String target = c.getString(TAG_TARGET);
                    String gambar = "https://d30y9cdsu7xlg0.cloudfront.net/png/1546-200.png";
                    HashMap<String, String> map = new HashMap<String,
                            String>();
                    map.put(TAG_ID, id);
                    map.put(TAG_NAMA, nama);
                    map.put(TAG_KATEGORI, kategori);
                    map.put(TAG_TIPE, tipe);
                    map.put(TAG_SATUAN, satuan);
                    map.put(TAG_TARGET, target);
                    map.put(TAG_GAMBAR, gambar);
                    DaftarIbadah.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SetListViewAdapter(DaftarIbadah);
                }
            });
        }
    }

    private void applyTheme(String theme, Button b1, Button b2, ImageView iv){
        switch (theme){
            case "theme1":
                iv.setImageResource(R.color.colorPrimaryDark);
                break;
            case "theme2":
                iv.setImageResource(R.color.theme2ColorPrimaryDark);
                break;
            case "theme3":
                iv.setImageResource(R.color.theme3ColorPrimaryDark);
                break;
            case "theme4":
                iv.setImageResource(R.color.theme4ColorPrimaryDark);
                break;
            case "theme5":
                iv.setImageResource(R.color.theme5ColorPrimaryDark);
                break;
            case "theme6":
                iv.setImageResource(R.color.theme6ColorPrimaryDark);
                break;
            case "theme7":
                iv.setImageResource(R.color.theme7ColorPrimaryDark);
                break;
            case "theme8":
                iv.setImageResource(R.color.theme8ColorPrimaryDark);
                break;
            default:
                break;
        }
    }
}