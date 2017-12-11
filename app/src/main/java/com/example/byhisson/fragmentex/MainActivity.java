package com.example.byhisson.fragmentex;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getFragmentManager().getBackStackEntryCount() < 1) {
            openUserListView();
        }
    }

    @Override
    public void onPause() {
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
        MyFragment1 addPerson = new MyFragment1();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, addPerson);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
