package com.postech.gestaodeenvio.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.gestaodeenvio.entities.Itens;
import com.postech.gestaodeenvio.entities.Pedido;
import com.postech.gestaodeenvio.entities.bodys.Options;
import com.postech.gestaodeenvio.entities.bodys.Volume;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.FromInserirFrete;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.InserirFretesRequest;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.ProductsInserirFretes;
import com.postech.gestaodeenvio.entities.bodys.inserirfrete.ToInserirFrete;
import com.postech.gestaodeenvio.entities.orderentities.Orders;
import com.postech.gestaodeenvio.services.OrdersService;
import com.postech.gestaodeenvio.services.PedidoService;
import com.postech.gestaodeenvio.services.integracao.MelhorEnvioClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final MelhorEnvioClient melhorEnvioClient;
    private final OrdersService ordersService;

    private final ObjectMapper objectMapper;

    public PedidoServiceImpl(MelhorEnvioClient melhorEnvioClient, OrdersService ordersService, ObjectMapper objectMapper) {
        this.melhorEnvioClient = melhorEnvioClient;
        this.ordersService = ordersService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<?> importarPedido(Pedido pedido) throws IOException, InterruptedException {
        InserirFretesRequest request = montarRequest(pedido);
        ResponseEntity<?> response = melhorEnvioClient.inserirFrete(request);
        System.out.println("Importando pedido: " + request);
        System.out.println("Retorno do melhor envio " + response.getBody());
        processJson(response.getBody().toString());
        return response;
    }

    private InserirFretesRequest montarRequest(Pedido pedido) {

        ToInserirFrete to = new ToInserirFrete(pedido.getEndereco().getCep(),
                pedido.getCliente().getNome(),
                pedido.getEndereco().getLogradouro(),
                pedido.getEndereco().getCidade(),
                pedido.getCliente().getCpf());

        FromInserirFrete from = new FromInserirFrete();

        List<ProductsInserirFretes> produtos = new ArrayList<>();
        List<Volume> volumes = new ArrayList<>();
        for (Itens item : pedido.getItens()) {
            ProductsInserirFretes prodctItem = new ProductsInserirFretes(item.getId(),
                    item.getNome(), item.getQuantidade(),item.getPrecoUnitario());
            produtos.add(prodctItem);

            //cada item vai gerar um volume
           // Volume volume = new Volume();
           // volumes.add(volume);
        }
        //Chumbado para enviar apenas um volume
        Volume volume = new Volume();
        volumes.add(volume);
        Options options = new Options();
        return new InserirFretesRequest(from, to, produtos, volumes, options);
    }

    public void processJson(String json) {
        try {
            Orders orders = objectMapper.readValue(json, Orders.class);
            ordersService.saveOrders(orders);
        } catch (Exception e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
        }
    }
}
