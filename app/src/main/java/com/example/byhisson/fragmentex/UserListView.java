package com.example.byhisson.fragmentex;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
 * Created by byhisson on 2017. 11. 27..
 */

public class UserListView extends Fragment {

    private ArrayList<Person> personArrayList;

    private parent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<ArrayList<Person>> call = dunkirkHub.repoContributors2("persons");

        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                personArrayList = response.body();

                ListView listview = (ListView) getView().findViewById(R.id.listview1);
                ListViewAdapter adapter = new ListViewAdapter();

                for (int i = 0; i < personArrayList.size(); i++) {
                    adapter.addItem(
                            ContextCompat.getDrawable(getActivity(),
                                    R.drawable.ic_face_black_24dp),
                            personArrayList.get(i).getName());
                }

                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        parent.openDetailPersonInfo(i, personArrayList);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {}
        });

        LinearLayout moveAddPerson = (LinearLayout) getView().findViewById(R.id.button_add2);
        moveAddPerson.setOnClickListener(((View v)) -> parent.openAddUser());
    }
}
