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

public class PersonListAdapter extends ArrayAdapter<Person> {
    Context context;
    int resId;
    ArrayList<Person> datas;
    PersonListItemView itemView;

    public PersonListAdapter(Context context, int resId, ArrayList<Person> datas) {
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
            PersonListItemView holder = new PersonListItemView(convertView);
            convertView.setTag(holder);
        }
        itemView = (PersonListItemView) convertView.getTag();

        getPersonInfo(position);

        return convertView;
    }

    public void getPersonInfo(int position){

        final Person person = datas.get(position);

        itemView.nameView.setText(person.name);
        itemView.addressView.setText(person.address);

        if (person.nationality.toLowerCase().equals("korea")) {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.kor, null)));
        } else if (person.nationality.toLowerCase().equals("usa")) {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.usa, null)));
        } else {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_place_black_24dp, null)));
        }

        itemView.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, person.hobby + " menu click", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}