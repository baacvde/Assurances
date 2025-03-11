# Spring Microservices Architecture with Eureka Server

## Project Overview
This project is a microservices-based architecture built using Spring Boot. It consists of multiple services that communicate with each other via Eureka Server, acting as a service registry. Each service is designed to handle a specific domain:

- **Eureka Server**: Service registry for dynamic discovery of microservices.
- **Client Service**: Manages client-related operations and authentication.
- **Contract Service**: Handles contracts and policy management.
- **Notification Service**: Sends notifications based on events triggered by other services.

## Architecture Diagram
```
                     +--------------------+
                     |   Eureka Server    |
                     +--------------------+
                              |
      -------------------------------------------------
      |                   |                         |
+---------------+  +-----------------+  +------------------+
| Client Service|  | Contract Service|  | Notification Service|
+---------------+  +-----------------+  +------------------+
```
## Technologies Used
- **Spring Boot** - Core framework for building microservices.
- **Spring Cloud Netflix Eureka** - Service discovery and registration.
- **Spring Security** - Authentication and authorization.
- **Feign Client** - Communication between microservices.
- **Kafka** - Event-driven messaging system.
- **Spring Data JPA** - Database access and ORM.
- **MySQL** - Relational database for persistence.
- **Docker** - Containerization of services.
- **Swagger** - API documentation.

## Setup Instructions
### Prerequisites
Ensure you have the following installed:
- Java 17+
- Maven
- Docker (optional)
- MySQL database

### Installation Steps
1. **Clone the repository**:
   ```sh
   git clone https://github.com/your-repository.git
   cd your-repository
   ```
2. **Start Eureka Server**:
   ```sh
   cd eureka-server
   mvn spring-boot:run
   ```
3. **Start the microservices**:
   ```sh
   cd client-service
   mvn spring-boot:run
   ```
   ```sh
   cd contract-service
   mvn spring-boot:run
   ```
   ```sh
   cd notification-service
   mvn spring-boot:run
   ```
4. **Access Eureka Dashboard**:
   Open your browser and go to `http://localhost:8761`.

## API Endpoints
Each microservice exposes a set of REST APIs:
- **Client Service**: `http://localhost:8081/api/clients`
- **Contract Service**: `http://localhost:8082/api/contracts`
- **Notification Service**: `http://localhost:8083/api/notifications`

Use Swagger at `/swagger-ui.html` to explore available endpoints.

## Security Configuration
- The `Client Service` uses **Basic Authentication**.
- The `Contract Service` uses **JWT Authentication**.
- Microservices communicate securely using **Feign Clients**.

## Future Enhancements
- Implement API Gateway for centralized routing.
- Integrate Circuit Breaker with Resilience4j.
- Deploy on Kubernetes with Helm charts.

## Author
[Samba Diop]

## License
This project is licensed under the MIT License - see the LICENSE file for details.

