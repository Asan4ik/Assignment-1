# Airline Reservation API ✈️

A modern REST API built with Spring Boot that manages flight bookings, passenger records, and real-time seat capacity. This project evolved from a Java Console application into a full-scale web service.

## 🚀 Tech Stack
* **Java 25** (Spring Boot 4.0.2)
* **Spring Data JPA**: For database object mapping.
* **PostgreSQL**: Robust relational database storage.
* **Lombok**: To keep the code clean and boilerplate-free.
* **Maven**: For dependency management.

## 🛠️ Setup & Installation
1. **Database**: Create a PostgreSQL database named `SimpleDB`.
2. **Configuration**: Update `src/main/resources/application.properties` with your PostgreSQL username and password.
3. **Run**: Use IntelliJ to run `AirlineReservationPortalApplication.java` or use the command:
   ```bash
   mvn spring-boot:run