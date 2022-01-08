package com.sofka.bibliotecaReactiva.useCases;


import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;

import reactor.core.publisher.Flux;

public interface ObtenerPorTematicaYTipo {
    Flux<RecursoDTO> get(String tematica, String tipo);
}
