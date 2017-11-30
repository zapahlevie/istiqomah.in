package com.developer.avengers.istiqomahin.act_rapor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RaporAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  public ImageLoader imageLoader;
    public RaporAdapter(Activity a, ArrayList<HashMap<String, String>> d)
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
            vi = inflater.inflate(R.layout.list_rapor, null);
        TextView nama_ibadah = (TextView) vi.findViewById(R.id.nama_ibadah);
        TextView status = (TextView) vi.findViewById(R.id.status);

        HashMap<String, String> daftar_rapor = new HashMap<String, String>();
        daftar_rapor = data.get(position);

        nama_ibadah.setText(daftar_rapor.get(RaporActivity.TAG_NAMA_IBADAH));
        status.setText(daftar_rapor.get(RaporActivity.TAG_TERCAPAI)+"/"+daftar_rapor.get(RaporActivity.TAG_TARGET));
        return vi;
    }
}