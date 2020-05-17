package com.aushev.plantstore.service;

import com.aushev.plantstore.exception.ProductAlreadyExistException;
import com.aushev.plantstore.exception.ProductNotExistException;
import com.aushev.plantstore.model.Product;
import com.aushev.plantstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotExistException(String.format("Product with id %s not exist", id)));
    }

    @Override
    public Product findProduct(String title) {
        return productRepository.findByTitle(title.trim()).orElseThrow(() ->
                new ProductNotExistException(String.format("Product with title %s not exist", title)));
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void creatProduct(Product product) {

        if (Objects.nonNull(productExist(product.getTitle()))) {
            throw new ProductAlreadyExistException(
                    String.format("Product with title %s already exist", product.getTitle()));
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {

        Product checkProduct = productExist(product.getTitle());

        if (Objects.nonNull(checkProduct) && !checkProduct.getId().equals(product.getId())) {
            throw new ProductAlreadyExistException(
                    String.format("Product with title %s already exist", product.getTitle()));
        }
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    private Product productExist(String title) {
        return productRepository.findByTitle(title).orElse(null);
    }
}
