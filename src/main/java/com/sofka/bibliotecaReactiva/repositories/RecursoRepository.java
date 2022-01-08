package com.sofka.bibliotecaReactiva.repositories;

import com.sofka.bibliotecaReactiva.collections.Recurso;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String>{
    
    Flux<Recurso> findByArea(String area);
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByAreaAndTipo(String area, String tipo);

}
