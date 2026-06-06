# 🛒 E-Commerce REST API

A **Spring Boot REST API** for an e-commerce platform with JWT authentication, role-based access control for **Admin** and **User** roles, featuring product management and cart functionality.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Spring Boot 3 | Application Framework |
| Spring Security | Authentication & Authorization |
| JWT (JSON Web Token) | Stateless Authentication |
| Spring Data JPA | Database ORM |
| MySQL | Relational Database |
| Lombok | Boilerplate Reduction |
| Maven | Build Tool |

---

## 📁 Project Structure
src/main/java/com/prasanna/e_commerce/
├── controller/
│   ├── AuthController.java
│   ├── AdminController.java
│   ├── ProductController.java
│   └── CartController.java
├── service/
│   ├── AuthService.java
│   ├── AdminService.java
│   ├── ProductService.java
│   ├── CartService.java
│   ├── JwtService.java
│   └── JwtFilter.java
├── repository/
│   ├── UserRepo.java
│   ├── ProductRepo.java
│   └── CartItemRepo.java
├── model/
│   ├── User.java
│   ├── Product.java
│   └── CartItem.java
├── dto/
│   ├── RegisterDto.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── ProductDto.java
│   └── CartItemDto.java
├── security/
│   ├── SecurityConfig.java
│   ├── PasswordConfig.java
│   ├── CustomUserDetails.java
│   └── CustomUserDetailsService.java
└── exception/
├── GlobalExceptionHandler.java
├── ErrorResponse.java
├── ResourceNotFoundException.java
├── BadRequestException.java
├── ConflictException.java
└── AuthenticationException.java

---

## ⚙️ Features

### 👤 User
- Register and login
- View all active products
- View product by ID
- Search products by name or category
- Add product to cart
- Remove product from cart
- View cart

### 👨‍💼 Admin
- Register and login
- View all products including inactive
- View product by ID
- Search all products
- Add new product
- Update product details
- Delete product (soft delete)

### 🔐 Auth
- Register as User
- Register as Admin
- Login (returns JWT token)

---

## 🔐 Security

- **JWT Authentication** — stateless token-based auth
- **BCrypt** password encoding
- **Role-based access control** — `ROLE_USER` and `ROLE_ADMIN`
- **Stateless** session management
- Token expiry — **1 hour**

---

## 🗄️ Database Schema

### Users Table
| Column | Type | Description |
|---|---|---|
| id | BIGINT | Primary Key |
| first_name | VARCHAR | First name |
| last_name | VARCHAR | Last name |
| username | VARCHAR | Unique username |
| email | VARCHAR | Unique email |
| password | VARCHAR | BCrypt encoded |
| address | VARCHAR | Delivery address |
| role | VARCHAR | ROLE_USER or ROLE_ADMIN |

### Products Table
| Column | Type | Description |
|---|---|---|
| id | BIGINT | Primary Key |
| name | VARCHAR | Product name |
| category | VARCHAR | Product category |
| description | VARCHAR | Product description |
| stock_quantity | INT | Available stock |
| price | DOUBLE | Product price |
| is_active | BOOLEAN | Soft delete flag |

### Cart Items Table
| Column | Type | Description |
|---|---|---|
| id | BIGINT | Primary Key |
| user_id | BIGINT | Foreign Key → Users |
| product_id | BIGINT | Foreign Key → Products |
| quantity | INT | Item quantity |
| total_amount | DOUBLE | price × quantity |

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven

### Setup

**1. Clone the repository:**
```bash
git clone https://github.com/prasannatalekar/e-commerce-rest-api.git
cd e-commerce-rest-api
```

**2. Create MySQL database:**
```sql
CREATE DATABASE e_commerce;
```

**3. Configure `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/e_commerce
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your_secret_key_here
```

**4. Run the application:**
```bash
mvn spring-boot:run
```

Application runs on `http://localhost:8080`

---

## 📮 API Endpoints

### 🔓 Auth (No Authorization Required)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register/user` | Register as user |
| POST | `/api/auth/register/admin` | Register as admin |
| POST | `/api/auth/login` | Login and get JWT token |

**Register Request Body:**
```json
{
    "firstName": "Prasanna",
    "lastName": "Kumar",
    "username": "prasanna",
    "email": "prasanna@gmail.com",
    "address": "Pune, Maharashtra",
    "password": "prasanna123"
}
```

**Login Request Body:**
```json
{
    "username": "prasanna",
    "password": "prasanna123"
}
```

**Login Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 👤 User & Admin APIs (JWT Token Required)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | Get all active products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/search?keyword=phone` | Search products |

---

### 🛒 Cart APIs (JWT Token Required — User)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/cart/view` | View cart |
| POST | `/api/cart/add/{productId}` | Add product to cart |
| DELETE | `/api/cart/remove/{productId}` | Remove product from cart |

---

### 👨‍💼 Admin APIs (JWT Token Required — Admin Only)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/admin/products` | Get all products |
| GET | `/api/admin/products/{id}` | Get product by ID |
| GET | `/api/admin/products/search?keyword=phone` | Search all products |
| POST | `/api/admin/add/product` | Add new product |
| PUT | `/api/admin/update/product/{id}` | Update product |
| DELETE | `/api/admin/delete/product/{id}` | Delete product |

**Add/Update Product Request Body:**
```json
{
    "name": "iPhone 15",
    "category": "Electronics",
    "description": "Latest iPhone model",
    "stockQuantity": 50,
    "price": 79999.00,
    "isActive": true
}
```

---

## 🔑 How to Use JWT in Postman

**Step 1 — Login and copy token:**
POST http://localhost:8080/api/auth/login
Response: { "token": "eyJhbGci..." }

**Step 2 — Add token to every request:**
Headers:
Authorization: Bearer eyJhbGci...

---

## ⚠️ Error Handling

All errors return a structured response:

```json
{
    "localDateTime": "2025-01-01T10:00:00",
    "status": 404,
    "message": "Product not found!",
    "path": "/api/products/99"
}
```

| Exception | HTTP Status | Scenario |
|---|---|---|
| ResourceNotFoundException | 404 | Product/User not found |
| BadRequestException | 400 | Out of stock, cart empty |
| ConflictException | 409 | Username/Email already exists |
| AuthenticationException | 401 | Invalid credentials |

---

## 👨‍💻 Author

**Prasanna Talekar**
- GitHub: [@prasannatalekar](https://github.com/prasannatalekar)
