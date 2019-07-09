package com.deimian86.verdurasdetemporada.entities.pescados;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "pescado_mes", primaryKeys = {"pescado_id","mes_id"})
public class PescadoMes {

    @ColumnInfo(name = "pescado_id")
    private int pescadoId;

    @ColumnInfo(name = "mes_id")
    private int mesId;

    @ColumnInfo(name = "menor_venta")
    private boolean menorVenta;

    public PescadoMes(int pescadoId, int mesId) {
        this.pescadoId = pescadoId;
        this.mesId = mesId;
    }

    public int getPescadoId() {
        return pescadoId;
    }

    public void setPescadoId(int pescadoId) {
        this.pescadoId = pescadoId;
    }

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public boolean isMenorVenta() { return menorVenta; }

    public void setMenorVenta(boolean menorVenta) { this.menorVenta = menorVenta; }

    @Override
    public String toString() {
        return "PescadoMes{" +
                "pescadoId=" + pescadoId +
                ", mesId=" + mesId +
                ", menorVenta=" + menorVenta +
                '}';
    }

}
