package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsInserirFretes {
    private Long id;
    private String name;
    private String quantity;
    private String unitary_value;

}
