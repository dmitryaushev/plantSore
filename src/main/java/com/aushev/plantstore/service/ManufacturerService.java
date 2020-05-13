package com.aushev.plantstore.service;

import com.aushev.plantstore.model.Manufacturer;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {

    Manufacturer findManufacturer(UUID id);

    Manufacturer findManufacturer(String title);

    List<Manufacturer> findAllManufacturers();

    void createManufacturer(Manufacturer manufacturer);

    void updateManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(UUID id);
}
