package com.example.exp6.model;

import jakarta.persistence.*;
import java.util.*;

// ============================================================
// PART (b): One-to-Many Relationship  -->  Category has many Products
// ============================================================

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;   // e.g., "Electronics", "Books"

    // ---------- One-to-Many: One Category -> Many Products ----------
    // "mappedBy" means the Product entity owns this relationship (has the FK)
    // CascadeType.ALL = if we save/delete category, products are also saved/deleted
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    // ----- Constructors -----
    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // ----- Getters and Setters -----
    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getCategoryName()                    { return categoryName; }
    public void setCategoryName(String categoryName)   { this.categoryName = categoryName; }

    public List<Product> getProducts()                   { return products; }
    public void setProducts(List<Product> products)      { this.products = products; }

    // Helper method to add a product to this category
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);   // set the back-reference
    }

    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + categoryName + "'}";
    }
}
