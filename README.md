# Experiment 6 – Configure JPA & Hibernate with MySQL

## Objective
Configure JPA & Hibernate with MySQL to model entity relationships and execute queries.

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
