package com.mats.alertavecinal.com.mats.alertavecinal.modelos;

import android.location.Location;

import java.util.ArrayList;

public class Grupo {
    private String id;
    private String nombre;
    private String ubicacion;
    private ArrayList<Mensaje> mensajes;

    public Grupo(String id, String nombre, String ubicacion) {
        //mensajes = new ArrayList<Mensaje>();
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
