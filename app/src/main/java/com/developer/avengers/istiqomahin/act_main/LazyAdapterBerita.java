package com.developer.avengers.istiqomahin.act_main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class LazyAdapterBerita extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  public ImageLoader imageLoader;
    public LazyAdapterBerita(Activity a, ArrayList<HashMap<String, String>> d)
    {
        activity = a;

        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup
            parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_berita, null);
        TextView id_berita = (TextView) vi.findViewById(R.id.kode);
        TextView judul = (TextView) vi.findViewById(R.id.judul);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.gambar);

        HashMap<String, String> daftar_berita = new HashMap<String, String>();
        daftar_berita = data.get(position);

        id_berita.setText(daftar_berita.get(IbadahFragment.TAG_ID));
        judul.setText(daftar_berita.get(IbadahFragment.TAG_JUDUL));

        Picasso.with(activity.getApplicationContext())
                .load(daftar_berita.get(IbadahFragment.TAG_GAMBAR))
                .error(R.mipmap.ic_launcher)
                .into(thumb_image);

        return vi;
    }
}