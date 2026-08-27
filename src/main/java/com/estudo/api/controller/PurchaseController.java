package com.estudo.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import com.estudo.api.dto.purchase.PurchaseDataDTO;
import com.estudo.api.dto.purchase.PurchaseSaveDTO;
import com.estudo.api.error.product.ProductNotFoundException;
import com.estudo.api.error.user.UserNotFoundException;
import com.estudo.api.service.PurchaseService;

import jakarta.validation.Valid;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchases")
    public ResponseEntity<PurchaseSaveDTO> create(@Valid @RequestBody PurchaseSaveDTO purchaseSaveDTO) {
        try {
            PurchaseSaveDTO createdPurchase = purchaseService.savePurchase(purchaseSaveDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchase);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/purchases/user/{userId}")
    public ResponseEntity<List<PurchaseDataDTO>> getByUser(@PathVariable UUID userId) {
        try {
            List<PurchaseDataDTO> purchases = purchaseService.getPurchaseByUser(userId);
            return ResponseEntity.ok(purchases);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}