package com.deimian86.verdurasdetemporada.entities.pescados;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PescadoDao {

    @Query("SELECT * FROM pescado")
    List<Pescado> getAll();

    @Query("SELECT * FROM pescado WHERE id IN (:ids)")
    LiveData<List<Pescado>> findAllByIds(int[] ids);

    @Query("SELECT * FROM pescado WHERE nombre LIKE :nombre LIMIT 1")
    LiveData<Pescado> findByName(String nombre);

    @Insert
    void insertAll(Pescado... pescados);

    @Delete
    void delete(Pescado pescado);


}
