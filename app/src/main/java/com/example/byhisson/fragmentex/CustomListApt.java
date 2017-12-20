package com.example.byhisson.fragmentex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by byhisson on 2017. 12. 18..
 */

public class CustomListApt extends ArrayAdapter<PersonVO> {
    Context context;
    int resId;
    ArrayList<PersonVO> datas;

    public CustomListApt(Context context, int resId, ArrayList<PersonVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            PersonHolder holder = new PersonHolder(convertView);
            convertView.setTag(holder);
        }
        PersonHolder holder = (PersonHolder) convertView.getTag();

        ImageView typeImagView = holder.typeImageView;
        TextView textView1 = holder.textView1;
        TextView textView2 = holder.textView2;
        ImageView menuImageView = holder.menuImangeView;

        final PersonVO vo = datas.get(position);

        textView1.setText(vo.name);
        textView2.setText(vo.address);

        if (vo.nationality.toLowerCase().equals("korea")) {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.kor, null)));
        } else if (vo.nationality.toLowerCase().equals("usa")) {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.usa, null)));
        } else {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_place_black_24dp, null)));
        }

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, vo.hobby + " menu click", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return convertView;
    }
}
