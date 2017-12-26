package com.example.byhisson.fragmentex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by byhisson on 2017. 12. 21..
 */

public class GroupListAdapter extends ArrayAdapter<Group> {


    Context context;
    int resId;
    ArrayList<Group> datas;


    ImageView typeImagView;
    TextView nameView;
    TextView organisation;
    ImageView menuImageView;

    public GroupListAdapter(Context context, int resId, ArrayList<Group> datas) {
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
            GroupHolder holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        }
        GroupHolder holder = (GroupHolder) convertView.getTag();

        typeImagView = holder.typeImageView;
        nameView = holder.nameView;
        organisation = holder.organisationView;
        menuImageView = holder.menuImangeView;

        final Group group = datas.get(position);

        nameView.setText(group.getGroupName());
        organisation.setText(group.getGroupOrganisation());
        getImageOfOrganization(group.getGroupOrganisation());

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, group.getGroupId() + " menu click", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return convertView;
    }

    public void getImageOfOrganization(String organization) {
        if (organization.equals("company")) {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_domain_black_24dp, null)));
        } else if (organization.equals("university")) {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_location_city_black_24dp, null)));
        } else if (organization.equals("church")) {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_church_50dp, null)));
        } else {
            typeImagView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_domain_black_24dp, null)));
        }
    }
}
