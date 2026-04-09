package com.example.exp6.repository;

import com.example.exp6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// ============================================================
// PART (a): Repository  +  PART (c): Custom JPQL Queries
// ============================================================

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ------- PART (c): Custom JPQL Query -------
    // Find users who have a specific role name
    // This is a JPQL query (works on entity names, NOT table names)
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = :roleName")
    List<User> findUsersByRoleName(@Param("roleName") String roleName);

    // Find users by name (case-insensitive search using LIKE)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByName(@Param("keyword") String keyword);
}
