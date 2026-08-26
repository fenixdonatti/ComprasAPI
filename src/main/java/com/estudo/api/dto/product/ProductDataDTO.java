package com.estudo.api.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDataDTO {

    private String name;
    private Double price;
    private Integer qtyStock;

    public ProductDataDTO(String name, Double price, Integer qtyStock) {
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    public ProductDataDTO() {
    }
}