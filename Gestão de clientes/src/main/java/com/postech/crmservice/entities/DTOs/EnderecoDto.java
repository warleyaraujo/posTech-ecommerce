package com.postech.crmservice.entities.DTOs;

public record EnderecoDto(
        Long id,
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String complemento,
        String numero
) {}