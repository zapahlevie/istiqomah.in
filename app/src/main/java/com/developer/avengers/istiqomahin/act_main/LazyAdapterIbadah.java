package com.developer.avengers.istiqomahin.act_main;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.avengers.istiqomahin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class LazyAdapterIbadah extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    //  public ImageLoader imageLoader;
    public LazyAdapterIbadah(Activity a, ArrayList<HashMap<String, String>> d)
    {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View vi = convertView;
        if (convertView == null){
            vi = inflater.inflate(R.layout.list_ibadah, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }

        holder = new ViewHolder();
        holder.nama = (TextView) vi.findViewById(R.id.nama);
        holder.satuan = (TextView) vi.findViewById(R.id.satuan);
        holder.tercapai_isi = (EditText) vi.findViewById(R.id.tercapai_isi);
        holder.tercapai_cek = (CheckBox) vi.findViewById(R.id.tercapai_cek);
        holder.target = (EditText) vi.findViewById(R.id.target);
        holder.thumb_image = (ImageView) vi.findViewById(R.id.gambar);

        holder.tercapai_cek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
            }
        });
        HashMap<String, String> daftar_ibadah = new HashMap<String, String>();
        daftar_ibadah = data.get(position);
        holder.nama.setText(daftar_ibadah.get(HomeFragment.TAG_NAMA));
        holder.satuan.setText(daftar_ibadah.get(HomeFragment.TAG_SATUAN));
        holder.target.setText(daftar_ibadah.get(HomeFragment.TAG_TARGET));
        if((daftar_ibadah.get(HomeFragment.TAG_TIPE)).equals("Isian")){
            holder.tercapai_isi.setVisibility(View.VISIBLE);
            holder.tercapai_cek.setVisibility(View.GONE);
            holder.satuan.setVisibility(View.VISIBLE);
            holder.tercapai_cek.setChecked(false);
        }
        else{
            holder.tercapai_isi.setVisibility(View.GONE);
            holder.tercapai_cek.setVisibility(View.VISIBLE);
            holder.satuan.setVisibility(View.INVISIBLE);
        }

        Picasso.with(activity.getApplicationContext())
                .load(daftar_ibadah.get(HomeFragment.TAG_GAMBAR))
                .error(R.mipmap.ic_launcher)
                .into(holder.thumb_image);

        return vi;
    }

    public class ViewHolder {
        TextView nama;
        TextView satuan;
        EditText tercapai_isi;
        CheckBox tercapai_cek;
        EditText target;
        ImageView thumb_image;
    }
}