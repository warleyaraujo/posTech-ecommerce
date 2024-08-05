package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.Endereco;
import com.postech.crmservice.entities.DTOs.EnderecoDto;

public interface EnderecoMapper {


    EnderecoDto toDto(Endereco endereco);

    Endereco toEntity(EnderecoDto enderecoDto);
}