package com.deimian86.verdurasdetemporada.utils.bus;

import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;

public class MessageEventPescado {

    private Pescado pescado;

    public MessageEventPescado(Pescado pescado) {
        this.pescado = pescado;
    }

    public Pescado getPescado() {
        return pescado;
    }

}