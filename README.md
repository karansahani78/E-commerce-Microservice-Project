
# **E-commerce Microservice Project**

An **enterprise-grade** E-commerce Microservice Architecture built using **Spring Boot**, designed to showcase scalable, modular, and maintainable backend systems. Each microservice is **decoupled** and follows best practices for communication, discovery, and resilience.

---

## **✨ Features**

- 🧱 Microservices (Spring Boot-based)  
- 🔍 Service Discovery using **Eureka**  
- 🚪 Centralized Routing with **API Gateway**  
- 📘 Unified API Documentation using **Swagger UI**  
- 🐳 **Docker** containerization support  
- 🛢️ Database Integration (**PostgreSQL / MySQL**)  
- 🔐 Secure Configuration (**Spring Config Ready**)  
- ⚡ Resilience with **Circuit Breakers** (Resilience4j)  
- 🚀 Scalable and Extensible Design  

---

## **📐 Microservice Architecture**

Here’s a high-level diagram of how the services are interconnected:

```
                         +-------------------+
                         |   Eureka Server   |
                         +-------------------+
                                 |
                ------------------------------------------
               |                     |                    |
       +--------------+     +---------------+    +----------------+
       | Product Svc  |     | Order Svc     |    | Payment Svc    |
       +--------------+     +---------------+    +----------------+
               |                     |                    |
               ---------------------------------------------
                                 |
                         +------------------+
                         |   API Gateway    |
                         +------------------+
                                 |
                         +------------------+
                         |   Swagger UI     |
                         +------------------+
```

Each microservice is **registered with the Eureka Discovery Server**. Traffic flows through the **API Gateway**, and the **Swagger UI** serves as the unified API interface.

---

## **🧩 Microservices Included**

- **Product Service** – Product catalog management (CRUD)  
- **Order Service** – Order lifecycle management  
- **Payment Service** – Simulated payment handling  
- **User/Auth Service** – Authentication & Authorization  
- **Inventory Service** – Stock tracking  
- **API Gateway** – Unified entry point for routing  
- **Eureka Server** – Service registry & discovery  

---

## **🛠️ Technologies Used**

- **Java 17+**  
- **Spring Boot 3+**  
- **Spring Cloud (Gateway, Eureka)**  
- **Spring Data JPA**  
- **Swagger / OpenAPI**  
- **PostgreSQL / MySQL**  
- **Docker**  
- **Maven**  

---

## **🚀 Getting Started**

### **📦 Prerequisites**

- Java 17 or higher  
- Maven  
- Docker (optional)  
- PostgreSQL / MySQL  

### **📂 Clone & Run**

```bash
git clone https://github.com/karansahani78/E-commerce-Microservice-Project.git
cd E-commerce-Microservice-Project
```

Configure your **application.yml** or **application.properties** with database details.

#### **Build All Services**
```bash
mvn clean install
```

#### **Start Eureka First**
```bash
cd eureka-server
mvn spring-boot:run
```

#### **Then Start API Gateway and Other Services**
```bash
cd api-gateway
mvn spring-boot:run
```

Repeat the above for each microservice.

---

## **📘 API Documentation**

Unified Swagger UI is accessible via:

🌐 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Use it to explore and test all APIs from one centralized location!

---

## **📁 Folder Structure**

```
E-commerce-Microservice-Project/
│
├── api-gateway/
├── eureka-server/
├── product-service/
├── order-service/
├── payment-service/
├── user-service/
├── inventory-service/
└── README.md
```

---

## **🤝 Contributing**

Contributions are welcome!  
Fork the repo and submit a **Pull Request**.

- Found a bug? Open an **Issue**  
- Have a feature request? Let us know!

---

## **👨‍💻 Author**

**Karan Sahani**  
📧 karansahani723@gmail.com  
🔗 [GitHub](https://github.com/karansahani78) | [LinkedIn](https://www.linkedin.com/in/karansahani78)

---

## **📄 License**

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for more information.

---
