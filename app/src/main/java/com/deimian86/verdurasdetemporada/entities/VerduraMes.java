package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "verdura_mes", primaryKeys = {"verdura_id","mes_id"})
public class VerduraMes {

    @ColumnInfo(name = "verdura_id")
    private int verduraId;

    @ColumnInfo(name = "mes_id")
    private int mesId;

    public VerduraMes(int verduraId, int mesId) {
        this.verduraId = verduraId;
        this.mesId = mesId;
    }

    public int getVerduraId() {
        return verduraId;
    }

    public void setVerduraId(int verduraId) {
        this.verduraId = verduraId;
    }

    public int getMesId() { return mesId; }

    public void setMesId(int mesId) { this.mesId = mesId; }

    @Override
    public String toString() {
        return "VerduraMes{" +
                "verduraId=" + verduraId +
                ", mesId=" + mesId +
                '}';
    }
}
