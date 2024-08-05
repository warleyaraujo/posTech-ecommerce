package br.com.fiap.gestaoprodutos.controller;

import br.com.fiap.gestaoprodutos.controllers.ProdutoController;
import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.handler.GlobalExceptionHandler;
import br.com.fiap.gestaoprodutos.service.ProdutoService;
import br.com.fiap.gestaoprodutos.utils.ProdutoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProdutoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ProdutoController produtoController = new ProdutoController(produtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarProduto {
        @Test
        void deveRegistrarProduto() throws Exception {
            var produto = ProdutoHelper.gerarProduto();

            when(produtoService.salvarProduto(any(Produto.class))).thenAnswer(p -> p.getArgument(0));

            mockMvc.perform(post("/produtos/cadastroProduto")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(produto)))
                    .andExpect(status().isCreated());

            verify(produtoService, times(1)).salvarProduto(any(Produto.class));
        }
    }

    @Nested
    class BuscarProduto {
        @Test
        void deveBuscarProduto() throws Exception {
            var id = 1L;
            var produto = ProdutoHelper.gerarProduto();

            when(produtoService.getProdutoPorId(any(Long.class))).thenReturn(produto);

            mockMvc.perform(get("/produtos/getProduto/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(produto.getId().toString()));

            verify(produtoService, times(1)).getProdutoPorId(any(Long.class));
        }
    }

    @Nested
    class EditarProduto {
        @Test
        void deveEditarProduto() throws Exception {
            var id = 1L;
            var produto = ProdutoHelper.gerarProduto();

            when(produtoService.updateProduto(any(Long.class), any(Produto.class)))
                    .thenAnswer(p -> p.getArgument(1));

            mockMvc.perform(put("/produtos/atualizaProduto/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(produto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(produto.getId().toString()));

            verify(produtoService, times(1)).updateProduto(any(Long.class), any(Produto.class));
        }
    }

    @Nested
    class ExcluirProduto {
        @Test
        void deveExcluirProduto() throws Exception {
            var id = 1L;

            when(produtoService.deleteProduto(any(Long.class))).thenReturn(true);

            mockMvc.perform(delete("/produtos/deleteProduto/{id}", id))
                    .andExpect(status().isNoContent());

            verify(produtoService, times(1))
                    .deleteProduto(any(Long.class));
        }
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}