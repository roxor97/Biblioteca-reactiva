package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseEliminarRecurso implements BorrarRecurso {

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseEliminarRecurso(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repositorio.deleteById(id);
    }
}