
# 🛒 E-commerce Microservice Project

> ⚠️ **Note**: This project is currently **in progress** and under active development. Some features or modules may be incomplete or undergoing testing.

An enterprise-grade E-commerce platform built using microservices architecture with Spring Boot, featuring service discovery, centralized gateway routing, secure authentication via Keycloak, and containerization with Docker.

---

## ✨ Features

* **Microservices Architecture**: Modular and scalable service-based system.
* **Service Discovery**: Managed via Eureka for dynamic registration.
* **API Gateway (Port `8084`)**: Handles routing, filtering, and load balancing.
* **Keycloak Authentication**: OAuth2/OIDC-secured APIs with role-based access control.
* **Swagger UI**: Interactive API documentation for all services.
* **Dockerized Infrastructure**: Easily deployable with Docker Compose.
* **Resilience**: Circuit breakers using Resilience4j.
* **Clean Codebase**: Follows best practices and separation of concerns.

---

## 🧱 Microservices Overview

| Service               | Description                                      |
| --------------------- | ------------------------------------------------ |
| **Discovery Server**  | Eureka server for service registration/discovery |
| **API Gateway**       | Central gateway to route all external requests   |
| **Product Service**   | Manages product catalog                          |
| **Inventory Service** | Manages product stock and inventory              |
| **Order Service**     | Handles order creation and tracking              |
| **Keycloak Auth**     | Identity and access management server            |

---
## Architecture Diagram    




![Flow diagram](https://github.com/user-attachments/assets/0c7bbd74-a588-4ad3-b59b-b0bd6ed52bb5)







---

## 🔐 Keycloak Security

This project uses **Keycloak** to secure all services:

* Integrated via **OAuth2 / OIDC**
* Supports **Role-Based Access Control (RBAC)**
* Access tokens are required for protected endpoints
* Auth flow via `password` or `client_credentials` grants

> Access Keycloak Admin at `http://localhost:8080` (default)

\| Realm     | ecommerce            |
\| Clients   | gateway, product-service, order-service, inventory-service |
\| Protocol  | OpenID Connect       |

---

## 🛠️ Tech Stack

* **Java 17**
* **Spring Boot & Spring Cloud**
* **Spring Security + Keycloak**
* **PostgreSQL / MySQL**
* **Resilience4j**
* **Swagger/OpenAPI**
* **Docker & Docker Compose**
* **Maven**

---

## 🚀 Getting Started

### Prerequisites

* Java 17+
* Maven
* Docker
* PostgreSQL or MySQL
* Keycloak (via Docker or Manual Setup)

### Setup Instructions

```bash
# Clone project
git clone https://github.com/karansahani78/E-commerce-Microservice-Project.git
cd E-commerce-Microservice-Project

# Build services
mvn clean install

# Start all containers (including Keycloak and services)
docker-compose up
```

---

## 🌐 Service Access

| Component        | URL                                     |
| ---------------- | --------------------------------------- |
| API Gateway      | `http://localhost:8084`                 |
| Swagger UI       | `http://localhost:8084/swagger-ui.html` |
| Keycloak Console | `http://localhost:8080`                 |

---

## 📚 API Documentation

🔐 All endpoints are protected via Keycloak. Include your access token in `Authorization: Bearer <token>` header.

### 🛍️ Product Service

| Method | Endpoint            | Description       |
| ------ | ------------------- | ----------------- |
| GET    | `/api/product`      | Get all products  |
| GET    | `/api/product/{id}` | Get product by ID |
| POST   | `/api/product`      | Create a product  |
| PUT    | `/api/product/{id}` | Update a product  |
| DELETE | `/api/product/{id}` | Delete a product  |

### 📦 Inventory Service

| Method | Endpoint                     | Description                    |
| ------ | ---------------------------- | ------------------------------ |
| GET    | `/api/inventory`             | Get all inventory items        |
| GET    | `/api/inventory/{productId}` | Get inventory by product ID    |
| POST   | `/api/inventory`             | Add new inventory              |
| PUT    | `/api/inventory/{productId}` | Update inventory for a product |
| DELETE | `/api/inventory/{productId}` | Delete inventory for a product |

### 🧾 Order Service

| Method | Endpoint          | Description        |
| ------ | ----------------- | ------------------ |
| GET    | `/api/order`      | Get all orders     |
| GET    | `/api/order/{id}` | Get order by ID    |
| POST   | `/api/order`      | Create a new order |
| PUT    | `/api/order/{id}` | Update an order    |
| DELETE | `/api/order/{id}` | Delete an order    |

---

## 🔐 Example Auth Request

```bash
# Get access token from Keycloak
curl -X POST http://localhost:8080/realms/ecommerce/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=gateway" \
  -d "username=<your-username>" \
  -d "password=<your-password>" \
  -d "grant_type=password"
```

Use token in your API calls:

```bash
curl -H "Authorization: Bearer <access_token>" http://localhost:8084/api/product
```

---

## 📦 Folder Structure

```plaintext
├── api-gateway/            # Central gateway
├── discovery-server/       # Eureka registry
├── product-service/        # Product APIs
├── inventory-service/      # Inventory APIs
├── order-service/          # Order APIs
├── keycloak/               # Realm/client config
├── docker-compose.yml      # All services defined here
└── README.md
```

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repository and submit a PR.

---

## 📬 Contact

* GitHub: [karansahani78](https://github.com/karansahani78)
* Email: karansahani723@gmail.com

