package com.aviharez.labs.indoneris_mob_app.feature.mhs;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.feature.mhs.achvmnt.MhsAcvFragment;
import com.aviharez.labs.indoneris_mob_app.feature.mhs.event.MhsEventFragment;
import com.aviharez.labs.indoneris_mob_app.feature.mhs.profil.MhsProfilFragment;

public class MhsMainActivity extends AppCompatActivity {

    private FrameLayout frameFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navi_event:
                    initFragment(new MhsEventFragment());
                    return true;
                case R.id.navi_achv:
                    initFragment(new MhsAcvFragment());
                    return true;
                case R.id.navi_profil:
                    initFragment(new MhsProfilFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        initFragment(new MhsEventFragment());

    }

    private void initFragment(Fragment classFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameFragment, classFragment);
        transaction.commit();
    }

}
