package com.postech.gestaodeenvio.entities.bodys;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Volume {
    private String description;

    private int height;
    private int width;
    private int length;
    private double weight;

   public Volume() {
        this.height = 4;
        this.width = 12;
        this.length = 17;
        this.weight = 0.3;
    }
}