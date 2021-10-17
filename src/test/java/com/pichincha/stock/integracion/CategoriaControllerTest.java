package com.pichincha.stock.integracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.stock.controller.CategoriaController;
import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.exception.CustomRuntimeException;
import com.pichincha.stock.proyection.CategoriaProyection;
import com.pichincha.stock.service.CategoriaService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CategoriaService service;

    @BeforeEach
    public void initialData() {
        Mockito.reset(service);
    }

    private void mockCategoriaProyection() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        CategoriaProyection projection = factory.createProjection(CategoriaProyection.class);
        projection.setId(1);
        projection.setNombre("Test Categoria");
        List<CategoriaProyection> categorias = new ArrayList<>();
        categorias.add(projection);
        given(service.findAllWithoutDetail()).willReturn(categorias);
    }

    private void mockCategoriaFindAll() {
        List<Categoria> categoriasT = new ArrayList<>();
        categoriasT.add(this.buildCategoria());
        given(service.findAll()).willReturn(categoriasT);
    }

    private void mockCategoriaFindByNombre() {
        String name = "Test Categoria";
        given(service.findByNombre(name)).willReturn(this.buildCategoria());
    }

    private void mockCategoriaFindByNombreEmpty() {
        String name = "Test Categoria";
        given(service.findByNombre(name)).willReturn(null);
    }

    private void mockCategoriaSave() {
        given(service.save(this.buildCategoria())).willReturn(this.buildCategoria());
    }

    private void mockCategoriaSaveGenerateError() {
        var category = this.buildCategoria();
        category.setId(5);
        given(service.save(category)).willThrow(new CustomRuntimeException("Ya existe una categoría con el nombre ingresado"));
    }

    private void mockCategoriaUpdate() {
        given(service.update(this.buildCategoria())).willReturn(this.buildCategoria());
    }

    private Categoria buildCategoria() {
        Categoria category = new Categoria();
        category.setId(3);
        category.setNombre("Test Categoria");

        return category;
    }

    @Test
    void findAllWithoutDetail() throws Exception {
        mockCategoriaProyection();
        this.mockMvc.perform(get("/categoria/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Test Categoria")));

        verify(service, times(1)).findAllWithoutDetail();
    }

    @Test
    void findAll() throws Exception {
        mockCategoriaFindAll();
        this.mockMvc.perform(get("/categoria/all-detail")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].nombre", is("Test Categoria")));

        verify(service, times(1)).findAll();
    }

    @Test
    void findByNombreCorrect() throws Exception {
        mockCategoriaFindByNombre();
        String name = "Test Categoria";
        this.mockMvc.perform(get("/categoria")
                        .queryParam("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("nombre", is(name)));

        verify(service, times(1)).findByNombre(name);
    }

    @Test
    void findByNombreNoContent() throws Exception {
        mockCategoriaFindByNombreEmpty();
        String name = "Test Categoria";
        this.mockMvc.perform(get("/categoria")
                        .queryParam("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).findByNombre(name);
    }

    @Test
    void save() throws Exception {
        mockCategoriaSave();
        this.mockMvc.perform(post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.buildCategoria()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("nombre", is("Test Categoria")));

        verify(service, times(1)).save(this.buildCategoria());
    }

    @Test
    void saveGenerateError() throws Exception {
        mockCategoriaSaveGenerateError();
        var category = this.buildCategoria();
        category.setId(5);
        this.mockMvc.perform(post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(category))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(service, times(1)).save(category);
    }

    @Test
    void update() throws Exception {
        mockCategoriaUpdate();
        this.mockMvc.perform(put("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.buildCategoria()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("nombre", is("Test Categoria")));

        verify(service, times(1)).update(this.buildCategoria());
    }
}
