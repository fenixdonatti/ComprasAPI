package com.estudo.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudo.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}