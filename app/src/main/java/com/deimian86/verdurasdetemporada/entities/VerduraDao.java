package com.deimian86.verdurasdetemporada.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface VerduraDao {

    @Query("SELECT * FROM verdura")
    List<Verdura> getAll();

    @Query("SELECT * FROM verdura WHERE id IN (:ids)")
    LiveData<List<Verdura>> findAllByIds(int[] ids);

    @Query("SELECT * FROM verdura WHERE nombre LIKE :nombre LIMIT 1")
    LiveData<Verdura> findByName(String nombre);

    @Insert
    void insertAll(Verdura... verduras);

    @Delete
    void delete(Verdura verdura);


}
