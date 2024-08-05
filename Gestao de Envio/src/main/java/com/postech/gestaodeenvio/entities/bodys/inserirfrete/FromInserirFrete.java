package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FromInserirFrete extends com.postech.gestaodeenvio.entities.bodys.From{
    private String name;
    private String address;//logradouro
    private String city;
    private String document;//cpf

    public FromInserirFrete() {
        super();
        this.name = "Paulo";
        this.address = "Jose Lazzari";
        this.city = "Caxias do Sul";
        this.document = "01625573022";
    }
}
