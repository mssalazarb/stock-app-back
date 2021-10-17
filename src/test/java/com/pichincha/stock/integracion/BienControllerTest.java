package com.pichincha.stock.integracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.stock.controller.BienController;
import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.service.BienService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private BienService service;

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

    private void mockBienFindById() {
        var projection = this.buildBienProyection();
        given(service.findById(projection.getId())).willReturn(projection);
    }

    private void mockFindByIdDetail() {
        var bien = this.buildBien();
        given(service.findByIdDetail(bien.getId())).willReturn(bien);
    }

    private void mockBienSave() {
        given(service.save(this.buildBien())).willReturn(this.buildBien());
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
    void findById() throws Exception {
        mockBienFindById();
        Integer id = 1;
        this.mockMvc.perform(get("/bien")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)))
                .andExpect(jsonPath("nombre", is("Test Bien")))
                .andExpect(jsonPath("stock", is(100)));

        verify(service, times(1)).findById(id);
    }

    @Test
    void findByIdDetail() throws Exception {
        mockFindByIdDetail();
        Integer id = 1;
        this.mockMvc.perform(get("/bien/detail")
                        .queryParam("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)))
                .andExpect(jsonPath("nombre", is("Test Bien")))
                .andExpect(jsonPath("stock", is(100)));

        verify(service, times(1)).findByIdDetail(id);
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
}
