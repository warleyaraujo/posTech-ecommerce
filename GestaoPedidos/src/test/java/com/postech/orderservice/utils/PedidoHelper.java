package com.postech.orderservice.utils;

import com.postech.orderservice.entities.Cliente;
import com.postech.orderservice.entities.Endereco;
import com.postech.orderservice.entities.ItemPedido;
import com.postech.orderservice.entities.Pedido;

import java.math.BigDecimal;
import java.util.Collections;

public abstract class PedidoHelper {
    public static Pedido gerarPedido() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Endereco Teste");
        endereco.setNumero("12345");
        endereco.setUf("AA");
        endereco.setCidade("Cidade");
        endereco.setBairro("Bairro");
        endereco.setComplemento("Complemento");

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("email@email.com");
        cliente.setCpf("12312312312");
        cliente.setTelefone("123456789");
        cliente.setEndereco(endereco);

        ItemPedido item = new ItemPedido();
        item.setIdProduto(1L);
        item.setQuantidade(1);

        return Pedido.builder()
                .idPedido(1L)
                .cliente(cliente)
                .totalPedido(BigDecimal.valueOf(50))
                .itens(Collections.singletonList(item))
                .build();
    }
}
