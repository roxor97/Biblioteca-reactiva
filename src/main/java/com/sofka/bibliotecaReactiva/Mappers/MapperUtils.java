package com.sofka.bibliotecaReactiva.Mappers;

import com.mongodb.Function;
import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.collections.Recurso;

import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    public Function<RecursoDTO, Recurso> mapperToRecurso(){
        return recursoDTO -> {
            Recurso recurso = new Recurso();
            recurso.setId(recursoDTO.getId());
            recurso.setTipo(recursoDTO.getTipo());
            recurso.setTematica(recursoDTO.getTematica());
            recurso.setEstaDisponible(recursoDTO.getDisponibilidad());
            recurso.setFechaPrestamo(recursoDTO.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapperToRecursoDTO(){
        return recurso -> new RecursoDTO(
                recurso.getId(),
                recurso.getTipo(),
                recurso.getTematica(),
                recurso.getFechaPrestamo(),
                recurso.getEstaDisponible()
        );
    }
}
