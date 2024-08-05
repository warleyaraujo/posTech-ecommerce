package com.postech.crmservice.services;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.entities.Endereco;
import com.postech.crmservice.mappers.ClienteMapper;
import com.postech.crmservice.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDto save(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        for (Endereco endereco : cliente.getEnderecos()) {
            endereco.setCliente(cliente);
        }
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto update(Long id, ClienteDto clienteDto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteDto.nome());
            cliente.setTelefone(clienteDto.telefone());
            cliente.setEmail(clienteDto.email());
            cliente.setCpf(clienteDto.cpf());
            cliente.setDataNascimento(clienteDto.dataNascimento());
            cliente.setAtivo(clienteDto.ativo());
            cliente = clienteRepository.save(cliente);
            return clienteMapper.toDto(cliente);
        } else {
            throw new RuntimeException("Cliente nao encontrado com o id: " + id);
        }
    }

    @Override
    public boolean delete(Long id) {
        clienteRepository.deleteById(id);
        return true;
    }

    @Override
    public ClienteDto getById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return clienteMapper.toDto(cliente.get());
        } else {
            throw new RuntimeException("Cliente nao encontrado com o id: " + id);
        }
    }

    @Override
    public List<ClienteDto> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }
}
