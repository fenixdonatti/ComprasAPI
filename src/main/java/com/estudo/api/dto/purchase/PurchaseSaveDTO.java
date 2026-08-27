package com.estudo.api.dto.purchase;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseSaveDTO {
    
    private UUID user;
    private List<UUID> products;
    private Double tota;
}
