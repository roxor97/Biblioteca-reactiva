package com.sofka.bibliotecaReactiva.useCases;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;

@Service
@Validated
public class UseCaseDevolverRecurso implements ObtenerDisponibilidad{
    private final RecursoRepository repositorio;
    private final MapperUtils mapper;

    public UseCaseDevolverRecurso(RecursoRepository repositorio, MapperUtils mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(r -> {
            if (!r.getDisponible()) {
                r.setDisponible(true);
                r.setFechaPrestamo(null);
                repositorio.save(r);
                return Mono.just("Recurso devuelto");
            }
            return Mono.just("Recurso no disponible");
        });
    }
}