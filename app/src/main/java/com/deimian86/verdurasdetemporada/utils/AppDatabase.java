package com.deimian86.verdurasdetemporada.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraDao;

@Database(entities = {Verdura.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VerduraDao verduraDao();
}
