package com.deimian86.verdurasdetemporada.utils;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;

import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.frutas.FrutaDao;
import com.deimian86.verdurasdetemporada.entities.frutas.FrutaMes;
import com.deimian86.verdurasdetemporada.entities.frutas.FrutaMesDao;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.mariscos.MariscoDao;
import com.deimian86.verdurasdetemporada.entities.mariscos.MariscoMes;
import com.deimian86.verdurasdetemporada.entities.mariscos.MariscoMesDao;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.pescados.PescadoDao;
import com.deimian86.verdurasdetemporada.entities.pescados.PescadoMes;
import com.deimian86.verdurasdetemporada.entities.pescados.PescadoMesDao;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
import com.deimian86.verdurasdetemporada.entities.verduras.VerduraDao;
import com.deimian86.verdurasdetemporada.entities.verduras.VerduraMes;
import com.deimian86.verdurasdetemporada.entities.verduras.VerduraMesDao;

import org.greenrobot.eventbus.EventBus;

@Database(entities = {Verdura.class, VerduraMes.class, Fruta.class, FrutaMes.class, Pescado.class, PescadoMes.class, Marisco.class, MariscoMes.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VerduraDao verduraDao();
    public abstract VerduraMesDao verduraMesDao();
    public abstract FrutaDao frutaDao();
    public abstract FrutaMesDao frutaMesDao();
    public abstract MariscoDao mariscoDao();
    public abstract MariscoMesDao mariscoMesDao();
    public abstract PescadoDao pescadoDao();
    public abstract PescadoMesDao pescadoMesDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Callback rdc = getCallBackInstance(context);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"de-temporada-db").addCallback(rdc).build();
                }
            }
        }
        return INSTANCE;
    }

    // Crear o abrir base de datos
    private static RoomDatabase.Callback getCallBackInstance(final Context context) {
        return new RoomDatabase.Callback(){
            public void onCreate (@NonNull SupportSQLiteDatabase database){
                // Llenamos la base de datos solo tras crearla
                AppDatabase_Create_Async createDb = new AppDatabase_Create_Async(INSTANCE, context);
                createDb.execute();
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                EventBus.getDefault().post(RequestCodes.DB_OPENED);
            }
        };
    }

}
