package com.postech.gestaodeenvio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Long id;

    private StatusPedido status;

    private List<Itens> itens;

    private Endereco endereco;

    private Destinatario cliente;

    private BigDecimal totalPedido;


}
