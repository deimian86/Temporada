package com.deimian86.verdurasdetemporada.entities.frutas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
