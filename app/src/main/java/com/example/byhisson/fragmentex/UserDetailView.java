package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;


/**
 * Created by byhisson on 2017. 11. 27..
 */

public class UserDetailView extends Fragment {

    public static String mParam1 = "";

    private TextView textDetail1;
    private TextView textDetail2;
    private TextView textDetail3;
    private TextView textDetail4;

    private MainActivity parent;

    public UserDetailView() {
        // Required empty public constructor
    }

    public static UserDetailView newInstance(String param1) {
        UserDetailView fragment = new UserDetailView();
        mParam1 = param1;
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<Person> call = dunkirkHub.detailPerson(mParam1);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(retrofit2.Call<Person> call, Response<Person> response) {

                textDetail1 = (TextView) getView().findViewById(R.id.text_detail1);
                textDetail2 = (TextView) getView().findViewById(R.id.text_detail2);
                textDetail3 = (TextView) getView().findViewById(R.id.text_detail3);
                textDetail4 = (TextView) getView().findViewById(R.id.text_detail4);

                Person selectedPerson = response.body();

                textDetail1.setText(selectedPerson.getName());
                textDetail2.setText(selectedPerson.getAddress());
                textDetail3.setText(selectedPerson.getHobby());
                textDetail4.setText(selectedPerson.getNationality());
            }

            @Override
            public void onFailure(retrofit2.Call<Person> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "조회 실패", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Button deleteButton = (Button) getView().findViewById(R.id.button_del);
        deleteButton.setOnClickListener((View view) -> deleteUser());
    }

    private void deleteUser() {
        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<Void> call = dunkirkHub.delPerson(textDetail1.getText().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                parent.goBack();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "삭제 실패", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
