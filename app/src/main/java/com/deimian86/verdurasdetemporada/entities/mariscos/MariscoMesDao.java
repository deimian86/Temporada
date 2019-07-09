package com.deimian86.verdurasdetemporada.entities.mariscos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.deimian86.verdurasdetemporada.entities.verduras.VerduraMes;

import java.util.List;

@Dao
public interface MariscoMesDao {

    @Query("SELECT * FROM marisco_mes WHERE marisco_id == :idMarisco")
    List<MariscoMes> findMesesPorMarisco(int idMarisco);

    @Insert
    void insertAll(MariscoMes... mariscoMes);

    @Delete
    void delete(MariscoMes mariscoMes);


}
