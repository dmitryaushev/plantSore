package com.aushev.plantstore.config;

import com.aushev.plantstore.repository.ManufacturerRepository;
import com.aushev.plantstore.repository.ProductRepository;
import com.aushev.plantstore.repository.UserRepository;
import com.aushev.plantstore.service.ManufacturerService;
import com.aushev.plantstore.service.ProductService;
import com.aushev.plantstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EntityExistValidator implements ConstraintValidator<EntityExist, String> {

    private UserService userService;
    private ProductService productService;
    private ManufacturerService manufacturerService;
    private String entity;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    public void initialize(EntityExist constraint) {
        entity = constraint.entity();
    }

    public boolean isValid(String title, ConstraintValidatorContext context) {
        switch (entity) {
            case "product":
                return productService.productExist(title);
            case "manufacturer":
                return manufacturerService.manufacturerExist(title);
            case "user":
                return userService.userExist(title);
            default:
                return true;
        }
    }

}
