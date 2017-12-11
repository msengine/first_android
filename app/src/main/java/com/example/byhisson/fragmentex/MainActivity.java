package com.example.byhisson.fragmentex;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String personName = "";
    String personAddress = "";
    String personHobby = "";
    String personNationality = "";

    AlertDialog verDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifeCycle", "onResume");
        if (getFragmentManager().getBackStackEntryCount() < 1) {
            openUserListView();
        }
    }

    @Override
    public void onPause() {
        Log.d("lifeCycle", "onPause");
        super.onPause();
    }

    public void openUserListView() {
        MyFragment2 openUserList = new MyFragment2();
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
}
