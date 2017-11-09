package com.deimian86.verdurasdetemporada.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Verdura;
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

        String json = rawJSONtoString();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Verdura>>(){}.getType();
        List<Verdura> verdurasList = (List<Verdura>)  gson.fromJson(json, listType);

        for(Verdura v : verdurasList) {
            Log.d(tag, v.toString());
            db.verduraDao().insertAll(v);
        }

        return true;
    }

    private String rawJSONtoString(){
        try {
            InputStream is = context.getResources().openRawResource(R.raw.data);
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