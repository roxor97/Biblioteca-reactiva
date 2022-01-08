package com.sofka.bibliotecaReactiva.DTOs;

import java.time.LocalDateTime;

import com.sofka.bibliotecaReactiva.utils.Tematica;
import com.sofka.bibliotecaReactiva.utils.Tipo;

public class RecursoDTO {

    private String id;
    private Tipo tipo;
    private boolean disponible;
    private Tematica tematica;
    private String nombre;
    private LocalDateTime fechaPrestamo;


    public RecursoDTO(Tipo tipo, Tematica tematica, String nombre) {
        this.tipo = tipo;
        this.tematica = tematica;
        this.nombre = nombre;
    }

    public RecursoDTO(String id, Tipo tipo, boolean disponible, Tematica tematica, String nombre, LocalDateTime fechaPrestamo) {
        this.id = id;
        this.tipo = tipo;
        this.disponible = disponible;
        this.tematica = tematica;
        this.nombre = nombre;
        this.fechaPrestamo = fechaPrestamo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    

    
}