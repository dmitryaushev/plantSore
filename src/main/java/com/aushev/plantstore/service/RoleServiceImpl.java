package com.aushev.plantstore.service;

import com.aushev.plantstore.model.Role;
import com.aushev.plantstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRole(String role) {
        return roleRepository.findByRole(role);
    }
}
