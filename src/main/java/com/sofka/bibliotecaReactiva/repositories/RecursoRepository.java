package com.sofka.bibliotecaReactiva.repositories;

import com.sofka.bibliotecaReactiva.collections.Recurso;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String>{
    
    Flux<Recurso> findByTematica(String tematica);
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByTematicaAndTipo(String tematica, String tipo);

}
