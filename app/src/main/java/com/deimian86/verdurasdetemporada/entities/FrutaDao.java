package com.deimian86.verdurasdetemporada.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
