package com.example.byhisson.fragmentex;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog verDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolar);
        setSupportActionBar(mainToolbar);

        openGroupListView();
        //openUserListView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ActionBar ab = getSupportActionBar();

        switch (id) {
            case R.id.action_group_list:
                ab.setTitle("Group Management");
                openGroupListView();
                return true;
            case R.id.action_person_list:
                ab.setTitle("Person Management");
                openUserListView();
                return true;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    public void openGroupListView() {
        GroupListView openGroupList = new GroupListView();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, openGroupList);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openAddGroup() {
        GroupAddFragment groupAddFragment = new GroupAddFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, groupAddFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openGroupMemberListView(Group group){
        GroupMemberListView groupMemberListView = new GroupMemberListView();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, groupMemberListView.newInstance(group));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openUserListView() {
        PersonListView openUserList = new PersonListView();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, openUserList);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openAddGroupMemberList(){

    }

    public void openAddUser() {
        PersonAddFragment addPerson = new PersonAddFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, addPerson);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openDetailPersonInfo(int i, ArrayList<Person> personArrayList) {
        String selectedName = personArrayList.get(i).name;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, PersonDetailView.newInstance(selectedName));
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
