package com.mats.alertavecinal.com.mats.alertavecinal.modelos;

import java.util.ArrayList;
import java.util.Date;

public class Vecino {
    private String nombre;
    private String apellido;
    private Integer dni;
    private Integer telefono;
    private Date fechaNacimiento;
    private String email;
    private Usuario usuario;
    private ArrayList<Grupo> grupos;

    public Vecino(){

    }

    public Vecino(String n, String a, Integer d, Integer t, Date f, String e, Usuario us){
        this.nombre = n;
        this.apellido = a;
        this.dni = d;
        this.telefono = t;
        this.fechaNacimiento = f;
        this.email = e;
        this.usuario = us;
        grupos = new ArrayList<>();
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void addGrupo(Grupo g){
        this.grupos.add(g);
    }
}
