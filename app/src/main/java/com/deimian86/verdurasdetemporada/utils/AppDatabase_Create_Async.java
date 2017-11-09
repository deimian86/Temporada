package com.deimian86.verdurasdetemporada.utils;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Verdura;

public class AppDatabase_Create_Async extends AsyncTask<Void, Void, Void> {

    private AppDatabase db;
    private Context context;

    public AppDatabase_Create_Async(AppDatabase db, Context context) {
        this.db = db;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Resources res = context.getResources();
        String[] verdurasNombres = res.getStringArray(R.array.verduras_array);

        for(String s : verdurasNombres) {
            Verdura v = new Verdura();
            v.setNombre(s);
        }

        Verdura v1 = new Verdura();
        v1.setNombre("Acelga");

        Verdura v2 = new Verdura();
        v2.setNombre("Tomate");

        Verdura v3 = new Verdura();
        v3.setNombre("Brócoli");

        db.verduraDao().insertAll(v1, v2, v3);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}