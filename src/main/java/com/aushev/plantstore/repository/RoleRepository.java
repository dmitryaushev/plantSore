package com.aushev.plantstore.repository;

import com.aushev.plantstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByTitle(String role);

    @Query("select count(u) from User u where u.role.title = ?1")
    int findCountByRole(String role);
}
