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
 * Created by byhisson on 2017. 12. 26..
 */

public class GroupAddFragment extends Fragment {

    private MainActivity parent;
    private TextView textGroupName;
    private TextView textGroupOrganisation;
    private EditText editGroupName;
    private EditText editGroupOrganisation;

    final DunkirkHub dunkirkHub = retrofit.create(DunkirkHub.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parent = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        return inflater.inflate(R.layout.fragment_group_add, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        linkControls();
    }

    private void onSubmit() {
        String groupName = editGroupName.getText().toString().trim();
        String groupOrganisation = editGroupOrganisation.getText().toString().trim();

        if (hasWrongValue(groupName)) {
            showError(textGroupName);
            return;
        }
        if (hasWrongValue(groupOrganisation)) {
            showError(textGroupOrganisation);
            return;
        }

        LoadingData.showLoadingDialog(getActivity());
        addGroup(groupName, groupOrganisation);
    }

    private void addGroup(String groupName, String groupOrganisation) {
        final Call<Boolean> call = dunkirkHub.addGroup(groupName, groupOrganisation);
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
        textGroupName = getView().findViewById(R.id.text_group_name);
        textGroupOrganisation = getView().findViewById(R.id.text_group_organization);
        editGroupName = getView().findViewById(R.id.group_add_name);
        editGroupOrganisation = getView().findViewById(R.id.group_add_organization);
        Button submitButton = (Button) getView().findViewById(R.id.button_group_add);
        submitButton.setOnClickListener((View view) -> onSubmit());
    }
}
