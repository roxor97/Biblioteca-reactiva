package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseBuscarPorTematica implements ObtenerPorString{

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseBuscarPorTematica(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String tematica) {
        return repositorio.findByTipo(tematica)
                .map(mapper::fromCollection);
    }
}