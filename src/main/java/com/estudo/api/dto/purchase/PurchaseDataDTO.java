package com.estudo.api.dto.purchase;

import java.util.List;

import com.estudo.api.dto.product.ProductDataDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.model.Product;
import com.estudo.api.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDataDTO {
    
    private UserDataDTO user;
    private List<ProductDataDTO> products;
    private Double total;
}
