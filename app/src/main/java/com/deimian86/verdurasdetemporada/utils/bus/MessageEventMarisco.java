package com.deimian86.verdurasdetemporada.utils.bus;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;

public class MessageEventMarisco {

    private Marisco marisco;

    public MessageEventMarisco(Marisco marisco) {
        this.marisco = marisco;
    }

    public Marisco getMarisco() {
        return marisco;
    }

}