package com.developer.avengers.istiqomahin.act_main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.Image_loader.ImageLoader;
import com.developer.avengers.istiqomahin.R;
import com.developer.avengers.istiqomahin.Server;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailBerita extends Activity {
    public ImageLoader imageLoader;
    {
        imageLoader = new ImageLoader(null);
    }

    JSONArray string_json = null;
    String idberita;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    public static final String TAG_ID = "id";
    public static final String TAG_JUDUL = "judul";
    public static final String TAG_ISI = "isi";
    public static final String TAG_GAMBAR = "gambar";
    private static final String url_detail_berita = Server.URL_DETAIL_BERITA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("local", MODE_PRIVATE);
        applyTheme(preferences.getString("theme", "theme1"));
        setContentView(R.layout.single_list_item);

        Intent i = getIntent();
        idberita = i.getStringExtra(TAG_ID);
        new AmbilDetailBerita().execute();

    }
    class AmbilDetailBerita extends AsyncTask<String, String,
            String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DetailBerita.this);
            pDialog.setMessage("Mohon Tunggu!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> params1 = new
                        ArrayList<NameValuePair>();
                params1.add(new
                        BasicNameValuePair("id",idberita));
                JSONObject json = jsonParser.makeHttpRequest(
                        url_detail_berita, "GET", params1);
                string_json = json.getJSONArray("berita");
                runOnUiThread(new Runnable() {
                    public void run() {
                        ImageView thumb_image = (ImageView)
                                findViewById(R.id.imageView1);
                        TextView judul = (TextView)
                                findViewById(R.id.judul);
                        //TextView detail = (TextView)
                        findViewById(R.id.detail);
                        TextView isi = (TextView)
                                findViewById(R.id.content);
                        try {
                            // ambil objek member pertama dari JSON Array
                            JSONObject ar =
                                    string_json.getJSONObject(0);
                            String judul_d = ar.getString(TAG_JUDUL);
                            String isi_d = ar.getString(TAG_ISI);
                            String url_detail_image = ar.getString(TAG_GAMBAR);
                            judul.setText(judul_d);
                            isi.setText(isi_d);


//                            imageLoader.DisplayImage(ar.getString(TAG_GAMBAR),thumb_image);
                            Picasso.with(getApplicationContext())
                                    .load(url_detail_image)
                                    .error(R.mipmap.ic_launcher)
                                    .into(thumb_image);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
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