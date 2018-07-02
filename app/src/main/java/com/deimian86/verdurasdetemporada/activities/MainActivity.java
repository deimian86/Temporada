package com.deimian86.verdurasdetemporada.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.fragments.FrutasFragment;
import com.deimian86.verdurasdetemporada.fragments.VerdurasFragment;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String tag = this.getClass().getName();
    private MainActivityPagerAdapter adapter;
    private ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public void init(){
        mViewPager = findViewById(R.id.pager);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment != null) fragment.getChildFragmentManager().popBackStack();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
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
