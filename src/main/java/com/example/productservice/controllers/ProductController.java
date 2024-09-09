package com.example.productservice.controllers;

import com.example.productservice.exceptions.InvalidProductDataException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> Products = productService.getAllProducts();
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product Products = productService.getProductById((long) id);
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (
                DataIntegrityViolationException e) {
            throw new InvalidProductDataException("Wrong product data", e);
        }
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int Id, @RequestBody Product product) {
        Product edittedProduct = productService.editProduct((long)Id, product);
        return new ResponseEntity<>(edittedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int Id) {
        Product deletedProduct = productService.deleteProduct(Id);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}
