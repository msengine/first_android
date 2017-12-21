package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;
/**
 * Created by byhisson on 2017. 12. 21..
 */

public class GroupListView extends Fragment{

    private ArrayList<GroupVO>  groupArrayList;
    private MainActivity parent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveeInstanceState){
        return inflater.inflate(R.layout.fragment_grouplist, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<ArrayList<GroupVO>> call = dunkirkHub.getGroupList("group");

        call.enqueue(new Callback<ArrayList<GroupVO>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupVO>> call, Response<ArrayList<GroupVO>> response) {
                groupArrayList = response.body();

                ListView groupListView= (ListView)getView().findViewById(R.id.group_listview);
                GroupListAdt adapter = new GroupListAdt(getActivity(), R.layout.group_custom_item, groupArrayList);
                groupListView.setAdapter(adapter);

                groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //parent.openDetailGroupInfo(i, groupArrayList);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<GroupVO>> call, Throwable t) {

            }
        });

    }
}