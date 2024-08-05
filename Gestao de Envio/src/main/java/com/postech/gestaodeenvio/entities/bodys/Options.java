package com.postech.gestaodeenvio.entities.bodys;

import lombok.Data;

@Data
public class Options {
    private boolean receipt;
    private boolean own_hand;

    public Options() {
        this.receipt = false;
        this.own_hand = false;
    }

}