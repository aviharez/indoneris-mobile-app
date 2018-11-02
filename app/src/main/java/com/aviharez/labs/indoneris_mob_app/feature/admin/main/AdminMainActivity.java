package com.aviharez.labs.indoneris_mob_app.feature.admin.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.*;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.feature.admin.menumain.AdminAddFragment;
import com.aviharez.labs.indoneris_mob_app.feature.admin.mhs.AdmMhsFragment;
import com.aviharez.labs.indoneris_mob_app.feature.admin.nextEvent.AdminEventFragment;

public class AdminMainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_mhs:
                    initFragment(new AdmMhsFragment());
                    return true;
                case R.id.menu_add:
                    initFragment(new AdminAddFragment());
                    return true;
                case R.id.menu_event:
                    initFragment(new AdminEventFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.botNavAdm);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        initFragment(new AdmMhsFragment());

    }

    private void initFragment(Fragment classFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, classFragment);
        transaction.commit();
    }
}
