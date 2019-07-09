package com.deimian86.verdurasdetemporada.entities.mariscos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "marisco_mes", primaryKeys = {"marisco_id","mes_id"})
public class MariscoMes {

    @ColumnInfo(name = "marisco_id")
    private int mariscoId;

    @ColumnInfo(name = "mes_id")
    private int mesId;

    @ColumnInfo(name = "menor_venta")
    private boolean menorVenta;

    public MariscoMes(int mariscoId, int mesId) {
        this.mariscoId = mariscoId;
        this.mesId = mesId;
    }

    public int getMariscoId() {
        return mariscoId;
    }

    public void setMariscoId(int mariscoId) {
        this.mariscoId = mariscoId;
    }

    public int getMesId() { return mesId; }

    public void setMesId(int mesId) { this.mesId = mesId; }

    public boolean isMenorVenta() { return menorVenta; }

    public void setMenorVenta(boolean menorVenta) { this.menorVenta = menorVenta; }

    @Override
    public String toString() {
        return "MariscoMes{" +
                "mariscoId=" + mariscoId +
                ", mesId=" + mesId +
                ", menorVenta=" + menorVenta +
                '}';
    }
}
