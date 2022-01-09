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
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;
import com.sofka.bibliotecaReactiva.useCases.UseCaseMostrarRecursos;
import com.sofka.bibliotecaReactiva.utils.Tematica;
import com.sofka.bibliotecaReactiva.utils.Tipo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MostrarRecursosRouter.class, UseCaseMostrarRecursos.class, MapperUtils.class})
class MostrarRecursosRouterTest {

    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetDatos() {

        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setTematica(Tematica.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFechaPrestamo(LocalDateTime.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setTematica(Tematica.CIENCIAS);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.LIBRO);
        recurso2.setNombre("Libro");
        recurso2.setFechaPrestamo(LocalDateTime.now());

        when(repositorio.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.get(1).getNombre()).isEqualTo(recurso2.getNombre());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findAll();
    }

}