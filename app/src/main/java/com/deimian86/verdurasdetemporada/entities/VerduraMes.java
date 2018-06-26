package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "verdura_mes", primaryKeys = {"verdura_id","mes_id"})
public class VerduraMes {

    @ColumnInfo(name = "verdura_id")
    private int verduraId;

    @ColumnInfo(name = "mes_id")
    private int mesId;

    @ColumnInfo(name = "menor_venta")
    private boolean menorVenta;

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

    public boolean isMenorVenta() { return menorVenta; }

    public void setMenorVenta(boolean menorVenta) { this.menorVenta = menorVenta; }

    @Override
    public String toString() {
        return "VerduraMes{" +
                "verduraId=" + verduraId +
                ", mesId=" + mesId +
                ", menorVenta=" + menorVenta +
                '}';
    }
}
