package com.deimian86.verdurasdetemporada.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.deimian86.verdurasdetemporada.BuildConfig;
import com.deimian86.verdurasdetemporada.fragments.MariscosFragment;
import com.deimian86.verdurasdetemporada.fragments.PescadosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.fragments.FrutasFragment;
import com.deimian86.verdurasdetemporada.fragments.VerdurasFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottomSheet;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.start(getApplication(), BuildConfig.MS_APPCENTER_SECRET, Analytics.class, Crashes.class);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFragment(new VerdurasFragment());
        initNavigation();
        initBottomDialog();
    }

    public void initNavigation(){
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_verduras:
                                fragment = new VerdurasFragment();
                                break;
                            case R.id.action_frutas:
                                fragment = new FrutasFragment();
                                break;
                            case R.id.action_pescados:
                                fragment = new PescadosFragment();
                                break;
                            case R.id.action_mariscos:
                                fragment = new MariscosFragment();
                                break;
                        }
                        return loadFragment(fragment);
                    }
                });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private void initBottomDialog(){
        bottomSheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        // callback for do something
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) { }
        });

    }



}
