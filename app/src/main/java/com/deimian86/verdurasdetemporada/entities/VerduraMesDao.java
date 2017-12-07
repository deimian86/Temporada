package com.deimian86.verdurasdetemporada.entities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VerduraMesDao {

    @Query("SELECT * FROM verdura_mes WHERE verdura_id == :idVerdura")
    LiveData<List<VerduraMes>> findMesesPorVerdura(int idVerdura);

    @Insert
    void insertAll(VerduraMes... verduraMes);

    @Delete
    void delete(VerduraMes verduraMes);


}
