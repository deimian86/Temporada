package com.deimian86.verdurasdetemporada.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.deimian86.verdurasdetemporada.entities.Fruta;
import com.deimian86.verdurasdetemporada.entities.FrutaDao;
import com.deimian86.verdurasdetemporada.entities.FrutaMes;
import com.deimian86.verdurasdetemporada.entities.FrutaMesDao;
import com.deimian86.verdurasdetemporada.entities.Mes;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraDao;
import com.deimian86.verdurasdetemporada.entities.VerduraMes;
import com.deimian86.verdurasdetemporada.entities.VerduraMesDao;

@Database(entities = {Verdura.class, VerduraMes.class, Fruta.class, FrutaMes.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VerduraDao verduraDao();
    public abstract VerduraMesDao verduraMesDao();
    public abstract FrutaDao frutaDao();
    public abstract FrutaMesDao frutaMesDao();
}
