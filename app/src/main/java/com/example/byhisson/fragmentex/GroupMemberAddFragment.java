package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;

/**
 * Created by byhisson on 2018. 1. 9..
 */

public class GroupMemberAddFragment extends Fragment {

    private ArrayList<Person> personArrayList;
    private ArrayList<String> memberArrayList;
    private static String selectedGroupName = "";
    private MainActivity parent;

    public static GroupMemberAddFragment newInstance(String groupName) {
        GroupMemberAddFragment fragment = new GroupMemberAddFragment();
        selectedGroupName = groupName;
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

        openPersonList();
    }

    public void openPersonList() {
        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<ArrayList<Person>> call = dunkirkHub.repoContributors2("persons");

        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                personArrayList = response.body();
                memberArrayList = new ArrayList<String>();
                for (int i = 0; i < personArrayList.size(); i++) {
                    memberArrayList.add(personArrayList.get(i).name);
                }

                ListView listview = (ListView) getView().findViewById(R.id.group_member_listview);
                GroupMemberListAdapter adapter = new GroupMemberListAdapter(getActivity(), R.layout.group_member_item, memberArrayList);

                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String member = (String) memberArrayList.get(i);
                        addMemberToGroup(member);
                    }
                });
            }
            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
            }
        });

        LinearLayout addMemberList = (LinearLayout) getView().findViewById(R.id.button_group_member_add);
        addMemberList.setVisibility(View.GONE);
    }

    public void addMemberToGroup(String member) {

        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<Boolean> call = dunkirkHub.addMemberToGroup(selectedGroupName, member);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast toast = Toast.makeText(getActivity(), "추가 성공", Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }
        });
    }
}
