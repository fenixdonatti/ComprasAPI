package com.estudo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.api.dto.product.ProductDataDTO;
import com.estudo.api.dto.purchase.PurchaseDataDTO;
import com.estudo.api.dto.purchase.PurchaseSaveDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.error.product.ProductNotFoundException;
import com.estudo.api.error.user.UserNotFoundException;
import com.estudo.api.model.Product;
import com.estudo.api.model.Purchase;
import com.estudo.api.model.User;
import com.estudo.api.repository.ProductRepository;
import com.estudo.api.repository.PurchaseRepository;
import com.estudo.api.repository.UserRepository;

@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public PurchaseSaveDTO savePurchase(PurchaseSaveDTO purchase) 
        throws UserNotFoundException, ProductNotFoundException 
    {
        List<Product> products = new ArrayList<>();

        for (UUID product : purchase.getProducts()) {
            Product productEntity = productRepository.getById(product);
            products.add(productEntity);

        }

        Purchase newPurchase = new Purchase();
        newPurchase.setId(UUID.randomUUID());
        newPurchase.setUser(userRepository.findById(purchase.getUser()).get());
        newPurchase.setProducts(products);
        purchaseRepository.save(newPurchase);

        return purchase;
    }

    public List<PurchaseDataDTO> getPurchaseByUser(UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Purchase> purchases = purchaseRepository.findByUser(user);
        List<PurchaseDataDTO> purchaseDTOs = new ArrayList<>();

        for (Purchase purchase : purchases) {
            PurchaseDataDTO dto = new PurchaseDataDTO();
            dto.setUser(new UserDataDTO(user.getName(), user.getEmail()));
            dto.setProducts(purchase.getProducts().stream()
                .map(product -> new ProductDataDTO(product.getName(), product.getPrice(), product.getQtyStock()))
                .collect(Collectors.toList()));
            dto.setTotal(purchase.getTotal());
            purchaseDTOs.add(dto);
        }

        return purchaseDTOs;
    }
}
