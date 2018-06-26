package com.deimian86.verdurasdetemporada.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Fruta;
import com.deimian86.verdurasdetemporada.entities.FrutaMes;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraMes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class AppDatabase_Create_Async extends AsyncTask<Void, Boolean, Boolean> {

    private String tag = this.getClass().getName();
    private AppDatabase db;
    private Context context;

    public AppDatabase_Create_Async(AppDatabase db, Context context) {
        this.db = db;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Gson gson = new Gson();

        // Cargando verduras en la base de datos

        String jsonVerduras = rawJSONtoString(R.raw.data_verduras);
        Type listTypeVerduras = new TypeToken<List<Verdura>>(){}.getType();
        List<Verdura> verdurasList = gson.fromJson(jsonVerduras, listTypeVerduras);

        for(Verdura v : verdurasList) {
            Log.d(tag, v.toString());
            db.verduraDao().insertAll(v);

            for (int i : v.getMeses()) {
                VerduraMes vm = new VerduraMes(v.getId(), i);
                if(v.getMesesMenos().contains(i)){
                    vm.setMenorVenta(true);
                }
                db.verduraMesDao().insertAll(vm);
            }

        }

        // Cargando frutas en la base de datos

        String jsonFrutas = rawJSONtoString(R.raw.data_frutas);
        Type listTypeFrutas = new TypeToken<List<Fruta>>(){}.getType();
        List<Fruta> frutasList = gson.fromJson(jsonFrutas, listTypeFrutas);

        for(Fruta f : frutasList) {
            Log.d(tag, f.toString());
            db.frutaDao().insertAll(f);

            for (int i : f.getMeses()) {
                FrutaMes fm = new FrutaMes(f.getId(), i);
                if(f.getMesesMenos().contains(i)){
                    fm.setMenorVenta(true);
                }
                db.frutaMesDao().insertAll(fm);
            }

        }

        return true;
    }

    private String rawJSONtoString(int id){
        try {
            InputStream is = context.getResources().openRawResource(id);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } catch (Exception e) {
            return null;
        }
    }

}