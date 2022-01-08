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
import com.sofka.bibliotecaReactiva.useCases.UseCaseObtenerPorId;

@Configuration
public class ObtenerPorIdRouter {

    @Bean
    public RouterFunction<ServerResponse> obtenerPorId(UseCaseObtenerPorId useCaseObtenerPorId) {
        return route(
                GET("/recursos/consultar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseObtenerPorId.get(request.pathVariable("id")), RecursoDTO.class))
        );
    }
}