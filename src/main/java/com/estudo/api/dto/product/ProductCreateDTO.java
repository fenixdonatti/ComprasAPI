package com.estudo.api.dto.product;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProductCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer qtyStock;

    public ProductCreateDTO() {
    }

    public ProductCreateDTO(String name, Double price, Integer qtyStock) {
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }
}