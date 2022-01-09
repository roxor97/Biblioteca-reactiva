package com.sofka.bibliotecaReactiva.router;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;
import com.sofka.bibliotecaReactiva.useCases.UseCaseCrear;
import com.sofka.bibliotecaReactiva.utils.Tematica;
import com.sofka.bibliotecaReactiva.utils.Tipo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, UseCaseCrear.class, MapperUtils.class})
class CrearRecursoRouterTest {

    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCrearRecurso() {

        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setTematica(Tematica.ARTES);
        recurso.setDisponible(true);
        recurso.setTipo(Tipo.DOCUMENTAL);
        recurso.setNombre("Documentales");
        recurso.setFechaPrestamo(LocalDateTime.now());

        RecursoDTO recursoDTO = new RecursoDTO(recurso.getId(),
                recurso.getTipo(),
                recurso.getDisponible(),
                recurso.getTematica(),
                recurso.getNombre(),
                recurso.getFechaPrestamo());

        Mono<Recurso> datoMono = Mono.just(recurso);

        when(repositorio.save(any())).thenReturn(datoMono);

        webTestClient.post()
                .uri("/recursos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getTematica()).isEqualTo(recurso.getTematica());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso.getTipo());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso.getNombre());
                            Assertions.assertThat(userResponse.getDisponible()).isEqualTo(recurso.getDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso.getId());
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).save(any());
    }


}