package com.postech.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.orderservice.entities.Pedido;
import com.postech.orderservice.repositories.ClienteRepository;
import com.postech.orderservice.repositories.PedidoRepository;
import com.postech.orderservice.service.impl.PedidoServiceImpl;
import com.postech.orderservice.service.rest.EnvioServiceClient;
import com.postech.orderservice.utils.PedidoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class PedidoServiceTest {
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private EnvioServiceClient envioServiceClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;

    AutoCloseable openMocks;


    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoService = new PedidoServiceImpl(pedidoRepository, restTemplate, objectMapper, envioServiceClient);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }


    @Nested
    class RegistrarPedido {
        @Test
        void devePermitirRegistrarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();

            when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

            var pedidoReg = pedidoService.criarPedido(pedido, 1L);

            assertThat(pedidoReg).isInstanceOf(Pedido.class).isNotNull();
            assertThat(pedidoReg.getIdPedido()).isEqualTo(pedido.getIdPedido());
            assertThat(pedidoReg.getTotalPedido()).isNotNull();
        }
    }

    @Nested
    class BuscarPedido {
        @Test
        void devePermitirBuscarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();
            var idPedido = 1L;

            when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));

            var pedidoReg = pedidoService.buscarPedidoPorId(any(Long.class));

            assertThat(pedidoReg).isInstanceOf(Pedido.class).isNotNull();
            assertThat(pedidoReg.getIdPedido()).isEqualTo(idPedido);
            verify(pedidoRepository, times(1)).findById(anyLong());
        }
    }

    @Nested
    class AlterarPedido {
        @Test
        void devePermitirAlterarPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();
            var idPedido = 1L;
            var pedidoUpdate = pedido;
            pedidoUpdate.setTotalPedido(BigDecimal.valueOf(80));

            when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));
            when(pedidoRepository.save(any(Pedido.class))).thenAnswer(p -> p.getArgument(0));

            var pedidoReg = pedidoService.atualizarPedido(idPedido, pedidoUpdate);

            assertThat(pedidoReg).isInstanceOf(Pedido.class).isNotNull();
            assertThat(pedidoReg.getIdPedido()).isEqualTo(idPedido);
            assertThat(pedidoReg.getTotalPedido()).isEqualTo(BigDecimal.valueOf(80));
            verify(pedidoRepository, times(1)).findById(anyLong());
        }
    }

    @Nested
    class ExcluirPedido {
        @Test
        void devePermitirExcluirPedido() throws Exception {
            var pedido = PedidoHelper.gerarPedido();
            var idPedido = 1L;

            when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));
            doNothing().when(pedidoRepository).deleteById(idPedido);

            var resultado = pedidoService.deletarPedido(idPedido);

            assertThat(resultado).isTrue();
            verify(pedidoRepository, times(1)).findById(anyLong());
            verify(pedidoRepository, times(1)).delete(any(Pedido.class));

        }
    }

}
