package com.deimian86.verdurasdetemporada.activities;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.fragments.FrutasFragment;
import com.deimian86.verdurasdetemporada.fragments.VerdurasFragment;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.AppDatabase_Create_Async;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String tag = this.getClass().getName();
    private MainActivityPagerAdapter adapter;
    public AppDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Crear o abrir base de datos
        RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
            public void onCreate (@NonNull SupportSQLiteDatabase database){
                // Llenamos la base de datos solo tras crearla
                AppDatabase_Create_Async createDb = new AppDatabase_Create_Async(db, getApplicationContext());
                createDb.execute();
            }
        };

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "verduras-frutas-db").addCallback(rdc).build();

        // Inicializamos la navegacion por tabs
        ViewPager mViewPager = findViewById(R.id.pager);
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
//        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d(tag,"onQueryTextSubmit: " + query);
//                // adapter.getFilter().filter(query);
//                // myActionMenuItem.collapseActionView();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                Log.d(tag,"SearchOnQueryTextChanged: " + s);
//                // adapter.getFilter().filter(s);
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

}
