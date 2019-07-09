package com.deimian86.verdurasdetemporada.entities.mariscos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MariscoDao {

    @Query("SELECT * FROM marisco")
    List<Marisco> getAll();

    @Query("SELECT * FROM marisco WHERE id IN (:ids)")
    LiveData<List<Marisco>> findAllByIds(int[] ids);

    @Query("SELECT * FROM marisco WHERE nombre LIKE :nombre LIMIT 1")
    LiveData<Marisco> findByName(String nombre);

    @Insert
    void insertAll(Marisco... mariscos);

    @Delete
    void delete(Marisco marisco);


}
