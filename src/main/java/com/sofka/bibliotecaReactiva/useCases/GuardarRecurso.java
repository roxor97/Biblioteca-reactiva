package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GuardarRecurso {

    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}