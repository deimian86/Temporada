package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "fruta_mes", primaryKeys = {"fruta_id","mes_id"})
public class FrutaMes {

    @ColumnInfo(name = "fruta_id")
    private int frutaId;

    @ColumnInfo(name = "mes_id")
    private int mesId;

    public FrutaMes(int frutaId, int mesId) {
        this.frutaId = frutaId;
        this.mesId = mesId;
    }

    public int getFrutaId() {
        return frutaId;
    }

    public void setFrutaId(int frutaId) {
        this.frutaId = frutaId;
    }

    public int getMesId() { return mesId; }

    public void setMesId(int mesId) { this.mesId = mesId; }

    @Override
    public String toString() {
        return "FrutaMes{" +
                "frutaId=" + frutaId +
                ", mesId=" + mesId +
                '}';
    }
}
