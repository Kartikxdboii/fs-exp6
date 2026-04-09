package com.example.exp6.model;

import jakarta.persistence.*;
import java.util.*;

// ============================================================
// PART (b): Many-to-Many Relationship  -->  User has many Roles
// ============================================================

@Entity                         // Marks this class as a database table
@Table(name = "users")          // Table name in MySQL
public class User {

    @Id                                              // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    private String name;

    private String email;

    // ---------- Many-to-Many: User <-> Role ----------
    // One user can have MANY roles, and one role can belong to MANY users
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",                                    // join table name
        joinColumns = @JoinColumn(name = "user_id"),            // FK to users
        inverseJoinColumns = @JoinColumn(name = "role_id")      // FK to roles
    )
    private Set<Role> roles = new HashSet<>();

    // ----- Constructors -----
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // ----- Getters and Setters -----
    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getName()          { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail()             { return email; }
    public void setEmail(String email)   { this.email = email; }

    public Set<Role> getRoles()              { return roles; }
    public void setRoles(Set<Role> roles)    { this.roles = roles; }

    // Helper method to add a role
    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
