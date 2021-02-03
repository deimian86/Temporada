package com.deimian86.verdurasdetemporada.entities.meses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormatSymbols;

@Entity
public class Mes {

    private static int MAX_STR_LENGTH = 3;

    public static final int ENERO         = 1;
    public static final int FEBRERO       = 2;
    public static final int MARZO         = 3;
    public static final int ABRIL         = 4;
    public static final int MAYO          = 5;
    public static final int JUNIO         = 6;
    public static final int JULIO         = 7;
    public static final int AGOSTO        = 8;
    public static final int SEPTIEMBRE    = 9;
    public static final int OCTUBRE       = 10;
    public static final int NOVIEMBRE     = 11;
    public static final int DICIEMBRE     = 12;

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
