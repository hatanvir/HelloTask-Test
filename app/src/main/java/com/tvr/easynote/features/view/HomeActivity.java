package com.tvr.easynote.features.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tvr.easynote.R;
import com.tvr.easynote.features.view.bottom_nav_fragment.CalendarFragment;
import com.tvr.easynote.features.view.bottom_nav_fragment.FavoriteFragment;
import com.tvr.easynote.features.view.bottom_nav_fragment.NoteFragment;
import com.tvr.easynote.features.view.bottom_nav_fragment.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.floating_button)
    FloatingActionButton floatingButton;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        setFragment(new CalendarFragment());


        bottomNavigation.getMenu().getItem(2).setEnabled(false);


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.calendar:
                        setFragment(new CalendarFragment());
                        return true;
                    case R.id.document:
                        setFragment(new NoteFragment());
                        return true;
                    case R.id.favorite:
                        setFragment(new FavoriteFragment());
                        return true;
                    case R.id.settings:
                        setFragment(new SettingsFragment());
                        return true;

                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment).addToBackStack("CALENDER_FRAG").commit();
    }
}