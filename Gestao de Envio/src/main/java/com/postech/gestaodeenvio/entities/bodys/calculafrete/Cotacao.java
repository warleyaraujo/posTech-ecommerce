package com.postech.gestaodeenvio.entities.bodys.calculafrete;

import com.postech.gestaodeenvio.entities.bodys.From;
import com.postech.gestaodeenvio.entities.bodys.Options;
import com.postech.gestaodeenvio.entities.bodys.To;
import com.postech.gestaodeenvio.entities.bodys.calculafrete.ProductsCotacao;
import lombok.Data;

@Data
public class Cotacao {
    private From from;
    private To to;

    private ProductsCotacao[] products;
    private Options options;
    private String services;

    public Cotacao( To toCep) {
        this.from = new From();
        this.to = toCep;
        this.products = new ProductsCotacao[1];
        this.options = new Options();
        this.services = "2, 4";
    }
}