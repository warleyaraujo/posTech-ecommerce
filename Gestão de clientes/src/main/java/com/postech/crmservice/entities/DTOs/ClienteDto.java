package com.postech.crmservice.entities.DTOs;

import java.time.LocalDate;
import java.util.List;

public record ClienteDto(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        LocalDate dataNascimento,
        Boolean ativo,
        List<EnderecoDto> enderecos
) {}