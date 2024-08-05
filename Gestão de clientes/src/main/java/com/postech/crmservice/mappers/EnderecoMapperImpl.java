package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.entities.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public EnderecoDto toDto(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        Long id = endereco.getId();
        String logradouro = endereco.getLogradouro();
        String bairro = endereco.getBairro();
        String cep = endereco.getCep();
        String cidade = endereco.getCidade();
        String uf = endereco.getUf();
        String complemento = endereco.getComplemento();
        String numero = endereco.getNumero();


        return new EnderecoDto( id, logradouro, bairro, cep, cidade, uf, complemento, numero );
    }

    @Override
    public Endereco toEntity(EnderecoDto enderecoDto) {
        if ( enderecoDto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setId( enderecoDto.id() );
        endereco.setLogradouro( enderecoDto.logradouro() );
        endereco.setBairro( enderecoDto.bairro() );
        endereco.setCep( enderecoDto.cep() );
        endereco.setCidade( enderecoDto.cidade() );
        endereco.setUf( enderecoDto.uf() );
        endereco.setComplemento( enderecoDto.complemento() );
        endereco.setNumero( enderecoDto.numero() );

        return endereco;
    }
}
