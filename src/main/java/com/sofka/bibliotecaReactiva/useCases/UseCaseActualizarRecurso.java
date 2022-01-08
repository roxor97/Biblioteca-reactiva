package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseActualizarRecurso implements GuardarRecurso{

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseActualizarRecurso(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorio.findById(recursoDTO.getId())
                .map(recurso -> {
                    recurso.setDisponible(recursoDTO.getDisponible());
                    recurso.setFechaPrestamo(recursoDTO.getFechaPrestamo());
                    recurso.setNombre(recursoDTO.getNombre());
                    recurso.setTipo(recursoDTO.getTipo());
                    return recurso;
                })
                .flatMap(repositorio::save)
                .map(mapper::fromCollection);
    }
}