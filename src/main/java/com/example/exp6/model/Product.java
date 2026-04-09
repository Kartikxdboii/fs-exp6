package com.example.exp6.model;

import jakarta.persistence.*;

// ============================================================
// PART (a): Simple JPA Entity  +  PART (b): Many-to-One side
// ============================================================

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Double price;

    // ---------- Many-to-One: Many Products -> One Category ----------
    // This is the owning side (has the foreign key column "category_id")
    @ManyToOne
    @JoinColumn(name = "category_id")   // FK column in products table
    private Category category;

    // ----- Constructors -----
    public Product() {}

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    // ----- Getters and Setters -----
    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getProductName()                     { return productName; }
    public void setProductName(String productName)     { this.productName = productName; }

    public Double getPrice()             { return price; }
    public void setPrice(Double price)   { this.price = price; }

    public Category getCategory()                  { return category; }
    public void setCategory(Category category)     { this.category = category; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + productName
               + "', price=" + price + "}";
    }
}
