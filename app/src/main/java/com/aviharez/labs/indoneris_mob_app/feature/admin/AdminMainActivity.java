package com.aviharez.labs.indoneris_mob_app.feature.admin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.aviharez.labs.indoneris_mob_app.R;

import java.util.ArrayList;

public class AdminMainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        ListView lv = (ListView) findViewById(R.id.listview);
        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.));

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btnav);
        bottomNavigationView.inflateMenu(R.menu.admin_botnav_item);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_contaner, new FragmentMenuMain()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.menu_main:
                        fragment = new FragmentMenuMain();
                        break;
                    case R.id.menu_eventlast:
                        fragment = new FragmentMenuEvent();
                        break;
                    case R.id.menu_evenbaru:
                        fragment = new FragmentEventBaru();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_contaner, fragment).commit();
                return true;
            }
        });

    }
}
