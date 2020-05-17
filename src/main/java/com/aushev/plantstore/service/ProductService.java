package com.aushev.plantstore.service;

import com.aushev.plantstore.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product findProduct(UUID id);

    Product findProduct(String title);

    List<Product> findAllProduct();

    void creatProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(UUID id);
}
