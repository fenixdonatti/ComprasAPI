package com.estudo.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.estudo.api.dto.product.ProductCreateDTO;
import com.estudo.api.dto.product.ProductDataDTO;
import com.estudo.api.dto.product.ProductUpdateDTO;
import com.estudo.api.error.product.ProductAlreadyExistsException;
import com.estudo.api.error.product.ProductCreateException;
import com.estudo.api.error.product.ProductDeleteException;
import com.estudo.api.error.product.ProductUpdateException;
import com.estudo.api.error.product.ProductNotFoundException;
import com.estudo.api.model.Product;
import com.estudo.api.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDataDTO saveProduct(ProductCreateDTO productCreateDTO)
            throws ProductCreateException, ProductAlreadyExistsException {
                
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setPrice(productCreateDTO.getPrice());
        product.setQtyStock(productCreateDTO.getQtyStock());

        try {
            Product savedProduct = productRepository.save(product);
            return new ProductDataDTO(
                    savedProduct.getName(),
                    savedProduct.getPrice(),
                    savedProduct.getQtyStock()
            );
        } catch (DataIntegrityViolationException exception) {
            throw new ProductAlreadyExistsException("Product already exists");
        } catch (RuntimeException exception) {
            throw new ProductCreateException("Could not create product", exception);
        }
    }

    public ProductDataDTO getProductById(UUID id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductDataDTO(
                product.getName(),
                product.getPrice(),
                product.getQtyStock()
        );
    }

    public ProductUpdateDTO updateProduct(ProductUpdateDTO data)
            throws ProductNotFoundException, ProductAlreadyExistsException, ProductUpdateException {
        Optional<Product> dbProduct = productRepository.findById(data.getId());
        if (dbProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        Product product = dbProduct.get();
        product.setName(data.getName());
        product.setPrice(data.getPrice());
        product.setQtyStock(data.getQtyStock());

        try {
            productRepository.save(product);
            return data;
        } catch (DataIntegrityViolationException exception) {
            throw new ProductAlreadyExistsException("Product already exists");
        } catch (RuntimeException exception) {
            throw new ProductUpdateException("Could not update product", exception);
        }
    }

    public void deleteProduct(UUID id) throws ProductNotFoundException, ProductDeleteException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }

        try {
            productRepository.deleteById(id);
        } catch (RuntimeException exception) {
            throw new ProductDeleteException("Could not delete product", exception);
        }
    }
}