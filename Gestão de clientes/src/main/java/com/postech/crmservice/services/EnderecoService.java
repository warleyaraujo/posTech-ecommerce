package com.postech.crmservice.services;

import com.postech.crmservice.entities.DTOs.EnderecoDto;

import java.util.List;

public interface EnderecoService {
    EnderecoDto save(EnderecoDto enderecoDto);
    EnderecoDto update(Long id, EnderecoDto enderecoDto);
    void delete(Long id);
    EnderecoDto getById(Long id);
    List<EnderecoDto> getAll();
}
