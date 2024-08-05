package com.postech.gestaodeenvio.entities.bodys.calculafrete;

import lombok.Data;

@Data
public class ProductsCotacao {

    public long id;
    private int height;
    private int width;
    private int length;
    private double weight;
    private float insurance_value;
    private int quantity;

    public ProductsCotacao() {
        this.id = 1;
        this.height = 4;
        this.width = 12;
        this.length = 17;
        this.weight = 0.3;
        this.insurance_value = 0;
        this.quantity = 1;
    }

    public ProductsCotacao(long id, int height, int width, int length, double weight, float insurance_value, int quantity) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.insurance_value = insurance_value;
        this.quantity = quantity;
    }
}
