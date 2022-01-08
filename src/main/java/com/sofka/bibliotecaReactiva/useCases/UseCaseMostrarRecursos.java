package com.sofka.bibliotecaReactiva.useCases;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

@Service
@Validated
public class UseCaseMostrarRecursos implements Supplier<Flux<RecursoDTO>> {

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseMostrarRecursos(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repositorio.findAll()
                .map(mapper::fromCollection);
    }
}