package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Verdura {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "foto")
    private String foto;

    @Ignore
    private List<Integer> meses = new ArrayList<>();

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

    public List<Integer> getMeses() {
        return meses;
    }

    public void setMeses(List<Integer> meses) {
        this.meses = meses;
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
