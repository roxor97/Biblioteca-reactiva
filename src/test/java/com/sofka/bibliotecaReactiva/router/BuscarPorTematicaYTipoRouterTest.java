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
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import com.sofka.bibliotecaReactiva.Mappers.MapperUtils;
import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.repositories.RecursoRepository;
import com.sofka.bibliotecaReactiva.useCases.UseCaseBuscarPorTematicaYTipo;
import com.sofka.bibliotecaReactiva.utils.Tematica;
import com.sofka.bibliotecaReactiva.utils.Tipo;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {BuscarPorTematicaYTipoRouter.class, UseCaseBuscarPorTematicaYTipo.class, MapperUtils.class})
class BuscarPorTematicaYTipoRouterTest {

    @MockBean
    RecursoRepository repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarPorAreaYTipoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("789");
        recurso1.setTematica(Tematica.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documentales");
        recurso1.setFechaPrestamo(LocalDateTime.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("10112");
        recurso2.setTematica(Tematica.ARTES);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.DOCUMENTAL);
        recurso2.setNombre("Libro un punto palido");
        recurso2.setFechaPrestamo(LocalDateTime.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByTematicaAndTipo(recurso1.getTematica().toString(),
                recurso1.getTipo().toString()))
                .thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrar/ARTES/DOCUMENTAL")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getTematica()).isEqualTo(recurso1.getTematica());
                            Assertions.assertThat(userResponse.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.get(1).getTematica()).isEqualTo(recurso2.getTematica());
                            Assertions.assertThat(userResponse.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByTematicaAndTipo("ARTES", "DOCUMENTAL");
    }

}