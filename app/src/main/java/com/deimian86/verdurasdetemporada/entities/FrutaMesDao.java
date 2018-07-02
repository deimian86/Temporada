package com.deimian86.verdurasdetemporada.entities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FrutaMesDao {

    @Query("SELECT * FROM fruta_mes WHERE fruta_id == :idFruta")
    List<FrutaMes> findMesesPorFruta(int idFruta);

    @Insert
    void insertAll(FrutaMes... frutaMes);

    @Delete
    void delete(FrutaMes frutaMes);


}
