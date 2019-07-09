package com.deimian86.verdurasdetemporada.entities.pescados;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PescadoMesDao {

    @Query("SELECT * FROM pescado_mes WHERE pescado_id == :idPescado")
    List<PescadoMes> findMesesPorPescado(int idPescado);
    @Insert
    void insertAll(PescadoMes... pescadoMes);

    @Delete
    void delete(PescadoMes pescadoMes);


}
