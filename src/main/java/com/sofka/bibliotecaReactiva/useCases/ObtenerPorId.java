package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;

import reactor.core.publisher.Mono;

public interface ObtenerPorId {
    Mono<RecursoDTO> get(String id);
}
