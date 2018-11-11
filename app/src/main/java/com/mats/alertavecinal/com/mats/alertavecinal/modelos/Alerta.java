package com.mats.alertavecinal.com.mats.alertavecinal.modelos;

import java.util.Date;

public class Alerta {
    private Date fecha;
    private String foto;// hay que saber bien como modelar esto;

    public Alerta(Date fecha, String foto) {
        this.fecha = fecha;
        this.foto = foto;
    }
}
