package com.estudo.api.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProductUpdateDTO {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer qtyStock;

    public ProductUpdateDTO() {
    }

    public ProductUpdateDTO(UUID id, String name, Double price, Integer qtyStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }
}