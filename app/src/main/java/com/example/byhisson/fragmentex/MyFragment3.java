package com.example.byhisson.fragmentex;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.GitHubService.retrofit;

/**
 * Created by byhisson on 2017. 11. 27..
 */

public class MyFragment3 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    //private String mParam2;

    Person personDetail;


    private OnFragmentInteractionListener mListener;

    public MyFragment3() {
        // Required empty public constructor
    }

    public static MyFragment3 newInstance(String param1) {
        MyFragment3 fragment = new MyFragment3();
        Bundle args = new Bundle();                 // 파라미터는 전달할 데이터 개
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        GitHubService gitHubService = retrofit.create(GitHubService.class);
        final Call<Person> call = gitHubService.detailPerson("persons", mParam1);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(retrofit2.Call<Person> call, Response<Person> response) {

                TextView textDetail1 = (TextView) getView().findViewById(R.id.text_detail1);
                TextView textDetail2 = (TextView) getView().findViewById(R.id.text_detail2);
                TextView textDetail3 = (TextView) getView().findViewById(R.id.text_detail3);
                TextView textDetail4 = (TextView) getView().findViewById(R.id.text_detail4);

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

                TextView textDetail1 = (TextView) getView().findViewById(R.id.text_detail1);
                TextView textDetail2 = (TextView) getView().findViewById(R.id.text_detail2);
                TextView textDetail3 = (TextView) getView().findViewById(R.id.text_detail3);
                TextView textDetail4 = (TextView) getView().findViewById(R.id.text_detail4);

                GitHubService gitHubService = retrofit.create(GitHubService.class);
                final  Call<Void> call = gitHubService.delPerson(textDetail1.getText().toString());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast toast = Toast.makeText(getActivity(), "삭제 성공", Toast.LENGTH_LONG);
                        toast.show();
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
