package com.aushev.plantstore.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "role")
@Component
public class Role {

    private UUID id;
    private String role;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
