package com.postech.crmservice.services;

import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.entities.Endereco;
import com.postech.crmservice.mappers.EnderecoMapper;
import com.postech.crmservice.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService{

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }


    @Override
    public EnderecoDto save(EnderecoDto enderecoDto) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDto);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDto(endereco);
    }

    @Override
    public EnderecoDto update(Long id, EnderecoDto enderecoDto) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            endereco.setLogradouro(enderecoDto.logradouro());
            endereco.setBairro(enderecoDto.bairro());
            endereco.setCep(enderecoDto.cep());
            endereco.setCidade(enderecoDto.cidade());
            endereco.setUf(enderecoDto.uf());
            endereco.setComplemento(enderecoDto.complemento());
            endereco = enderecoRepository.save(endereco);
            endereco.setNumero(enderecoDto.numero());
            return enderecoMapper.toDto(endereco);
        } else {
            throw new RuntimeException("Endereco nao encontrado com o id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public EnderecoDto getById(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return enderecoMapper.toDto(endereco.get());
        } else {
            throw new RuntimeException("Endereco nao encontrado com o id: " + id);
        }
    }

    @Override
    public List<EnderecoDto> getAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return Collections.singletonList(enderecoMapper.toDto((Endereco) enderecos));
    }
}
