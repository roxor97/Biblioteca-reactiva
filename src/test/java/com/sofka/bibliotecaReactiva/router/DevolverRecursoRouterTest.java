package com.sofka.bibliotecaReactiva.router;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;
import com.sofka.bibliotecaReactiva.useCases.UseCaseDevolverRecurso;
import com.sofka.bibliotecaReactiva.utils.Tematica;
import com.sofka.bibliotecaReactiva.utils.Tipo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DevolverRecursoRouter.class, UseCaseDevolverRecurso.class, MapperUtils.class})
class DevolverRecursoRouterTest {

    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void DevolverRecursoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setTematica(Tematica.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFechaPrestamo(LocalDateTime.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/devolver/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("El recurso no está prestado"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");
    }

}