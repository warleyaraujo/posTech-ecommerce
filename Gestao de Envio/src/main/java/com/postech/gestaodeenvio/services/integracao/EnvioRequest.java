package com.postech.gestaodeenvio.services.integracao;

import com.postech.gestaodeenvio.entities.Destinatario;
import com.postech.gestaodeenvio.entities.Endereco;
import com.postech.gestaodeenvio.entities.Itens;
import com.postech.gestaodeenvio.entities.StatusPedido;

import java.util.List;

public class EnvioRequest {
    private long id;

    private StatusPedido status;

    private List<Itens> itens;

    private Endereco endereco;

    private Destinatario cliente;

    public long getId() {
        return id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<Itens> getItens() {
        return itens;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Destinatario getCliente() {
        return cliente;
    }
}