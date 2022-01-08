package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;

import reactor.core.publisher.Flux;

public interface ObtenerPorString {
    Flux<RecursoDTO> get(String string);
}