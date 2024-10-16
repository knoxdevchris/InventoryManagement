# InventoryManagement

**Small-scale inventory management system built in Java**

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Class Overview](#class-overview)
  - [Product](#product)
  - [Electronics](#electronics)
  - [Inventory](#inventory)
  - [InventoryApp](#inventoryapp)

---

## Introduction

This project is a simple **Inventory Management System** built in Java using Object-Oriented Programming (OOP) principles. It allows users to add, remove, and list products. The system also implements file persistence to save and load inventory data using file I/O.

---

## Features

- **Product Management**: Add, remove, and list products in the inventory.
- **OOP Principles**: Uses classes, inheritance, and abstraction.
- **File Persistence**: Inventory data is saved to a file and reloaded on startup.
- **Text-Based Interface**: Simple user interface through the command line.

---

## Class Overview

### Product
- **Description**: `Product` is the base class for all types of products in the inventory. It is abstract and provides common attributes like `productId`, `name`, `price`, and `quantity`. Each product type extends this class and implements the `getCategory` method.
- **Key Methods**:
  - `getProductId()`: Returns the product's ID.
  - `getName()`: Returns the product's name.
  - `getPrice()`: Returns the product's price.
  - `getQuantity()`: Returns the quantity of the product in stock.
  - `getCategory()`: Abstract method to return the product's category.

### Electronics
- **Description**: The `Electronics` class is a specific type of `Product` that represents electronics items in the inventory.
- **Key Methods**:
  - Inherits all methods from `Product`.
  - Implements `getCategory()`, returning `"Electronics"`.

### Inventory
- **Description**: The `Inventory` class manages a list of `Product` objects. It provides functionality to add and remove products from the inventory and uses file I/O to save and load the inventory.
- **Key Methods**:
  - `addProduct(Product product)`: Adds a new product to the inventory.
  - `removeProduct(String productId)`: Removes a product from the inventory by its ID.
  - `listProducts()`: Lists all products in the inventory.
  - `saveInventory(String fileName)`: Saves the inventory to a file.
  - `loadInventory(String fileName)`: Loads the inventory from a file.

### InventoryApp
- **Description**: This is the entry point of the program. It provides a text-based user interface to interact with the `Inventory` class and manage products.
- **Key Methods**:
  - `addProduct()`: Prompts the user to enter product details and adds the product to the inventory.
  - `removeProduct()`: Prompts the user to remove a product by its ID.
  - `saveInventory()`: Saves the current inventory to a file before exit.
  - `loadInventory()`: Loads the inventory from a file when the program starts.
