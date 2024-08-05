package com.postech.crmservice.entities.DTOs;

import com.postech.crmservice.entities.Endereco;

import java.time.LocalDate;
import java.util.List;

public class ClienteRequest {
  private Long id;
  private String nome;
  private String telefone;
  private String email;
  private String cpf;
  private LocalDate dataNascimento;
  private Boolean ativo;
  private List<Endereco> enderecos;

  // Getter methods
  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getEmail() {
    return email;
  }

  public String getCpf() {
    return cpf;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private List<Endereco> enderecos;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder telefone(String telefone) {
      this.telefone = telefone;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder cpf(String cpf) {
      this.cpf = cpf;
      return this;
    }

    public Builder dataNascimento(LocalDate dataNascimento) {
      this.dataNascimento = dataNascimento;
      return this;
    }

    public Builder ativo(Boolean ativo) {
      this.ativo = ativo;
      return this;
    }

    public Builder enderecos(List<Endereco> enderecos) {
      this.enderecos = enderecos;
      return this;
    }

    public ClienteRequest build() {
      ClienteRequest clienteRequest = new ClienteRequest();
      clienteRequest.id = this.id;
      clienteRequest.nome = this.nome;
      clienteRequest.telefone = this.telefone;
      clienteRequest.email = this.email;
      clienteRequest.cpf = this.cpf;
      clienteRequest.dataNascimento = this.dataNascimento;
      clienteRequest.ativo = this.ativo;
      clienteRequest.enderecos = this.enderecos;
      return clienteRequest;
    }
  }
}