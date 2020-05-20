package com.aushev.plantstore.config;

import com.aushev.plantstore.model.Manufacturer;
import com.aushev.plantstore.model.Product;
import com.aushev.plantstore.model.User;
import com.aushev.plantstore.service.ManufacturerService;
import com.aushev.plantstore.service.ProductService;
import com.aushev.plantstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.UUID;

public class EntityExistValidator implements ConstraintValidator<EntityExist, Object> {

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

    public boolean isValid(Object object, ConstraintValidatorContext context) {

        switch (entity) {
            case "user": {
                User user = (User) object;
                UUID id = user.getId();
                User existUser = userService.userExist(user.getEmail());
                if (Objects.nonNull(id) && Objects.nonNull(existUser) && existUser.getId().equals(id)) {
                    return true;
                }
                if (Objects.nonNull(existUser)) {
                    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                            .addPropertyNode("email").addConstraintViolation();
                    return false;
                }
                return true;
            }
            case "product": {
                Product product = (Product) object;
                UUID id = product.getId();
                Product productExist = productService.productExist(product.getTitle());
                if (Objects.nonNull(id) && Objects.nonNull(productExist) && productExist.getId().equals(id)) {
                    return true;
                }
                if (Objects.nonNull(productExist)) {
                    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                            .addPropertyNode("title").addConstraintViolation();
                    return false;
                }
                return true;
            }
            case "manufacturer": {
                Manufacturer manufacturer = (Manufacturer) object;
                UUID id = manufacturer.getId();
                Manufacturer manufacturerExist = manufacturerService.manufacturerExist(manufacturer.getTitle());
                if (Objects.nonNull(id) && Objects.nonNull(manufacturerExist) && manufacturerExist.getId().equals(id)) {
                    return true;
                }
                if (Objects.nonNull(manufacturerExist)) {
                    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                            .addPropertyNode("title").addConstraintViolation();
                    return false;
                }
                return true;
            }
            default:
                return true;
        }
    }

}
