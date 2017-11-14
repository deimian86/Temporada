package com.deimian86.verdurasdetemporada.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.adapters.VerduraAdapter;
import com.deimian86.verdurasdetemporada.entities.Mes;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.AppDatabase_Create_Async;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        // FAB //

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mes mes = new Mes(new Random().nextInt(11) + 1);
                Toast.makeText(getApplicationContext(), mes.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // LISTADO //

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapter = new VerduraAdapter(getApplicationContext(), verduras);
        rv.setAdapter(adapter);
        loadData();

    }

    private void loadData(){

        Log.d(tag, "loadData()");

        LiveData<List<Verdura>> verdurasLiveList = db.verduraDao().getAll();

        // ADAPTER //

        verdurasLiveList.observe(MainActivity.this, new Observer<List<Verdura>>() {
            @Override
            public void onChanged(@Nullable List<Verdura> verdurasTemp) {
                Log.d(tag, "Observer<List<Verdura>> onChanged() " + verdurasTemp.size());
                verduras.clear();
                verduras.addAll(verdurasTemp);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
