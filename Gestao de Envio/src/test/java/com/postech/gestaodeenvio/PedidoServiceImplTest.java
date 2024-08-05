package com.postech.gestaodeenvio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.gestaodeenvio.entities.*;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.InserirFretesRequest;
import com.postech.gestaodeenvio.services.OrdersService;
import com.postech.gestaodeenvio.services.impl.PedidoServiceImpl;
import com.postech.gestaodeenvio.services.integracao.MelhorEnvioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoServiceImplTest {

    @Mock
    private MelhorEnvioClient melhorEnvioClient;

    @Mock
    private OrdersService ordersService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PedidoServiceImpl pedidoServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testImportarPedido() throws IOException, InterruptedException {
        Pedido pedido = criarPedido(); // Inicialize o pedido com dados necessários

        ResponseEntity<String> responseEntity = ResponseEntity.ok("response json");

        when(melhorEnvioClient.inserirFrete(any(InserirFretesRequest.class)))
                .thenAnswer(invocation -> {

                    return responseEntity;
                });

        ResponseEntity<?> response = pedidoServiceImpl.importarPedido(pedido);

        assertEquals(responseEntity.getStatusCode(), response.getStatusCode());
        assertEquals(responseEntity.getBody(), response.getBody());
        verify(melhorEnvioClient, times(1)).inserirFrete(any(InserirFretesRequest.class));
        verify(ordersService, times(1)).saveOrders(any());
    }

    private Pedido criarPedido() {
        Pedido pedido = new Pedido();

        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setLogradouro("Rua Exemplo");
        endereco.setNumero("123");
        endereco.setBairro("Bairro Exemplo");
        endereco.setCidade("Cidade Exemplo");
        endereco.setEstado("Estado Exemplo");

        Destinatario cliente = new Destinatario();
        cliente.setId(1L);
        cliente.setNome("Nome Exemplo");
        cliente.setCpf("123.456.789-00");
        cliente.setTelefone("123456789");

        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setId(1L);
        item.setNome("Item Exemplo");
        item.setQuantidade("1");
        item.setPrecoUnitario("100.0");

        itens.add(item);

        pedido.setId(1L);
        pedido.setStatus(StatusPedido.PRONTO_PARA_ENVIO);
        pedido.setItens(itens);
        pedido.setEndereco(endereco);
        pedido.setCliente(cliente);
        pedido.setTotalPedido(BigDecimal.valueOf(100.0));

        return pedido;
    }


}