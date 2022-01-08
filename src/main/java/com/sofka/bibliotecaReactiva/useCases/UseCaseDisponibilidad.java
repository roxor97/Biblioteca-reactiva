package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class UseCaseDisponibilidad implements ObtenerDisponibilidad{

    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseDisponibilidad(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        return repositorio.findById(id).map(r->
                r.getDisponible() ?
                        "El material se encuentra disponible"
                        : "El material no se encuntra disponible, fue prestado el: "+ r.getFechaPrestamo()
        );
    }
}