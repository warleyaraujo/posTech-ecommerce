package com.postech.gestaodeenvio.entities.bodys;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class From {
    private String postal_code;

    public From() {
        this.postal_code = "95115300";
    }

    public String getPostal_code() {
        return postal_code;
    }
}
