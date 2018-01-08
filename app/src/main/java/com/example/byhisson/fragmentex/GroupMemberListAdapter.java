package com.example.byhisson.fragmentex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by byhisson on 2018. 1. 5..
 */

public class GroupMemberListAdapter extends ArrayAdapter<String> {

    Context context;
    int resId;
    ArrayList<String> datas;
    GroupMemberListItemView groupMemberListItemView;


    public GroupMemberListAdapter(Context context, int resId, ArrayList<String> datas) {
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
            GroupMemberListItemView groupMemberListItemView = new GroupMemberListItemView(convertView);
            convertView.setTag(groupMemberListItemView);
        }

        groupMemberListItemView = (GroupMemberListItemView) convertView.getTag();
        setMemberInfo(position);

        return convertView;
    }

    public void setMemberInfo(int position) {
        groupMemberListItemView.groupMemberView.setText(datas.get(position));

        groupMemberListItemView.memberImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "clicked", Toast.LENGTH_LONG);
                toast.show();
            }
        });


    }


}
