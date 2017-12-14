package com.example.byhisson.fragmentex;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    /*
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //finish();
            //additional code
        } else {
            Log.d("MainActivity", "count != 0");
            getFragmentManager().popBackStack();
        }
    }
    */

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

    public void openDetailPersonInfo(int i, ArrayList<Person> personArrayList) {
        String selectedName = personArrayList.get(i).getName();
        //MyFragment3 detailPerson = new MyFragment3();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, MyFragment3.newInstance(selectedName));
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

    public void clearBackStack() {
        FragmentManager fm = getFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        openUserListView();
    }
}
