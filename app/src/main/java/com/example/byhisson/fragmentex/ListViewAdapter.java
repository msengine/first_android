package com.example.byhisson.fragmentex;

/**
 * Created by byhisson on 2017. 11. 30..
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

    }

    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public ListViewItem getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_btn_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView10);

        ListViewItem listViewItem = getItem(position);

        iconImageView.setImageDrawable(listViewItem.getIconDrawble());
        titleTextView.setText(listViewItem.getName());

        return convertView;
    }

    public void addItem(Drawable icon, String name) {
        ListViewItem item = new ListViewItem();

        item.setIconDrawble(icon);
        item.setName(name);

        listViewItemArrayList.add(item);
    }
}
