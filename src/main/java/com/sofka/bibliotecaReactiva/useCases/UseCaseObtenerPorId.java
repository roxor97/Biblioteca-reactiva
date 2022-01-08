package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class UseCaseObtenerPorId implements ObtenerPorId {

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseObtenerPorId(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> get(String id) {
        return repositorio.findById(id).map(mapper::fromCollection);
    }

}