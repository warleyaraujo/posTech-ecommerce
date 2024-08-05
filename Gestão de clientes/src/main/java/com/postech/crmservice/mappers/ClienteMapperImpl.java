package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Override
    public ClienteDto toDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        List<EnderecoDto> enderecos = null;

        enderecos = enderecoListToEnderecoDtoList( cliente.getEnderecos() );

        Long id = cliente.getId();
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String email = cliente.getEmail();
        String cpf = cliente.getCpf();
        LocalDate dataNascimento = cliente.getDataNascimento();
        Boolean ativo = cliente.getAtivo();

        return new ClienteDto( id, nome, telefone, email, cpf, dataNascimento, ativo, enderecos );
    }

    @Override
    public Cliente toEntity(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDto.id() );
        cliente.setNome( clienteDto.nome() );
        cliente.setTelefone( clienteDto.telefone() );
        cliente.setEmail( clienteDto.email() );
        cliente.setCpf( clienteDto.cpf() );
        cliente.setDataNascimento( clienteDto.dataNascimento() );
        cliente.setAtivo( clienteDto.ativo() );
        cliente.setEnderecos( enderecoDtoListToEnderecoList( clienteDto.enderecos() ) );

        return cliente;
    }

    protected List<EnderecoDto> enderecoListToEnderecoDtoList(List<Endereco> list) {
        if ( list == null ) {
            return Collections.emptyList();
        }

        List<EnderecoDto> list1 = new ArrayList<EnderecoDto>( list.size() );
        for ( Endereco endereco : list ) {
            list1.add( enderecoMapper.toDto( endereco ) );
        }

        return list1;
    }

    protected List<Endereco> enderecoDtoListToEnderecoList(List<EnderecoDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Endereco> list1 = new ArrayList<Endereco>( list.size() );
        for ( EnderecoDto enderecoDto : list ) {
            list1.add( enderecoMapper.toEntity( enderecoDto ) );
        }

        return list1;
    }
}
