package com.deimian86.verdurasdetemporada.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.frutas.FrutaMes;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.mariscos.MariscoMes;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.pescados.PescadoMes;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
import com.deimian86.verdurasdetemporada.entities.verduras.VerduraMes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AppDatabase_Populate_Async extends AsyncTask<Void, Boolean, Boolean> {

    private String tag = this.getClass().getName();
    private AppDatabase db;
    private Context context;

    public AppDatabase_Populate_Async(AppDatabase db, Context context) {
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

        // Cargando pescados en la base de datos

        String jsonPescados = rawJSONtoString(R.raw.data_pescados);
        Type listTypePescados = new TypeToken<List<Pescado>>(){}.getType();
        List<Pescado> pescadoList = gson.fromJson(jsonPescados, listTypePescados);

        for(Pescado p : pescadoList) {
            Log.d(tag, p.toString());
            db.pescadoDao().insertAll(p);

            for (int i : p.getMeses()) {
                PescadoMes pm = new PescadoMes(p.getId(), i);
                if(p.getMesesMenos().contains(i)){
                    pm.setMenorVenta(true);
                }
                db.pescadoMesDao().insertAll(pm);
            }

        }

        // Cargando mariscos en la base de datos

        String jsonMariscos = rawJSONtoString(R.raw.data_mariscos);
        Type listTypeMariscos = new TypeToken<List<Marisco>>(){}.getType();
        List<Marisco> mariscosList = gson.fromJson(jsonMariscos, listTypeMariscos);

        for(Marisco m : mariscosList) {
            Log.d(tag, m.toString());
            db.mariscoDao().insertAll(m);

            for (int i : m.getMeses()) {
                MariscoMes mm = new MariscoMes(m.getId(), i);
                if(m.getMesesMenos().contains(i)){
                    mm.setMenorVenta(true);
                }
                db.mariscoMesDao().insertAll(mm);
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
                Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
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

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        EventBus.getDefault().post(RequestCodes.DB_CREATED);
    }
}