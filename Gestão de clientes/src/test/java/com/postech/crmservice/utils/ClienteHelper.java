package com.postech.crmservice.utils;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.entities.DTOs.ClienteRequest;
import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.entities.Endereco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ClienteHelper {

    public static Cliente gerarRegistro() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente teste");
        cliente.setEmail("teste@email.com");
        cliente.setCpf("111.111.111-11");
        cliente.setTelefone("(11) 97777-7777");
        cliente.setAtivo(true);

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua da Silva");
        endereco.setNumero("911");
        endereco.setComplemento("Silva");
        endereco.setCidade("Silva");
        endereco.setBairro("Silva");
        endereco.setUf("SP");
        endereco.setCep("00000-000");
        endereco.setCliente(cliente);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        cliente.setEnderecos(enderecos);

        return cliente;
    }

    public static ClienteDto gerarRegistroDto() {
        EnderecoDto enderecoDto = new EnderecoDto(1L, "Rua da Silva", "911", "Silva", "Silva", "Silva", "SP", "00000-000");

        List<EnderecoDto> enderecosDto = new ArrayList<EnderecoDto>();
        enderecosDto.add(enderecoDto);

        ClienteDto clienteDto = new ClienteDto(1L, "Cliente teste", "(11) 97777-7777", "teste@email.com", "111.111.111-11", null, true, enderecosDto);

        return clienteDto;
    }


    public static ClienteRequest gerarRegistroRequest() {
       var endereco = new Endereco();
         endereco.setLogradouro("Endereco Automatizado");
         endereco.setBairro("Bairro Auto");
         endereco.setCep("00000-000");
         endereco.setCidade("Cidade Auto");
         endereco.setUf("SP");
         endereco.setComplemento("Comple");
         endereco.setNumero("1010");
         List<Endereco> enderecos = new ArrayList<Endereco>();
         enderecos.add(endereco);
        var entity = ClienteRequest.builder()
                .id(1L)
                .nome("Cliente Automatizado")
                .email("email@email.com")
                .telefone("(11) 99999-9999")
                .cpf("111.111.111-11")
                .ativo(true)
                .enderecos(enderecos)
                .build();
        return entity;

    }
}
