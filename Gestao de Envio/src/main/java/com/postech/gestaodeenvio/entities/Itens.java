package com.postech.gestaodeenvio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itens {

    private Long Id;
    private String nome;
    private String quantidade;
    private String precoUnitario;

}
