package com.deimian86.verdurasdetemporada.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.adapters.VerduraAdapter;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraMes;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.AppDatabase_Create_Async;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String tag = this.getClass().getName();
    private AppDatabase db;
    private RecyclerView rv;
    private List<Verdura> verduras = new ArrayList<>();
    private VerduraAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CREAR O ABRIR BASE DE DATOS //

        RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
            public void onCreate (@NonNull SupportSQLiteDatabase database){
                // Llenamos la base de datos solo tras crearla
                AppDatabase_Create_Async createDb = new AppDatabase_Create_Async(db, getApplicationContext());
                createDb.execute();
            }
        };

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "verduras-db").addCallback(rdc).build();

        // LISTADO //

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapter = new VerduraAdapter(getApplicationContext(), verduras);
        rv.setAdapter(adapter);
        loadVerduras();

    }

    private void loadVerduras(){
        LiveData<List<Verdura>> verdurasLiveList = db.verduraDao().getAll();
        verdurasLiveList.observe(MainActivity.this, new Observer<List<Verdura>>() {
            @Override
            public void onChanged(@Nullable List<Verdura> verdurasTemp) {
                loadMesesPorVerdura(verdurasTemp);
            }
        });

    }

    private void loadMesesPorVerdura(final List<Verdura> verdurasTemp){

        for (final Verdura v: verdurasTemp) {
            LiveData<List<VerduraMes>> verdurasMesLiveList = db.verduraMesDao().findMesesPorVerdura(v.getId());
            verdurasMesLiveList.observe(MainActivity.this, new Observer<List<VerduraMes>>() {
                @Override
                public void onChanged(@Nullable List<VerduraMes> verdurasMesesTemp) {
                    for (VerduraMes vm: verdurasMesesTemp) {
                        Log.d(tag, "v = " + v.getNombre() + " vm = "  + vm.getMesId());
                        v.getMeses().add(vm.getMesId());
                    }
                    refreshAdapter(verdurasTemp);
                }
            });
        }
    }

    private void refreshAdapter(List<Verdura> verdurasTemp){
        verduras.clear();
        verduras.addAll(verdurasTemp);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(tag,"onQueryTextSubmit: " + query);
                adapter.getFilter().filter(query);
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(tag,"SearchOnQueryTextChanged: " + s);
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
