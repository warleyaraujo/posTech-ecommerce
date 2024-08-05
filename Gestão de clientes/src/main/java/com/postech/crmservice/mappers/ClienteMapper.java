package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;

public interface ClienteMapper {

    ClienteDto toDto(Cliente cliente);

    Cliente toEntity(ClienteDto clienteDto);
}