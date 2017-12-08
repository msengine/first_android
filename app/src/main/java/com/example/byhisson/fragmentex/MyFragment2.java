package com.example.byhisson.fragmentex;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;

/**
 * Created by byhisson on 2017. 11. 27..
 */

public class MyFragment2 extends Fragment {

    private ArrayList<Person> personArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        /* 리스트 불러오기 */

        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<ArrayList<Person>> call = dunkirkHub.repoContributors2("persons");

        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                personArrayList = response.body();

                ListView listview = (ListView) getView().findViewById(R.id.listview1);
                ListViewAdapter adapter = new ListViewAdapter();

                for (int i = 0; i < personArrayList.size(); i++) {
                    adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_face_black_24dp), personArrayList.get(i).getName());
                }

                listview.setAdapter(adapter);

                // 리스트 클릭 이벤트
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        openDetailPersonInfo(i);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {

            }
        });

        /* 사용자 추가 페이지로 이동 */

        LinearLayout moveAddPerson = (LinearLayout) getView().findViewById(R.id.button_add2);
        moveAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openAddUser();
            }
        });
    }

    public void openDetailPersonInfo(int i){
        String selectedName = personArrayList.get(i).getName();
        MyFragment3 detailPerson = new MyFragment3();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, detailPerson.newInstance(selectedName));
        fragmentTransaction.commit();
    }
}
