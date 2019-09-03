package com.deimian86.verdurasdetemporada.utils.bus;

import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;

public class MessageEventVerdura {

    private Verdura verdura;

    public MessageEventVerdura(Verdura verdura) {
        this.verdura = verdura;
    }

    public Verdura getVerdura() {
        return verdura;
    }

}