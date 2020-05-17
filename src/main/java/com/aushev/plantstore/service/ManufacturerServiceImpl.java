package com.aushev.plantstore.service;

import com.aushev.plantstore.exception.ManufacturerNotExistException;
import com.aushev.plantstore.model.Manufacturer;
import com.aushev.plantstore.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer findManufacturer(UUID id) {
        return manufacturerRepository.findById(id).orElseThrow(() ->
                new ManufacturerNotExistException(String.format("Manufacturer with id %s not exist", id)));
    }

    @Override
    public Manufacturer findManufacturer(String title) {
        return manufacturerRepository.findByTitle(title).orElseThrow(() ->
                new ManufacturerNotExistException(String.format("Manufacturer with title %s not exist", title)));
    }

    @Override
    public List<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteManufacturer(UUID id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public Manufacturer manufacturerExist(String title) {
        return manufacturerRepository.findByTitle(title).orElse(null);
    }
}
