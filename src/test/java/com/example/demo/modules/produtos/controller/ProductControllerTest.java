package com.example.demo.modules.produtos.controller;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:/produtoController.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {

    public static String URL_PRODUTOS = "/api/products/";

    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    public void validaFiltroGeral_deveRetornarPageProdutosFiltrados_quandoSemFiltroEComPage() {
        mvc.perform(get(URL_PRODUTOS + "search/page")
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.*", hasSize(6)));
    }

    @SneakyThrows
    @Test
    public void validaFiltroDescricao_deveRetornarProdutosFiltrados_quandoComFiltroDesc() {
        mvc.perform(get(URL_PRODUTOS + "search")
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
                .param("description", "produto id 300"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(300)));
    }

    @SneakyThrows
    @Test
    public void validaFiltroNome_deveRetornarProdutosFiltrados_quandoComFiltroDesc() {
        mvc.perform(get(URL_PRODUTOS + "search")
                .accept(MediaType.APPLICATION_JSON)
                .param("name", "produto 3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(300)));
    }

    @SneakyThrows
    @Test
    public void validaFiltroEntrePrecos_deveRetornarProdutosFiltrados_quandoComFiltroDesc() {
        mvc.perform(get(URL_PRODUTOS + "search")
                .accept(MediaType.APPLICATION_JSON)
                .param("min_price", "200.00")
                .param("max_price", "350.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


}
