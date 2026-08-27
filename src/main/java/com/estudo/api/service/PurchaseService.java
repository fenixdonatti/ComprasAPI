package com.estudo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.api.dto.purchase.PurchaseSaveDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.repository.PurchaseRepository;
import com.estudo.api.repository.UserRepository;

@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public PurchaseSaveDTO savePurchase(PurchaseSaveDTO purchase) {
    }
}
