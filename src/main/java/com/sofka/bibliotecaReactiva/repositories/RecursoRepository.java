package com.sofka.bibliotecaReactiva.repositories;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.utils.Tematica;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String>{
    
    Flux<Recurso> findByTematica(String string);
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByTematicaAndTipo(String tematica, String tipo);

}
