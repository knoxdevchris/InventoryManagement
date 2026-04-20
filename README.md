# Inventory Management

A small-scale inventory management system written in Java, organized as a
standard Maven project with a layered architecture, validated domain models,
and JUnit 5 test coverage.

## Features

- Multiple product categories: **Electronics**, **Food**, **Clothing**
- Category-specific attributes (warranty, expiration date, size)
- Input validation on every domain object and CLI prompt
- File-based persistence behind a swappable repository interface
- Summary statistics (total units, total inventory value)
- Clean command-line interface with typed prompts and friendly error messages
- Unit tests for models, services, and repository

## Project Layout

```
inventory-management/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/inventory/
    │   ├── InventoryApp.java          # Entry point
    │   ├── cli/                       # Console UI
    │   │   ├── ConsoleApp.java
    │   │   └── ConsoleIO.java
    │   ├── exception/                 # Domain exceptions
    │   │   ├── DuplicateProductException.java
    │   │   ├── InventoryException.java
    │   │   └── ProductNotFoundException.java
    │   ├── model/                     # Domain model
    │   │   ├── Category.java
    │   │   ├── Clothing.java
    │   │   ├── Electronics.java
    │   │   ├── Food.java
    │   │   └── Product.java
    │   ├── repository/                # Persistence abstraction
    │   │   ├── FileInventoryRepository.java
    │   │   ├── InMemoryInventoryRepository.java
    │   │   └── InventoryRepository.java
    │   └── service/                   # Business logic
    │       └── InventoryService.java
    └── test/java/com/inventory/       # JUnit 5 tests
        ├── model/ProductTest.java
        ├── repository/FileInventoryRepositoryTest.java
        └── service/InventoryServiceTest.java
```

## Architecture

Three layers keep responsibilities separated:

1. **Model** – Immutable-identity product entities with validation.
2. **Repository** – `InventoryRepository` interface with file and in-memory
   implementations.
3. **Service** – `InventoryService` owns business rules (duplicate detection,
   aggregation, filtering) and is the only entry point the CLI talks to.

The CLI layer (`ConsoleApp`, `ConsoleIO`) handles presentation and user input
parsing; it has no knowledge of persistence.

## Requirements

- Java 17+
- Maven 3.9+

## Build & Run

```bash
# Compile and run tests
mvn test

# Build a runnable jar
mvn package

# Launch the application (uses ./inventory.dat by default)
java -jar target/inventory-management-1.0.0.jar

# Or specify a custom data file
java -jar target/inventory-management-1.0.0.jar /path/to/data.dat
```

## Menu

```
1) Add product
2) Remove product
3) Update quantity
4) List all products
5) List by category
6) Summary
7) Save
8) Save and exit
```

## License

See [LICENSE](LICENSE).
