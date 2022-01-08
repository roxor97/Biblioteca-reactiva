package com.sofka.bibliotecaReactiva.Mappers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.mongodb.Function;
import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.collections.Recurso;

import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    public Function<RecursoDTO, Recurso> mapperToRecurso(String string){
        return recursoDTO -> {
            Recurso recurso = new Recurso();
            recurso.setId(recursoDTO.getId());
            recurso.setTipo(recursoDTO.getTipo());
            recurso.setTematica(recursoDTO.getTematica());
            recurso.setDisponible(recursoDTO.getDisponible());
            recurso.setFechaPrestamo(recursoDTO.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapperToRecursoDTO(){
        return recurso -> new RecursoDTO(
            recurso.getId(),
            recurso.getTipo(),
            recurso.getDisponible(),
            recurso.getTematica(),
            recurso.getNombre(),
            recurso.getFechaPrestamo()
        );
    }
    public Recurso fromDTO(RecursoDTO dto){
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setTematica(dto.getTematica());
        recurso.setDisponible(dto.getDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setNombre(dto.getNombre());
        recurso.setTipo(dto.getTipo());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection){
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setTematica(collection.getTematica());
        recursoDTO.setDisponible(collection.getDisponible());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setTipo(collection.getTipo());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollection(List<Recurso> collection){
        if(collection==null){
            return null;
        }
        List<RecursoDTO> recursoDTOs = new ArrayList<>();
        for(Recurso recurso : collection){
            recursoDTOs.add(fromCollection(recurso));
        }
        return recursoDTOs;
    }



    
}
