# Experiment 6 – Configure JPA & Hibernate with MySQL

## Objective
Configure JPA & Hibernate with MySQL/PostgreSQL to model entity relationships and execute queries.

## 📋 Experiment Requirements

**a)** Configure database connectivity in `application.properties` and create a simple JPA entity with a corresponding repository.

**b)** Model One-to-Many and Many-to-Many relationships (e.g., User–Role, Category–Product) and fetch related data using JPA.

**c)** Write custom JPQL queries to filter, sort, and paginate records (e.g., products by price range, users by role), and analyze generated SQL.

## What This Project Covers

### Part (a) – Database Configuration & Simple Entity
- MySQL database connectivity in `application.properties`
- Simple JPA entity (`Product`) with auto-generated ID
- JPA Repositories for CRUD operations

### Part (b) – Entity Relationships
- **One-to-Many**: `Category` → `Product` (one category has many products)
- **Many-to-Many**: `User` ↔ `Role` (users can have multiple roles, roles can belong to multiple users)

### Part (c) – Custom JPQL Queries
- Filter products by **price range**
- **Sort** products by price
- Filter products by **category name**
- Find **users by role** name
- **Search** users by name keyword
- **Pagination** with `PageRequest` and `Sort`
- `show-sql=true` prints all Hibernate-generated SQL

## Project Structure
```
src/main/java/com/example/exp6/
├── Exp6Application.java          ← Main class
├── model/
│   ├── Product.java              ← Simple entity + ManyToOne
│   ├── Category.java             ← OneToMany relationship
│   ├── User.java                 ← ManyToMany relationship
│   └── Role.java                 ← Role entity
├── repository/
│   ├── ProductRepository.java    ← JPQL queries (filter, sort, paginate)
│   ├── CategoryRepository.java
│   ├── UserRepository.java       ← JPQL queries (find by role, search)
│   └── RoleRepository.java
└── runner/
    └── DataLoader.java           ← Inserts sample data & runs demo queries
```

## How to Run

### Prerequisites
- Java 17+
- MySQL running on `localhost:3306`
- Update `src/main/resources/application.properties` with your MySQL username/password

### Steps
```bash
# Clone the repository
git clone https://github.com/Kartikxdboii/fs-exp6.git
cd fs-exp6

# Run the application (Maven wrapper included)
./mvnw spring-boot:run
```

The application will:
1. Auto-create the database `exp6_db`
2. Create tables from entity classes
3. Insert sample data (users, roles, categories, products)
4. Run all demo queries and print results to the console
5. Show Hibernate-generated SQL for each query

## Technologies Used
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Output
The application runs in console mode and displays:
- ✅ Entity creation and saving
- ✅ Relationship mappings (One-to-Many, Many-to-Many)
- ✅ Custom JPQL query results
- ✅ Pagination examples
- ✅ All Hibernate-generated SQL queries

## Database Schema
The application automatically creates these tables:
- `users` (id, name, email)
- `roles` (id, role_name)
- `user_roles` (user_id, role_id) - Join table for Many-to-Many
- `categories` (id, category_name)
- `products` (id, product_name, price, category_id)

## Author
**Kartik** - [GitHub](https://github.com/Kartikxdboii)
