package com.deimian86.verdurasdetemporada.utils;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.deimian86.verdurasdetemporada.entities.Verdura;

public class AppDatabase_Create_Async extends AsyncTask<Void, Void, Void> {

    private AppDatabase db;

    public AppDatabase_Create_Async(AppDatabase db) {
        this.db = db;
    }

    @Override
    protected Void doInBackground(Void... voids) {

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