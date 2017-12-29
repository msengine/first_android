package com.example.byhisson.fragmentex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by byhisson on 2017. 12. 21..
 */

public class GroupListAdapter extends ArrayAdapter<Group> {

    Context context;
    int resId;
    ArrayList<Group> datas;
    GroupListItemView itemView;

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
            GroupListItemView itemView = new GroupListItemView(convertView);
            convertView.setTag(itemView);
        }
        itemView = (GroupListItemView) convertView.getTag();

        setGroupInfo(position);

        return convertView;
    }

    public void setGroupInfo(int position){
        final Group group = datas.get(position);

        itemView.nameView.setText(group.getName());
        itemView.organizationView.setText(group.getOrganization());
        setImageOfOrganization(group.getOrganization());

        itemView.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, group.getId() + " menu click", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void setImageOfOrganization(String organization) {
        if (organization.equals("company")) {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_domain_black_24dp, null)));
        } else if (organization.equals("university")) {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_location_city_black_24dp, null)));
        } else if (organization.equals("church")) {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_church_50dp, null)));
        } else {
            itemView.typeImageView.setImageDrawable((ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_domain_black_24dp, null)));
        }
    }
}
