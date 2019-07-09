package com.deimian86.verdurasdetemporada.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.deimian86.verdurasdetemporada.BuildConfig;
import com.deimian86.verdurasdetemporada.fragments.MariscosFragment;
import com.deimian86.verdurasdetemporada.fragments.PescadosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.fragments.FrutasFragment;
import com.deimian86.verdurasdetemporada.fragments.VerdurasFragment;
import java.util.ArrayList;
import java.util.List;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.start(getApplication(), BuildConfig.MS_APPCENTER_SECRET, Analytics.class, Crashes.class);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public void init(){
        final ViewPager mViewPager = findViewById(R.id.pager);
        setupViewPager(mViewPager);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_verduras:
                                mViewPager.setCurrentItem(0);
                                break;
                            case R.id.action_frutas:
                                mViewPager.setCurrentItem(1);
                                break;
                            case R.id.action_pescados:
                                mViewPager.setCurrentItem(2);
                                break;
                            case R.id.action_mariscos:
                                mViewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });
    }

    // Inicializacion del adapter de fragments
    private void setupViewPager(ViewPager viewPager) {
        MainActivityPagerAdapter adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        // Fragment verduras
        VerdurasFragment fragmentVerduras = new VerdurasFragment();
        adapter.addFragment(fragmentVerduras, getResources().getString(R.string.tab_verduras));
        // Fragment Frutas
        FrutasFragment fragmentFrutas = new FrutasFragment();
        adapter.addFragment(fragmentFrutas, getResources().getString(R.string.tab_frutas));
        // Fragment Pescados
        PescadosFragment fragmentPescados = new PescadosFragment();
        adapter.addFragment(fragmentPescados, getResources().getString(R.string.tab_pescados));
        // Fragment Mariscos
        MariscosFragment fragmentMariscos = new MariscosFragment();
        adapter.addFragment(fragmentMariscos, getResources().getString(R.string.tab_mariscos));
        viewPager.setAdapter(adapter);
    }

    public class MainActivityPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private MainActivityPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}
