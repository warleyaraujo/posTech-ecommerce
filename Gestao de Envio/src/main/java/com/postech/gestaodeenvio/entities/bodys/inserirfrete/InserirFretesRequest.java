package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import com.postech.gestaodeenvio.entities.bodys.*;
import com.postech.gestaodeenvio.entities.bodys.calculafrete.ProductsCotacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class InserirFretesRequest {
    private int service;
    private Integer agency;
    private From from;
    private ToInserirFrete to;
    private List<ProductsInserirFretes> products;
    private List<Volume> volumes;
    private Options options;

    public InserirFretesRequest() {
    }

    public InserirFretesRequest(From from,
                                ToInserirFrete to, List<ProductsInserirFretes> products,
                                List<Volume> volumes, Options options) {
        this.service = 2;
        this.agency = null;
        this.from = from;
        this.to = to;
        this.products = products;
        this.volumes = volumes;
        this.options = options;
    }
}
