package com.sofka.bibliotecaReactiva.useCases;

import reactor.core.publisher.Mono;

public interface ObtenerDisponibilidad {
    Mono<String> get(String id);
}