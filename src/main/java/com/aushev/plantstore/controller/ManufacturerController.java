package com.aushev.plantstore.controller;

import com.aushev.plantstore.exception.ManufacturerAlreadyExistException;
import com.aushev.plantstore.exception.ManufacturerNotExistException;
import com.aushev.plantstore.model.Manufacturer;
import com.aushev.plantstore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/showManufacturers")
    public String getAllManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        return "manufacturer/show_manufacturers";
    }

    @GetMapping("/get")
    public String getManufacturer(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("manufacturer", manufacturerService.findManufacturer(id));
        return "manufacturer/manufacturer_details";
    }

    @GetMapping("/findManufacturer")
    public String showFindManufacturerPage() {
        return "manufacturer/find_manufacturer";
    }

    @GetMapping("/find")
    public String findManufacturer(@RequestParam("title") String title, Model model) {
        try {
            model.addAttribute("manufacturer", manufacturerService.findManufacturer(title));
            return "manufacturer/manufacturer_details";
        } catch (ManufacturerNotExistException e) {
            model.addAttribute("error", e.getMessage());
            return "manufacturer/find_manufacturer";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createManufacturer")
    public String showCreateManufacturerPage() {
        return "manufacturer/create_manufacturer";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createManufacturer(@Valid Manufacturer manufacturer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "manufacturer/create_manufacturer";
        }
        try {
            manufacturerService.createManufacturer(manufacturer);
            return "manufacturer/manufacturer_details";
        } catch (ManufacturerAlreadyExistException e) {
            model.addAttribute("message", e.getMessage());
            return "manufacturer/create_manufacturer";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public String shoeEditManufacturerPage(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("manufacturer", manufacturerService.findManufacturer(id));
        return "manufacturer/edit_manufacturer";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/editManufacturer")
    public String editManufacturer(@Valid Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manufacturer/edit_manufacturer";
        }
        try {
            manufacturerService.updateManufacturer(manufacturer);
            return "manufacturer/manufacturer_details";
        } catch (ManufacturerAlreadyExistException e) {
            model.addAttribute("message", e.getMessage());
            return "manufacturer/edit_manufacturer";
        }
    }

    @PreAuthorize(("hasRole('ROLE_ADMIN')"))
    @GetMapping("/delete")
    public String deleteManufacturer(@RequestParam("id") UUID id) {
        manufacturerService.deleteManufacturer(id);
        return "redirect:/manufacturer/showManufacturers";
    }

    @ModelAttribute("manufacturer")
    public Manufacturer getDefaultManufacturer() {
        return new Manufacturer();
    }
}
