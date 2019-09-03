package com.deimian86.verdurasdetemporada.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.deimian86.verdurasdetemporada.BuildConfig;
import com.deimian86.verdurasdetemporada.dialogs.DialogDetailFood;
import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
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

    public void showDetailBottomDialog(Object food) {
        DialogDetailFood bottomSheet = null;
        if(food instanceof Fruta) {
            bottomSheet = DialogDetailFood.newInstance((Fruta) food);
        } else if(food instanceof Verdura) {
            bottomSheet = DialogDetailFood.newInstance((Verdura) food);
        } else if(food instanceof Pescado) {
            bottomSheet = DialogDetailFood.newInstance((Pescado) food);
        } else if(food instanceof Marisco) {
            bottomSheet = DialogDetailFood.newInstance((Marisco) food);
        }
        if(bottomSheet != null) {
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        }
    }

}
