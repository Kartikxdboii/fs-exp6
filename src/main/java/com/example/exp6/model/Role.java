package com.example.exp6.model;

import jakarta.persistence.*;

// ============================================================
// PART (b): Many-to-Many Relationship  -->  Role entity
// ============================================================

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;   // e.g., "ADMIN", "USER", "MANAGER"

    // ----- Constructors -----
    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    // ----- Getters and Setters -----
    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getRoleName()                { return roleName; }
    public void setRoleName(String roleName)   { this.roleName = roleName; }

    @Override
    public String toString() {
        return "Role{id=" + id + ", roleName='" + roleName + "'}";
    }
}
