package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsInserirFrete extends com.postech.gestaodeenvio.entities.bodys.Options{
    private double insurance_value;
    private boolean reverse;
    private boolean non_commercial;

    public OptionsInserirFrete() {
        super();
        this.insurance_value = 10;
        this.reverse = false;
        this.non_commercial = true;
    }
}
