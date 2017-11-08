package com.deimian86.verdurasdetemporada.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.adapters.VerduraAdapter;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.AppDatabase_Create_Async;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CREATE DATABASE //

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "verduras-db").build();
        AppDatabase_Create_Async createDb = new AppDatabase_Create_Async(db);
        createDb.execute();

        // FAB //

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Texto de prueba", Toast.LENGTH_SHORT).show();
            }
        });

        // LISTADO //

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        // ADAPTER //

        LiveData<List<Verdura>> verdurasLiveList = db.verduraDao().getAll();
        verdurasLiveList.observe(MainActivity.this, new Observer<List<Verdura>>() {
            @Override
            public void onChanged(@Nullable List<Verdura> verduras) {
                VerduraAdapter adapter = new VerduraAdapter(verduras);
                rv.setAdapter(adapter);
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
