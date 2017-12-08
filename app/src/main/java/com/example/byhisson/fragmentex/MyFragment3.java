package com.example.byhisson.fragmentex;

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

public class MyFragment3 extends Fragment {

    private static final String PERSON_NAME = "param1";

    private String mParam1;

    private TextView textDetail1;
    private TextView textDetail2;
    private TextView textDetail3;
    private TextView textDetail4;

    private OnFragmentInteractionListener mListener;

    public MyFragment3() {
        // Required empty public constructor
    }

    public static MyFragment3 newInstance(String param1) {
        MyFragment3 fragment = new MyFragment3();
        Bundle personDetail = new Bundle();                 // 파라미터는 전달할 데이터
        personDetail.putString(PERSON_NAME, param1);
        fragment.setArguments(personDetail);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(PERSON_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
        final Call<Person> call = dunkirkHub.detailPerson("persons", mParam1);

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

        return inflater.inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Button buttonDel = (Button) getView().findViewById(R.id.button_del);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textDetail1 = (TextView) getView().findViewById(R.id.text_detail1);
                textDetail2 = (TextView) getView().findViewById(R.id.text_detail2);
                textDetail3 = (TextView) getView().findViewById(R.id.text_detail3);
                textDetail4 = (TextView) getView().findViewById(R.id.text_detail4);

                DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);
                final Call<Void> call = dunkirkHub.delPerson(textDetail1.getText().toString());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "삭제 실패", Toast.LENGTH_LONG);
                        toast.show();

                    }
                });

                textDetail1.setText("");
                textDetail2.setText("");
                textDetail3.setText("");
                textDetail4.setText("");

                /* 삭제 후 리스트뷰로 이동 */
                ((MainActivity) getActivity()).openUserListView();

            }
        });
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
*/

/*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
        //void onFragmentInteraction(Uri uri);
    }
}
