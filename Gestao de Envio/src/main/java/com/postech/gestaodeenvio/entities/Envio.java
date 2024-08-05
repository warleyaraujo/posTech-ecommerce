package com.postech.gestaodeenvio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_envio")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long pedidoId;

    private String transportadora;

    private String servico;

    private String codigoRastreamento;

    private String status;

    private String etiquetaUrl;
}