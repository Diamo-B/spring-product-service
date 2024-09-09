package com.example.productservice.services;

import com.example.productservice.exceptions.InvalidProductDataException;
import com.example.productservice.exceptions.NoProductsException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        List<Product> prods = productDao.findAll();
        if (prods.isEmpty()) {
            throw new NoProductsException("No products were found");
        }
        return prods;
    }

    public Product getProductById(Long id) {
        Optional<Product> p = productDao.findById(id);
        if (p.isPresent())
            return p.get();
        else
            throw new NoProductsException("No Product found with id: " + id);
    }

    public Product addProduct(Product product) {
        return productDao.save(product);
    }

    public Product editProduct(Long Id, Product product) {
        Optional<Product> p = productDao.findById(Id);
        return p.get();
        /*if (p.isPresent()) {
            try {
                product.setId(Id);
                return productDao.save(product);
            } catch (DataIntegrityViolationException e) {
                throw new InvalidProductDataException("Wrong product data", e);
            }
        } else {
            throw new NoProductsException("No Product found with id: " + Id);
        }*/
    }

    public Product deleteProduct(int id) {
        Optional<Product> product = productDao.findById((long)id);
        if (product.isPresent()) {
            productDao.delete(product.get());
            return product.get();
        }
        throw new NoProductsException("No Product found with id: " + id);
    }
}
