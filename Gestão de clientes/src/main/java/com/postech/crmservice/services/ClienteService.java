package com.postech.crmservice.services;

import com.postech.crmservice.entities.DTOs.ClienteDto;

import java.util.List;

public interface ClienteService {
    ClienteDto save(ClienteDto clienteDto);
    ClienteDto update(Long id, ClienteDto clienteDto);
    boolean delete(Long id);
    ClienteDto getById(Long id);
    List<ClienteDto> getAll();
}
