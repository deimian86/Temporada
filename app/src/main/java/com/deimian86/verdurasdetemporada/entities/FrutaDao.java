package com.deimian86.verdurasdetemporada.entities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FrutaDao {

    @Query("SELECT * FROM fruta")
    List<Fruta> getAll();

    @Query("SELECT * FROM fruta WHERE id IN (:ids)")
    LiveData<List<Fruta>> findAllByIds(int[] ids);

    @Query("SELECT * FROM fruta WHERE nombre LIKE :nombre LIMIT 1")
    LiveData<Fruta> findByName(String nombre);

    @Insert
    void insertAll(Fruta... frutas);

    @Delete
    void delete(Fruta fruta);


}
