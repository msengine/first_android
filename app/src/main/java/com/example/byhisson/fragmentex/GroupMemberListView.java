package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;

/**
 * Created by byhisson on 2018. 1. 4..
 */

public class GroupMemberListView extends Fragment {

    public static String selectedGroupName = "";
    private ArrayList<String> memberArrayList;
    private MainActivity parent;

    //public GroupMemberListView(){};

    public static GroupMemberListView newInstance(Group group) {
        GroupMemberListView fragment = new GroupMemberListView();
        selectedGroupName = group.getName();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_member_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        openMemberList(selectedGroupName);
    }

    public void openMemberList(String groupName) {
        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<ArrayList<String>> call = dunkirkHub.openGroupMemberList(groupName);

        call.enqueue(new Callback<ArrayList<String>> () {
            @Override
            public void onResponse(Call<ArrayList<String>>  call, Response<ArrayList<String>>  response) {
                memberArrayList = response.body();
                OrderByAscending();

                ListView listview = (ListView) getView().findViewById(R.id.group_member_listview);
                GroupMemberListAdapter adapter = new GroupMemberListAdapter(getActivity(), R.layout.group_member_item, memberArrayList);

                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //parent.openDetailPersonInfo(i, personArrayList);
                    }
                });

            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
            }
        });

        LinearLayout moveAddMember = (LinearLayout) getView().findViewById(R.id.button_group_member_add);
        moveAddMember.setOnClickListener((View v) -> parent.openAddGroupMemberList(selectedGroupName));
    }

    void OrderByAscending(){
        String temp ="";
        for(int i = 0; i < memberArrayList.size(); i++){
            for(int j =i +1 ; j < memberArrayList.size();j++ ){
                if(memberArrayList.get(j).compareTo(memberArrayList.get(i)) < 0){
                    temp = memberArrayList.get(i);
                    memberArrayList.set(i, memberArrayList.get(j));
                    memberArrayList.set(j, temp);
                }
            }
        }
    }
}
