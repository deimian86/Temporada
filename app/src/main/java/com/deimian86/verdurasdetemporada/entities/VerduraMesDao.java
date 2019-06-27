package com.deimian86.verdurasdetemporada.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VerduraMesDao {

    @Query("SELECT * FROM verdura_mes WHERE verdura_id == :idVerdura")
    List<VerduraMes> findMesesPorVerdura(int idVerdura);

    @Insert
    void insertAll(VerduraMes... verduraMes);

    @Delete
    void delete(VerduraMes verduraMes);


}
