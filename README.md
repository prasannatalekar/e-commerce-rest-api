# E-Commerce REST API

This is a Spring Boot REST API for an e-commerce platform.It supports user registration, login, role-based access, product management, and cart functionality.

## 🚀 Features

- User Registration and Login with hashed passwords (BCrypt)
- Role-based access control: Admin and User
- Admin can Add, Update, Delete Products
- Users can View Products and manage their Cart
- Product search with keyword filtering
- Clean layered architecture using Controller → Service → Repository
- MySQL database with JPA and Spring Data

## Technologies
Java, Spring Boot, MySQL, JPA, Maven

## 📂 Project Structure

- controller — contains REST API controllers (AuthController, ProductController, CartController)
- dto — contains request DTOs like LoginRequest and RegisterRequest
- model — contains entity classes like User, Product, CartItem
- repository — contains Spring Data JPA repositories
- service — contains business logic
- config — contains configuration beans like BCryptPasswordEncoder

## 📑 API Endpoints

### 🔐 Auth Endpoints

- POST `/auth/register`  
  → Register a new user or admin (based on role field)

- POST `/auth/login`  
  → Login using username and password

---

### 📦 Product Endpoints

- GET `/products`  
  → Fetch all products

- GET `/products/products/{id}`  
  → Get a single product by ID

- GET `/products/search?keyword=value`  
  → Search products by name or category (case-insensitive)

- POST `/products/admin/add?username=admin_username`  
  → Add a new product (Admin only)

- PUT `/products/admin/update/{id}?username=admin_username`  
  → Update existing product details (Admin only)

- DELETE `/products/admin/delete/{id}?username=admin_username`  
  → Delete a product by ID (Admin only)

---

### 🛒 Cart Endpoints

- POST `/cart/add/{productId}?username=username`  
  → Add a product to a user’s cart

- GET `/cart/view?username=username`  
  → View all cart items for a user

## 🧪 Running the App

1. Clone the repository
2. Create a MySQL database named `e_commerce`
3. Update your `application.properties` with database username and password
4. Run the app
