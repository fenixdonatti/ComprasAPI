package com.estudo.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudo.api.model.Purchase;
import com.estudo.api.model.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID>{
    List<Purchase> findByUser(User user);
}
