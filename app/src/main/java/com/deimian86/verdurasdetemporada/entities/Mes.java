package com.deimian86.verdurasdetemporada.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormatSymbols;

@Entity
public class Mes {

    private static int MAX_STR_LENGTH = 3;

    @PrimaryKey
    private int id;

    public Mes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return new DateFormatSymbols().getMonths()[this.getId() - 1];
    }

    public String getNombreCorto() {
        return new DateFormatSymbols().getShortMonths()[this.getId() - 1];
    }

    public String getNombreCortoFormateado(){
        return StringUtils.substring(getNombreCorto(), 0, MAX_STR_LENGTH).toUpperCase();
    }

    @Override
    public String toString() {
        return "Mes{" +
                "id=" + id +
                " nombre=" + getNombre() +
                " nombreCorto=" + getNombreCorto() +
                " nombreCortoFormateado=" + getNombreCortoFormateado() +
                '}';
    }


}
