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
import com.sofka.bibliotecaReactiva.useCases.UseCaseBuscarPorTematicaYTipo;

@Configuration
public class BuscarPorTematicaYTipoRouter {
    @Bean
    public RouterFunction<ServerResponse> buscarPorAreaYTipo(UseCaseBuscarPorTematicaYTipo useCaseBuscarPorAreaYTipo) {
        return route(
                GET("/recursos/filtrar/{tematica}/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseBuscarPorAreaYTipo.get(request.pathVariable("tematica"), request.pathVariable("tipo")), RecursoDTO.class))
        );
    }
}