package com.aushev.plantstore.controller;

import com.aushev.plantstore.exception.ProductNotExistException;
import com.aushev.plantstore.model.Product;
import com.aushev.plantstore.service.ManufacturerService;
import com.aushev.plantstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/plant")
public class ProductController {

    private ProductService productService;
    private ManufacturerService manufacturerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/showPlants")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAllProduct());
        return "product/show_products";
    }

    @GetMapping("/get")
    public String getProduct(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("product", productService.findProduct(id));
        return "product/product_details";
    }

    @GetMapping("/findPlant")
    public String showFindProductPage() {
        return "product/find_product";
    }

    @GetMapping("/find")
    public String findProduct(@RequestParam("title") String title, Model model) {
        try {
            model.addAttribute("product", productService.findProduct(title.trim()));
            return "product/product_details";
        } catch (ProductNotExistException e) {
            model.addAttribute("error", e.getMessage());
            return "product/find_product";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createPlant")
    public String showCreateProductPage(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        return "product/create_product";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
            return "product/create_product";
        }
        productService.creatProduct(product);
        return "product/product_details";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public String showEditProductPage(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("product", productService.findProduct(id));
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        return "product/edit_product";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
            return "product/edit_product";
        }
        productService.updateProduct(product);
        return "product/product_details";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") UUID id) {
        productService.deleteProduct(id);
        return "redirect:/plant/showPlants";
    }

    @ModelAttribute("product")
    public Product getDefaultProduct() {
        return new Product();
    }
}
