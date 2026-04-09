package com.example.exp6.runner;

import com.example.exp6.model.*;
import com.example.exp6.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

// ============================================================
// This class runs automatically when the application starts.
// It inserts sample data and demonstrates all queries.
// ============================================================

@Component
public class DataLoader implements CommandLineRunner {

    // Inject all repositories
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataLoader(RoleRepository roleRepository,
                      UserRepository userRepository,
                      CategoryRepository categoryRepository,
                      ProductRepository productRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n");
        System.out.println("==========================================================");
        System.out.println("   EXPERIMENT 6: JPA & Hibernate with MySQL");
        System.out.println("==========================================================");

        // ====================================================
        // PART (a): Create and Save simple entities
        // ====================================================
        System.out.println("\n--- PART (a): Creating and Saving Entities ---\n");

        // Create Roles
        Role adminRole = new Role("ADMIN");
        Role userRole  = new Role("USER");
        Role managerRole = new Role("MANAGER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        roleRepository.save(managerRole);
        System.out.println("Saved Roles: ADMIN, USER, MANAGER");

        // Create Users
        User alice = new User("Alice", "alice@example.com");
        User bob   = new User("Bob", "bob@example.com");
        User charlie = new User("Charlie", "charlie@example.com");

        // ====================================================
        // PART (b): Many-to-Many  -->  Assign Roles to Users
        // ====================================================
        System.out.println("\n--- PART (b): Many-to-Many (User <-> Role) ---\n");

        alice.addRole(adminRole);
        alice.addRole(userRole);      // Alice has 2 roles: ADMIN + USER

        bob.addRole(userRole);        // Bob has 1 role: USER

        charlie.addRole(managerRole);
        charlie.addRole(userRole);    // Charlie has 2 roles: MANAGER + USER

        userRepository.save(alice);
        userRepository.save(bob);
        userRepository.save(charlie);

        System.out.println("Alice  -> Roles: " + alice.getRoles());
        System.out.println("Bob    -> Roles: " + bob.getRoles());
        System.out.println("Charlie-> Roles: " + charlie.getRoles());

        // ====================================================
        // PART (b): One-to-Many  -->  Category has many Products
        // ====================================================
        System.out.println("\n--- PART (b): One-to-Many (Category -> Product) ---\n");

        // Create Categories
        Category electronics = new Category("Electronics");
        Category books = new Category("Books");
        Category clothing = new Category("Clothing");

        // Create Products and add them to categories
        electronics.addProduct(new Product("Laptop", 75000.0));
        electronics.addProduct(new Product("Smartphone", 25000.0));
        electronics.addProduct(new Product("Headphones", 2000.0));

        books.addProduct(new Product("Java Programming", 500.0));
        books.addProduct(new Product("Spring Boot Guide", 800.0));

        clothing.addProduct(new Product("T-Shirt", 400.0));
        clothing.addProduct(new Product("Jeans", 1500.0));

        // Save categories (products are saved automatically due to CascadeType.ALL)
        categoryRepository.save(electronics);
        categoryRepository.save(books);
        categoryRepository.save(clothing);

        System.out.println("Category: Electronics -> Products: " + electronics.getProducts());
        System.out.println("Category: Books       -> Products: " + books.getProducts());
        System.out.println("Category: Clothing    -> Products: " + clothing.getProducts());

        // ====================================================
        // Fetching related data
        // ====================================================
        System.out.println("\n--- Fetching Related Data ---\n");

        // Fetch a category and its products
        Category fetchedCategory = categoryRepository.findByCategoryName("Electronics");
        System.out.println("Fetched Category: " + fetchedCategory.getCategoryName());
        System.out.println("Its Products: " + fetchedCategory.getProducts());

        // ====================================================
        // PART (c): Custom JPQL Queries
        // ====================================================
        System.out.println("\n--- PART (c): Custom JPQL Queries ---\n");

        // (c1) Filter products by price range (500 to 5000)
        System.out.println(">> Products with price between 500 and 5000:");
        List<Product> filteredProducts = productRepository.findByPriceRange(500.0, 5000.0);
        for (Product p : filteredProducts) {
            System.out.println("   " + p);
        }

        // (c2) All products sorted by price
        System.out.println("\n>> All products sorted by price (ascending):");
        List<Product> sortedProducts = productRepository.findAllSortedByPrice();
        for (Product p : sortedProducts) {
            System.out.println("   " + p);
        }

        // (c3) Products in a specific category
        System.out.println("\n>> Products in 'Books' category:");
        List<Product> bookProducts = productRepository.findByCategoryName("Books");
        for (Product p : bookProducts) {
            System.out.println("   " + p);
        }

        // (c4) Users by role name
        System.out.println("\n>> Users with role 'USER':");
        List<User> usersWithRole = userRepository.findUsersByRoleName("USER");
        for (User u : usersWithRole) {
            System.out.println("   " + u + " -> Roles: " + u.getRoles());
        }

        System.out.println("\n>> Users with role 'ADMIN':");
        List<User> admins = userRepository.findUsersByRoleName("ADMIN");
        for (User u : admins) {
            System.out.println("   " + u + " -> Roles: " + u.getRoles());
        }

        // (c5) Search users by name keyword
        System.out.println("\n>> Search users with name containing 'li':");
        List<User> searchResult = userRepository.searchByName("li");
        for (User u : searchResult) {
            System.out.println("   " + u);
        }

        // (c6) Pagination - Page 0, Size 3 (first 3 products)
        System.out.println("\n>> Pagination: Page 1 (size=3), sorted by price DESC:");
        Page<Product> page = productRepository.findAllWithPagination(
            PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "price"))
        );
        System.out.println("   Total Products : " + page.getTotalElements());
        System.out.println("   Total Pages    : " + page.getTotalPages());
        System.out.println("   Current Page   : " + (page.getNumber() + 1));
        for (Product p : page.getContent()) {
            System.out.println("   " + p);
        }

        // Second page
        System.out.println("\n>> Pagination: Page 2 (size=3), sorted by price DESC:");
        Page<Product> page2 = productRepository.findAllWithPagination(
            PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC, "price"))
        );
        for (Product p : page2.getContent()) {
            System.out.println("   " + p);
        }

        System.out.println("\n==========================================================");
        System.out.println("   CHECK THE SQL QUERIES PRINTED ABOVE (show-sql=true)");
        System.out.println("   Hibernate generates SQL for each JPA operation.");
        System.out.println("==========================================================\n");
    }
}
