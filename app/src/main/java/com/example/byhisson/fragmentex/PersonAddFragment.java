package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.byhisson.fragmentex.DunkirkHub.retrofit;

/**
 * Created by byhisson on 2017. 11. 27..
 */

public class PersonAddFragment extends Fragment {
    private MainActivity parent;
    private TextView textName;
    private TextView textAddr;
    private TextView textHobby;
    private TextView textNat;
    private EditText editName;
    private EditText editAddress;
    private EditText editHobby;
    private EditText editNationality;

    final DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        linkControls();

        Button submitButton = (Button) getView().findViewById(R.id.button_add);
        submitButton.setOnClickListener((View view) -> onSubmit());
    }

    private void onSubmit() {
        String personName = editName.getText().toString().trim();
        String personAddress = editAddress.getText().toString().trim();
        String personHobby = editHobby.getText().toString().trim();
        String personNationality = editNationality.getText().toString().trim();
        if (hasWrongValue(personName)) {
            showError(textName);
            return;
        }
        if (hasWrongValue(personAddress)) {
            showError(textAddr);
            return;
        }
        if (hasWrongValue(personHobby)) {
            showError(textHobby);
            return;
        }
        if (hasWrongValue(personNationality)) {
            showError(textNat);
            return;
        }

        LoadingData.showLoadingDialog(getActivity());
        addUser(personName, personAddress, personHobby, personNationality);
    }

    private void addUser(String personName, String personAddress, String personHobby, String personNationality) {
        final Call<Boolean> call = dunkirkHub.addPerson(personName, personAddress, personHobby, personNationality);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                LoadingData.hideLoadingDialog();
                parent.goBack();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                LoadingData.hideLoadingDialog();
                serverResponseError();
            }
        });
    }

    void showError(TextView textView) {
        parent.callDialog(textView.getText().toString() + " 값을 입력하세요.");
    }

    void serverResponseError() {
        parent.callDialog("서버 응답 에러\n다시 시도하세요.");
    }

    Boolean hasWrongValue(String input) {
        return input == null || input.equals("");
    }

    private void linkControls() {
        textName = (TextView) getView().findViewById(R.id.text_add1);
        textAddr = (TextView) getView().findViewById(R.id.text_add2);
        textHobby = (TextView) getView().findViewById(R.id.text_add3);
        textNat = (TextView) getView().findViewById(R.id.text_add4);

        editName = (EditText) getView().findViewById(R.id.edittext1);
        editAddress = (EditText) getView().findViewById(R.id.edittext2);
        editHobby = (EditText) getView().findViewById(R.id.edittext3);
        editNationality = (EditText) getView().findViewById(R.id.edittext4);
    }
}