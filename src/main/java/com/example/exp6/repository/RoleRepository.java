package com.example.exp6.repository;

import com.example.exp6.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ============================================================
// PART (a): Simple JPA Repository for Role
// ============================================================
// JpaRepository<Role, Long> gives us CRUD methods automatically:
//   save(), findById(), findAll(), deleteById(), etc.

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Spring Data JPA creates the query automatically from method name!
    Role findByRoleName(String roleName);
}
