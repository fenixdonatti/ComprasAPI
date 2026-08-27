package com.estudo.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudo.api.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID>{
    
}
