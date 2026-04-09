package com.example.exp6.repository;

import com.example.exp6.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// ============================================================
// PART (a): Repository  +  PART (c): Custom JPQL Queries
// ============================================================

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ------- PART (c): JPQL - Filter by price range -------
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice);

    // ------- PART (c): JPQL - Sort by price ascending -------
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();

    // ------- PART (c): JPQL - Filter by category name -------
    @Query("SELECT p FROM Product p WHERE p.category.categoryName = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);

    // ------- PART (c): Pagination support -------
    // Pageable parameter enables pagination automatically
    @Query("SELECT p FROM Product p")
    Page<Product> findAllWithPagination(Pageable pageable);
}
