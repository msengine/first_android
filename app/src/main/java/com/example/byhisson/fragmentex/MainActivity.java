package com.example.byhisson.fragmentex;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog verDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openUserListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    public void openUserListView() {
        UserListView openUserList = new UserListView();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, openUserList);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openAddUser() {
        AddFragment addPerson = new AddFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, addPerson);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openDetailPersonInfo(int i, ArrayList<Person> personArrayList) {
        String selectedName = personArrayList.get(i).getName();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, UserDetailView.newInstance(selectedName));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void callDialog(String dialogMassege) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_info_outline_black_24dp);
        builder.setTitle("알림");
        builder.setMessage(dialogMassege);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("NO", null);
        verDialog = builder.create();
        verDialog.show();
    }

    public void goBack() {
        getFragmentManager().popBackStack();
    }
}
