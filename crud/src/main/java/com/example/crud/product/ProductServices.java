package com.example.crud.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public void newProduct(@Valid @NotNull Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto con el nombre.");
        }
        productRepository.save(product);
    }

    public void borrarProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new IllegalStateException("El producto no pudo ser borrado porque no existe.");
        }

        productRepository.deleteById(product.getId());
    }

    public Product editarProduct(@Valid @NotNull Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(product.getId());

        if (existingProductOptional.isEmpty()) {
            throw new IllegalArgumentException("No se puede editar. El producto con ID " + product.getId() + " no existe.");
        }

        Product existingProduct = existingProductOptional.get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }
}
