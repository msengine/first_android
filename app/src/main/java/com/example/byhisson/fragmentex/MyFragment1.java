package com.example.byhisson.fragmentex;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;

/**
 * Created by byhisson on 2017. 11. 27..
 */

public class MyFragment1 extends Fragment {

    private ArrayList<Person> openPersonList;

    // fragment 가 생성될 때 호출되는 부분
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreate 후에 화면을 구성할 때 호훌되는 부분

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Button button_frag1 = (Button) getView().findViewById(R.id.button_add);
        button_frag1.setOnClickListener(new Button.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                EditText editName = (EditText) getView().findViewById(R.id.edittext1);
                EditText editAddress = (EditText) getView().findViewById(R.id.edittext2);
                EditText editHobby = (EditText) getView().findViewById(R.id.edittext3);
                EditText editNationality = (EditText) getView().findViewById(R.id.edittext4);

                String personName = editName.getText().toString();
                String personAddress = editAddress.getText().toString();
                String personHobby = editHobby.getText().toString();
                String personNationality = editNationality.getText().toString();

                final DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
                final Call<Boolean> call = dunkirkHub.addPerson(personName, personAddress, personHobby, personNationality);

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        /* 추가 성공 후 리스트 화면으로 이동 */
                        ((MainActivity)getActivity()).openUserListView();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "추가 실패", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

            }
        });
    }
}
