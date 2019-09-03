package com.deimian86.verdurasdetemporada.utils.bus;

import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;

public class MessageEventFruta {

    private Fruta fruta;

    public MessageEventFruta(Fruta fruta) {
        this.fruta = fruta;
    }

    public Fruta getFruta() {
        return fruta;
    }

}