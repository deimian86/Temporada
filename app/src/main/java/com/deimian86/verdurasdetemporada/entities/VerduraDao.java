package com.deimian86.verdurasdetemporada.entities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface VerduraDao {

    @Query("SELECT * FROM verdura")
    LiveData<List<Verdura>> getAll();

    @Query("SELECT * FROM verdura WHERE uid IN (:ids)")
    LiveData<List<Verdura>> findAllByIds(int[] ids);

    @Query("SELECT * FROM verdura WHERE nombre LIKE :nombre LIMIT 1")
    LiveData<Verdura> findByName(String nombre);

    @Insert
    void insertAll(Verdura... verduras);

    @Delete
    void delete(Verdura verdura);


}
