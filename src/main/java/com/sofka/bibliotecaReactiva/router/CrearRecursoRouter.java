package com.sofka.bibliotecaReactiva.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.useCases.UseCaseCrear;

@Configuration
public class CrearRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> crearRecursooo(UseCaseCrear useCaseCrear) {
        return route(POST("/recursos/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> useCaseCrear.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}