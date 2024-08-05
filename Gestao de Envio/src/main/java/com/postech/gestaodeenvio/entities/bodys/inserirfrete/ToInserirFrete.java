package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ToInserirFrete extends com.postech.gestaodeenvio.entities.bodys.To {
    private String name;
    private String address;//logradouro
    private String city;
    private String document;//cpf

    public ToInserirFrete(String postal_code, String name, String address, String city, String document) {
        super(postal_code);
        this.name = name;
        this.address = address;
        this.city = city;
        this.document = document;
    }
}
