package com.postech.gestaodeenvio.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Embeddable
@Data
@AllArgsConstructor
public class Destinatario {

    private long id;

    private String nome;

    private String cpf;

    private String telefone;

    public Destinatario() {

    }
}
