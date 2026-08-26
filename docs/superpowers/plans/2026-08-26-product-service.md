# Product Service Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Create a ProductService class following the pattern of UserService, including DTOs and exception classes.

**Architecture:** 
- Create DTO classes (ProductCreateDTO, ProductDataDTO, ProductUpdateDTO) in dto/product package.
- Create exception classes (ProductAlreadyExistsException, ProductCreateException, ProductDeleteException, ProductUpdateException, ProductNotFoundException) in error/product package.
- Implement ProductService in service package using ProductRepository, following the same CRUD pattern as UserService.
- Service will handle UUID-based operations since Product lacks unique business fields like email.

**Tech Stack:** Java, Spring Boot, JPA, Lombok

**Spec:** Based on existing UserService implementation and Product model.

## Global Constraints
- Follow existing code style and patterns in com.estudo.api package.
- Use UUID for product identification.
- Maintain consistent exception handling with UserService.
- Use Lombok getters/setters in DTOs if needed, but follow existing DTO patterns.

---
### Task 1: Create Product DTOs

**Files:**
- Create: `src/main/java/com/estudo/api/dto/product/ProductCreateDTO.java`
- Create: `src/main/java/com/estudo/api/dto/product/ProductDataDTO.java`
- Create: `src/main/java/com/estudo/api/dto/product/ProductUpdateDTO.java`

**Interfaces:**
- Consumes: None
- Produces: DTO classes for Product service layer

- [ ] **Step 1: Write ProductCreateDTO**

```java
package com.estudo.api.dto.product;

public class ProductCreateDTO {
    private String name;
    private Double price;
    private Integer qtyStock;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getQtyStock() { return qtyStock; }
    public void setQtyStock(Integer qtyStock) { this.qtyStock = qtyStock; }
}
```

- [ ] **Step 2: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/dto/product/ProductCreateDTO.java`
Expected: Compilation success

- [ ] **Step 3: Write ProductDataDTO**

```java
package com.estudo.api.dto.product;

public class ProductDataDTO {
    private String name;
    private Double price;
    private Integer qtyStock;

    // Constructor for entity mapping
    public ProductDataDTO(String name, Double price, Integer qtyStock) {
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    // Getters
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public Integer getQtyStock() { return qtyStock; }
}
```

- [ ] **Step 4: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/dto/product/ProductDataDTO.java`
Expected: Compilation success

- [ ] **Step 5: Write ProductUpdateDTO**

```java
package com.estudo.api.dto.product;

import java.util.UUID;

public class ProductUpdateDTO {
    private UUID id;
    private String name;
    private Double price;
    private Integer qtyStock;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getQtyStock() { return qtyStock; }
    public void setQtyStock(Integer qtyStock) { this.qtyStock = qtyStock; }
}
```

- [ ] **Step 6: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/dto/product/ProductUpdateDTO.java`
Expected: Compilation success

- [ ] **Step 7: Commit DTOs**

```bash
git add src/main/java/com/estudo/api/dto/product/
git commit -m "feat: create Product DTOs"
```

### Task 2: Create Product Exception Classes

**Files:**
- Create: `src/main/java/com/estudo/api/error/product/ProductAlreadyExistsException.java`
- Create: `src/main/java/com/estudo/api/error/product/ProductCreateException.java`
- Create: `src/main/java/com/estudo/api/error/product/ProductDeleteException.java`
- Create: `src/main/java/com/estudo/api/error/product/ProductUpdateException.java`
- Create: `src/main/java/com/estudo/api/error/product/ProductNotFoundException.java`

**Interfaces:**
- Consumes: None
- Produces: Exception classes for Product service layer

- [ ] **Step 1: Write ProductAlreadyExistsException**

```java
package com.estudo.api.error.product;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
```

- [ ] **Step 2: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/error/product/ProductAlreadyExistsException.java`
Expected: Compilation success

- [ ] **Step 3: Write ProductCreateException**

```java
package com.estudo.api.error.product;

public class ProductCreateException extends Exception {
    public ProductCreateException(String message) {
        super(message);
    }

    public ProductCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

- [ ] **Step 4: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/error/product/ProductCreateException.java`
Expected: Compilation success

- [ ] **Step 5: Write ProductDeleteException**

```java
package com.estudo.api.error.product;

public class ProductDeleteException extends Exception {
    public ProductDeleteException(String message) {
        super(message);
    }

    public ProductDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

- [ ] **Step 6: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/error/product/ProductDeleteException.java`
Expected: Compilation success

- [ ] **Step 7: Write ProductUpdateException**

```java
package com.estudo.api.error.product;

public class ProductUpdateException extends Exception {
    public ProductUpdateException(String message) {
        super(message);
    }

    public ProductUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

- [ ] **Step 8: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/error/product/ProductUpdateException.java`
Expected: Compilation success

- [ ] **Step 9: Write ProductNotFoundException**

```java
package com.estudo.api.error.product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
```

- [ ] **Step 10: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/error/product/ProductNotFoundException.java`
Expected: Compilation success

- [ ] **Step 11: Commit exceptions**

```bash
git add src/main/java/com/estudo/api/error/product/
git commit -m "feat: create Product exception classes"
```

### Task 3: Create ProductService

**Files:**
- Create: `src/main/java/com/estudo/api/service/ProductService.java`

**Interfaces:**
- Consumes: Product DTOs, Product exceptions, ProductRepository
- Produces: Product service layer

- [ ] **Step 1: Write ProductService**

```java
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
            throws ProductCreateException {
        // Note: Product doesn't have unique business field like email, so no duplicate check
        // If name should be unique, uncomment and add repository method
        // if (productRepository.findByName(productCreateDTO.getName()) != null) {
        //     throw new ProductAlreadyExistsException("Product name already exists");
        // }

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
```

- [ ] **Step 2: Run test to verify it compiles**

Run: `javac src/main/java/com/estudo/api/service/ProductService.java`
Expected: Compilation success

- [ ] **Step 3: Commit ProductService**

```bash
git add src/main/java/com/estudo/api/service/ProductService.java
git commit -m "feat: create ProductService"
```