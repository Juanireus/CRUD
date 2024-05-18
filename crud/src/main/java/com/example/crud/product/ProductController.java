package com.example.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/products")
public class ProductController {

   private final  ProductServices productServices;

    @Autowired
    public ProductController (ProductServices productServices) {
        this.productServices = productServices;

    }
    @GetMapping
    public List<Product> getProducts() {
        return  this.productServices.getProducts();
    }
    @PostMapping
    public void registrarProducto(@RequestBody Product product) {

        this.productServices.newProduct(product);
    }
    @PutMapping
    public void editarProduct(@RequestBody Product product) {

        this.productServices.newProduct(product);
    }
    @DeleteMapping
    public void eliminarProducto(@RequestBody Product product) {
        this.productServices.borrarProduct(product);
    }

}
