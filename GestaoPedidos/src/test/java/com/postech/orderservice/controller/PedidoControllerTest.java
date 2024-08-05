package com.postech.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.orderservice.controllers.PedidoController;
import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.handler.GlobalExceptionHandler;
import com.postech.orderservice.service.PedidoService;
import com.postech.orderservice.utils.PedidoHelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class PedidoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks = MockitoAnnotations.openMocks(this);
        PedidoController pedidoController = new PedidoController(pedidoService);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
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
    class RegistrarPedido {
        @Test
        void deveRegistrarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();

            when(pedidoService.criarPedido(pedido, 1L)).thenAnswer(p -> p.getArgument(0));

            mockMvc.perform(post("/pedidos?idCliente={idCliente}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(pedido)))
                    .andExpect(status().isCreated());

            verify(pedidoService, times(1)).criarPedido(any(Pedido.class), anyLong());
        }
    }

    @Nested
    class BuscarPedido {
        @Test
        void deveBuscarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();
            var idPedido = 1L;

            when(pedidoService.buscarPedidoPorId(any(Long.class))).thenReturn(pedido);

            mockMvc.perform(get("/pedidos/{id}", idPedido)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idPedido").value(pedido.getIdPedido().toString()));

            verify(pedidoService, times(1)).buscarPedidoPorId(any(Long.class));
        }
    }

    @Nested
    class EditarPedido {
        @Test
        void deveEditarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();
            var idPedido = 1L;

            when(pedidoService.atualizarPedido(any(Long.class), any(Pedido.class))).thenAnswer(p -> p.getArgument(1));

            mockMvc.perform(put("/pedidos/{id}", idPedido)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(pedido)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idPedido").value(pedido.getIdPedido().toString()));

            verify(pedidoService, times(1)).atualizarPedido(any(Long.class), any(Pedido.class));
        }
    }

    @Nested
    class ExcluirPedido {
        @Test
        void deveExcluirPedido() throws Exception {
            var id = 1L;

            when(pedidoService.deletarPedido(any(Long.class))).thenReturn(true);

            mockMvc.perform(delete("/pedidos/{id}", id))
                    .andExpect(status().isNoContent());

            verify(pedidoService, times(1))
                    .deletarPedido(any(Long.class));
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
