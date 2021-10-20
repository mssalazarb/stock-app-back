package com.pichincha.stock.integracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.stock.controller.BienController;
import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.proyection.BienesDisponiblesDadosDeBajaProyection;
import com.pichincha.stock.proyection.BienesDisponiblesProyection;
import com.pichincha.stock.service.impl.BienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(BienController.class)
public class BienControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BienServiceImpl service;

    private ProjectionFactory factory;

    @BeforeEach
    public void initialData() {
        Mockito.reset(service);
        factory = new SpelAwareProxyProjectionFactory();
    }

    private void mockBienProyectionStatus() {
        var projection = this.buildBienProyection();
        List<BienProyection> bienes = new ArrayList<>();
        bienes.add(projection);
        given(service.findAllBienesStatus()).willReturn(bienes);
    }

    private void mockBienFindByName() {
        var projection = this.buildBienProyection();
        given(service.findByNameBien(projection.getNombre())).willReturn(projection);
    }

    private void mockBienFindByNameEmpty() {
        var projection = this.buildBienProyection();
        given(service.findByNameBien(projection.getNombre())).willReturn(null);
    }

    private void mockFindByNameDetail() {
        var bien = this.buildBien();
        given(service.findByNameDetail(bien.getNombre())).willReturn(bien);
    }

    private void mockFindByNameDetailEmpty() {
        var bien = this.buildBien();
        given(service.findByNameDetail(bien.getNombre())).willReturn(null);
    }

    private void mockBienSave() {
        given(service.save(this.buildBien())).willReturn(this.buildBien());
    }

    private void mockBienesDisponibles() {
        var projection = this.buildBienesDisponibles();
        given(service.findBienesDisponiblesByCategory()).willReturn(projection);
    }

    private void mockBienesDisponiblesPorCategoria() {
        var projection = this.buildBienesDisponiblesProyection();
        given(service.findBienesDisponiblesDadosDeBajaByCategory(1)).willReturn(projection);
    }

    private void mockSaveAll() {
        List<Bien> bienes = new ArrayList<>();
        bienes.add(this.buildBien());
        doNothing().when(service).saveAll(bienes);
    }

    private void mockDarDeBaja() {
        List<Integer> bienes = new ArrayList<>();
        bienes.add(1);
        bienes.add(2);
        doNothing().when(service).darDeBaja(bienes);
    }

    private BienProyection buildBienProyection() {
        BienProyection projection = factory.createProjection(BienProyection.class);
        projection.setId(1);
        projection.setNombre("Test Bien");
        projection.setStock(100);
        projection.setIdEstado(1);
        projection.setIdCategoria(1);
        projection.setEstadoBien("Activo");

        return projection;
    }

    private List<BienesDisponiblesProyection> buildBienesDisponibles() {
        List<BienesDisponiblesProyection> list = new ArrayList<>();
        BienesDisponiblesProyection projection = factory.createProjection(BienesDisponiblesProyection.class);
        projection.setDisponibles(3);
        projection.setCategoria("Test Categoria");
        list.add(projection);

        return list;
    }

    private List<BienesDisponiblesDadosDeBajaProyection> buildBienesDisponiblesProyection() {
        List<BienesDisponiblesDadosDeBajaProyection> list = new ArrayList<>();
        BienesDisponiblesDadosDeBajaProyection projection = factory.createProjection(BienesDisponiblesDadosDeBajaProyection.class);
        projection.setDisponibles(3);
        projection.setDadosDeBaja(0);
        projection.setCategoria("Test Categoria");
        list.add(projection);

        return list;
    }

    private Bien buildBien() {
        Bien bien = new Bien();
        bien.setId(1);
        bien.setNombre("Test Bien");
        bien.setStock(100);
        bien.setIdEstado(1);
        bien.setIdCategoria(1);
        bien.setEstadoBien("Activo");

        return bien;
    }

    @Test
    void findAllBienesStatus() throws Exception {
        mockBienProyectionStatus();
        this.mockMvc.perform(get("/bien/all-estado")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Test Bien")))
                .andExpect(jsonPath("$[0].stock", is(100)));

        verify(service, times(1)).findAllBienesStatus();
    }

    @Test
    void findByNameBien() throws Exception {
        mockBienFindByName();
        Integer id = 1;
        this.mockMvc.perform(get("/bien")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("name", "Test Bien"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)))
                .andExpect(jsonPath("nombre", is("Test Bien")))
                .andExpect(jsonPath("stock", is(100)));

        verify(service, times(1)).findByNameBien("Test Bien");
    }

    @Test
    void findByNameBienEmpty() throws Exception {
        mockBienFindByNameEmpty();
        Integer id = 1;
        this.mockMvc.perform(get("/bien")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("name", "Test Bien"))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

        verify(service, times(1)).findByNameBien("Test Bien");
    }

    @Test
    void findByNameDetail() throws Exception {
        mockFindByNameDetail();
        Integer id = 1;
        this.mockMvc.perform(get("/bien/detail")
                        .queryParam("name", "Test Bien")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)))
                .andExpect(jsonPath("nombre", is("Test Bien")))
                .andExpect(jsonPath("stock", is(100)));

        verify(service, times(1)).findByNameDetail("Test Bien");
    }

    @Test
    void findByNameDetailEmpty() throws Exception {
        mockFindByNameDetailEmpty();
        Integer id = 1;
        this.mockMvc.perform(get("/bien/detail")
                        .queryParam("name", "Test Bien")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

        verify(service, times(1)).findByNameDetail("Test Bien");
    }

    @Test
    void save() throws Exception {
        mockBienSave();
        this.mockMvc.perform(post("/bien")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.buildBien()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("nombre", is("Test Bien")));

        verify(service, times(1)).save(this.buildBien());
    }

    @Test
    void findBienesDisponiblesByCategory() throws Exception {
        mockBienesDisponibles();
        this.mockMvc.perform(get("/bien/disponibles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].disponibles", is(3)))
                .andExpect(jsonPath("$[0].categoria", is("Test Categoria")));

        verify(service, times(1)).findBienesDisponiblesByCategory();
    }

    @Test
    void findBienesDisponiblesDadosDeBajaByCategory() throws Exception {
        mockBienesDisponiblesPorCategoria();
        this.mockMvc.perform(get("/bien/disponibles-categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("categoria", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].disponibles", is(3)))
                .andExpect(jsonPath("$[0].dadosDeBaja", is(0)))
                .andExpect(jsonPath("$[0].categoria", is("Test Categoria")));

        verify(service, times(1)).findBienesDisponiblesDadosDeBajaByCategory(1);
    }

    @Test
    void registerBatchBienes() throws Exception {
        mockSaveAll();
        List<Bien> bienes = new ArrayList<>();
        bienes.add(this.buildBien());
        this.mockMvc.perform(post("/bien/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bienes))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("Bienes registrados correctamente"));

        verify(service, times(1)).saveAll(bienes);
    }

    @Test
    void darDeBajaBienes() throws Exception {
        mockDarDeBaja();
        List<Integer> bienes = new ArrayList<>();
        bienes.add(1);
        bienes.add(2);
        this.mockMvc.perform(put("/bien/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bienes))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("Los bienes se dieron de baja correctamente"));

        verify(service, times(1)).darDeBaja(bienes);
    }
}
