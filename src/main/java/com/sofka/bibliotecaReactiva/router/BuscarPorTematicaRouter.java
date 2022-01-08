package com.sofka.bibliotecaReactiva.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.useCases.UseCaseBuscarPorTematica;

@Configuration
public class BuscarPorTematicaRouter {
    @Bean
    public RouterFunction<ServerResponse> buscarPorArea(UseCaseBuscarPorTematica useCaseBuscarPorArea) {
        return route(
                GET("/recursos/filtrarArea/{tematica}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseBuscarPorArea.get(request.pathVariable("tematica")), RecursoDTO.class))
        );
    }
}