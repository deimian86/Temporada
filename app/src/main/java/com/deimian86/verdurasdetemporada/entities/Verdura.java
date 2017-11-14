package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Verdura {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "foto")
    private String foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Verdura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
